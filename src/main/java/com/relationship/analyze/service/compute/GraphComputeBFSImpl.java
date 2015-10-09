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
 * �û���ϵͼ����BFSʵ��
 * 
 * @author yanjiu.lj
 */
public class GraphComputeBFSImpl extends GraphCompute {

    /** �������� */
    private Queue<Vertex>       queue             = null;

    /** ��������� */
    private int                 depthLimit        = Integer.MAX_VALUE;

    /** �����û����������� */
    private RelateUserContainer relateUserCounter = null;

    /** Ŀ��Ԫ�� */
    private User                expectedDst       = null;

    /**
     * ���캯��
     * 
     * @param graph ͼ��Ϣ
     */
    public GraphComputeBFSImpl() {
        super(null);
    }

    /**
     * ���캯��
     * 
     * @param graph ͼ��Ϣ
     */
    public GraphComputeBFSImpl(Graph graph) {
        super(graph);

        queue = new LinkedList<Vertex>();
        relateUserCounter = new RelateUserContainer();
    }

    /**
    * ��BFS��������֮ǰ���г�ʼ��
    * 
    * @param source Դ��
    * @param depth ��������
    */
    private void prepare(User source, Integer depth) {
        //���ü���������ƣ���ն��в���ʼ������Դ��
        this.depthLimit = depth;
        queue.clear();
        relateUserCounter.clear();

        init(source);
    }

    /**
     * ��ʼ��Դ��,�������
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
     * bfsÿ�α�����������һ���������Ķ����������������ĳ��������һ���ֹͣ��
     * ��Ϊ�������е���ͨ�����Ȳ����ᱻĿ���ͬ�㶥��Ĺ������Ӱ��
     */
    private void compute() {
        Vertex v = null;
        while ((v = queue.peek()) != null) {
            if (v.getDepth() > depthLimit) {
                break;
            }

            //�������б�
            List<Edge> edges = graph.getEdgesFor(v);
            int depth = v.getDepth() + 1;
            int edgesSize = edges == null ? 0 : edges.size();

            //����ÿһ���ڱ�
            for (int index = 0; index < edgesSize; index++) {
                //���ڱ�
                Edge edge = edges.get(index);
                //���ڶ���
                Vertex dst = edge.getDst();

                if (!dst.isVisited())/*���㱻��һ�α���*/{

                    int connTimes = edge.getConnTimes();
                    if (connTimes != 0) /*����ͨ��������0����ʾ�ɴ�*/{
                        //·������ͨ����=ͬһ��·����ѡ�����б�����С��ͨ����
                        connTimes = v.getConnTimes() > 0 ? Math.min(v.getConnTimes(), connTimes)
                            : connTimes;
                        dst.reset(depth, v.getPathCounter(), connTimes, true);
                        //ͳ�ƹ���user
                        relateUserCounter.add(depth, dst.getUser());

                        if (depth <= depthLimit)/*���С�ڼ������Լ��*/{
                            queue.add(dst);

                            if (dst.getUser().equals(expectedDst)) /*����ҵ�Ŀ��㣬����������굱ǰ�������ֹͣ*/{
                                depthLimit = depth;
                            }
                        }
                    }

                } else if (dst.getDepth() == depth)/*�����ѱ����ʹ�,�ҵ�ǰ����·�������Ⱥ�ǰ�����*/{
                    int connTimes = edge.getConnTimes();
                    if (connTimes == 0)/*�����ͨ*/{
                        continue;
                    }

                    //����·������
                    dst.setPathCounter(dst.getPathCounter() + v.getPathCounter());
                    //·������ͨ����=ͬһ��·����ѡ�����б�����С��ͨ����
                    connTimes = v.getConnTimes() > 0 ? Math.min(v.getConnTimes(), connTimes)
                        : connTimes;

                    //������ͨ����=��ͬ·��ѡ����
                    connTimes = Math.max(connTimes, dst.getConnTimes());
                    dst.setConnTimes(connTimes);
                }
            }

            //����
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

        //�ж��Ƿ���ͬһ��Ⱥ����
        if (graph == null || graph.getVertexBy(src) == null || graph.getVertexBy(dst) == null) {
            return composeResult(depthLimit, 0, 0);
        }

        //����ǰ��׼��
        prepare(src, depthLimit);
        this.expectedDst = dst;

        //BFS����
        compute();

        //������ֵ
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
     * ��װ���������б�
     * 
     * @param dstVertex Ŀ��� 
     * @param src TODO
     * @return ���������б�
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
        //�ж��Ƿ���Ⱥ����
        if (graph.getVertexBy(src) == null) {
            return null;
        }

        //����ǰ��        
        prepare(src, depthLimit);

        //BFS����        
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
     * ��װ������
     * 
     * @param depthLimit �����������
     * @param pathCnt ·������
     * @param cnnTimes ��ͨ����
     * @return ��ϵ������
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
