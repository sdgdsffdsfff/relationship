package com.relationship.analyze.service.compute;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.graph.Edge;
import com.relationship.analyze.domain.graph.Graph;
import com.relationship.analyze.domain.graph.RelateUserContainer;
import com.relationship.analyze.domain.graph.Vertex;
import com.relationship.analyze.domain.result.RelationComputeResult;

/**
 * 用户关系图计算BFS实现
 * 
 * @author yanjiu.lj
 */
public class GraphComputeBFSImpl extends GraphCompute {

    /** 遍历队列 */
    private Queue<Vertex>       queue             = null;

    /** 最大遍历深度 */
    private int                 depthLimit        = Integer.MAX_VALUE;

    /** 关联用户计算结果容器 */
    private RelateUserContainer relateUserCounter = null;

    /** 目标元素 */
    private User                expectedDst       = null;

    /**
     * 构造函数
     * 
     * @param graph 图信息
     */
    public GraphComputeBFSImpl() {
        super(null);
    }

    /**
     * 构造函数
     * 
     * @param graph 图信息
     */
    public GraphComputeBFSImpl(Graph graph) {
        super(graph);

        queue = new LinkedList<Vertex>();
        relateUserCounter = new RelateUserContainer();
    }

    /**
    * 在BFS搜索计算之前进行初始化
    * 
    * @param source 源点
    * @param depth 度数限制
    */
    private void prepare(User source, Integer depth) {
        //设置计算度数限制，清空队列并初始化计算源点
        this.depthLimit = depth;
        queue.clear();
        relateUserCounter.clear();

        init(source);
    }

    /**
     * 初始化源点,并入队列
     * 
     * @param source
     */
    private void init(User source) {
        Vertex vs = graph.getVertexBy(source);
        vs.reset(0, 1, 0, true);

        this.source = source;
        this.queue.add(vs);
    }

    /**
     * bfs每次遍历会遍历完成一个“完整的度数”，不会遍历到某个度数的一半就停止，
     * 因为计算结果中的连通次数等参数会被目标点同层顶点的关联情况影响
     */
    private void compute() {
        Vertex v = null;
        while ((v = queue.peek()) != null) {
            if (v.getDepth() > depthLimit) {
                break;
            }

            //遍历所有边
            List<Edge> edges = graph.getEdgesFor(v);
            int depth = v.getDepth() + 1;
            int edgesSize = edges == null ? 0 : edges.size();

            //遍历每一条邻边
            for (int index = 0; index < edgesSize; index++) {
                //相邻边
                Edge edge = edges.get(index);
                //相邻顶点
                Vertex dst = edge.getDst();

                if (!dst.isVisited())/*顶点被第一次遍历*/{

                    int connTimes = edge.getConnTimes();
                    if (connTimes != 0) /*边连通次数大于0，表示可达*/{
                        //路径的连通次数=同一条路径中选择所有边中最小连通次数
                        connTimes = v.getConnTimes() > 0 ? Math.min(v.getConnTimes(), connTimes)
                            : connTimes;
                        dst.reset(depth, v.getPathCounter(), connTimes, true);
                        //统计关联user
                        relateUserCounter.add(depth, dst.getUser());

                        if (depth <= depthLimit)/*深度小于计算深度约束*/{
                            queue.add(dst);

                            if (dst.getUser().equals(expectedDst)) /*如果找到目标点，则继续遍历完当前度数层才停止*/{
                                depthLimit = depth;
                            }
                        }
                    }

                } else if (dst.getDepth() == depth)/*顶点已被访问过,且当前计算路径层数度和前次相等*/{
                    int connTimes = edge.getConnTimes();
                    if (connTimes == 0)/*如果连通*/{
                        continue;
                    }

                    //增加路径条数
                    dst.setPathCounter(dst.getPathCounter() + v.getPathCounter());
                    //路径的连通次数=同一条路径中选择所有边中最小连通次数
                    connTimes = v.getConnTimes() > 0 ? Math.min(v.getConnTimes(), connTimes)
                        : connTimes;

                    //顶点连通次数=不同路径选择大的
                    connTimes = Math.max(connTimes, dst.getConnTimes());
                    dst.setConnTimes(connTimes);
                }
            }

            //出队
            queue.poll();
        }
    }

