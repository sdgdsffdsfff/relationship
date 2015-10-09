package com.relationship.analyze.service.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.graph.Edge;
import com.relationship.analyze.domain.graph.Graph;
import com.relationship.analyze.domain.graph.Group;
import com.relationship.analyze.domain.graph.Vertex;

/**
 * 构图工厂
 * 
 * @author yanjiu.lj
 */
public class GraphFactory {

    /** 用户朋友ID列表分隔符    */
    private static final String SEP = ",";

    /**
     * 构图方法 
     * 
     * @param group 群组关联信息
     * @return graph 图
     */
    public static Graph createGraph(Group group) {
        if (null == group || null == group.getUsers()) {
            return null;
        }

        //顶点到关联边的映射
        Map<Vertex, List<Edge>> v2edges = new HashMap<Vertex, List<Edge>>();
        //基础元素到顶点的映射
        Map<User, Vertex> user2vertexs = new HashMap<User, Vertex>();
        //基础元素对列表
        List<User> users = group.getUsers();

        for (User user : users) {
            user2vertexs.put(user, new Vertex(user));
        }

        for (User srcUser : user2vertexs.keySet()) {
            //获取待过滤基础元素和事件列表
            String[] friendsId = srcUser.getFriendsId().split(SEP);

            for (String dstUserId : friendsId) {
                //添加顶点到关联边映射
                addEdge(user2vertexs.get(srcUser), user2vertexs.get(findFriend(users, dstUserId)),
                    1, v2edges);
            }
        }

        return new Graph(v2edges, user2vertexs);
    }

    /**
     * 根据用户id查找用户
     * 
     * @param users 待查找用户集合
     * @param userId 用户id
     * @return 目标用户
     */
    private static User findFriend(List<User> users, String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }

        return null;
    }

    /**
     * 构造源点到目标点的边，并添加到图中
     * 
     * @param vSrc 源点
     * @param vDst 目标点
     * @param cnnTimes 联通次数
     * @param v2edges 图中顶点到边的关联关系
     */
    private static void addEdge(Vertex vSrc, Vertex vDst, int cnnTimes,
                                Map<Vertex, List<Edge>> v2edges) {

        List<Edge> list = v2edges.get(vSrc);
        Edge edge = new Edge(vDst, cnnTimes);

        if (list != null) {
            list.add(edge);
        } else {
            list = new ArrayList<Edge>();
            list.add(edge);
            v2edges.put(vSrc, list);
        }
    }
}