    /* (non-Javadoc)
     * @see com.relationship.analyze.compute.GraphCompute#relationBetween(com.relationship.analyze.graph.User, com.relationship.analyze.graph.User, java.lang.Integer)
     */
    @Override
    public RelationComputeResult relationBetween(User src, User dst, Integer depthLimit) {
        if (src == null || dst == null) {
            return composeResult(depthLimit, 0, 0);
        }

        //判断是否在同一个群组中
        if (graph == null || graph.getVertexBy(src) == null || graph.getVertexBy(dst) == null) {
            return composeResult(depthLimit, 0, 0);
        }

        //计算前置准备
        prepare(src, depthLimit);
        this.expectedDst = dst;

        //BFS计算
        compute();

        //处理返回值
        Vertex dstVertex = graph.getVertexBy(dst);

        RelationComputeResult result = null;
        if (!dstVertex.isRelate(depthLimit)) {
            result = composeResult(depthLimit, 0, 0);
        } else {
            result = composeResult(depthLimit, dstVertex.getPathCounter(), dstVertex.getConnTimes());

            result.setAdjFriendList(composeAdjUsers(dstVertex, src));
        }

        return result;
    }

    /**
     * 组装过渡朋友列表
     * 
     * @param dstVertex 目标点 
     * @param src TODO
     * @return 过渡朋友列表
     */
    private List<User> composeAdjUsers(Vertex dstVertex, User src) {
        List<User> adjUsers = new ArrayList<User>();
        adjUsers.add(dstVertex.getUser());

        for (int depth = dstVertex.getDepth() - 1; depth > 0; depth--) {
            Set<User> users = relateUserCounter.realteUsers(depth);

            for (User user : users) {
                if (user.getFriendsId().contains(dstVertex.getUser().getUserId())) {
                    adjUsers.add(user);

                    dstVertex = graph.getVertexBy(user);
                    continue;
                }
            }
        }

        adjUsers.add(src);
        return adjUsers;
    }

    /* (non-Javadoc)
     * @see com.relationship.analyze.compute.GraphCompute#relateElements(com.relationship.analyze.graph.User, java.lang.Integer)
     */
    @Override
    public RelateUserContainer relateElements(User src, Integer depthLimit) {
        //判断是否在群组中
        if (graph.getVertexBy(src) == null) {
            return null;
        }

        //计算前置        
        prepare(src, depthLimit);

        //BFS计算        
        compute();

        return relateUserCounter;
    }

    /* (non-Javadoc)
     * @see com.relationship.analyze.compute.GraphCompute#relateElementSize(com.relationship.analyze.graph.User, java.lang.Integer)
     */
    @Override
    public RelateUserContainer relateElementSize(User src, Integer depthLimit) {
        return relateElements(src, depthLimit);
    }

    /**
     * 组装计算结果
     * 
     * @param depthLimit 计算度数限制
     * @param pathCnt 路径条数
     * @param cnnTimes 连通次数
     * @return 关系计算结果
     */
    private RelationComputeResult composeResult(Integer depthLimit, Integer pathCnt,
                                                Integer cnnTimes) {
        RelationComputeResult result = new RelationComputeResult();
        result.setConnTimes(cnnTimes);
        result.setPathSize(pathCnt);
        result.setDepth(depthLimit);
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "GraphComputeBFSImpl [queue=" + queue + ", depthLimit=" + depthLimit
               + ", relateElementCounter=" + relateUserCounter + ", expectedDst=" + expectedDst
               + "]";
    }
}
