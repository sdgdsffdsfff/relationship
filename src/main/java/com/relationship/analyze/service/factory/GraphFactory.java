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
 * ��ͼ����
 * 
 * @author yanjiu.lj
 */
public class GraphFactory {

    /** �û�����ID�б�ָ���    */
    private static final String SEP = ",";

    /**
     * ��ͼ���� 
     * 
     * @param group Ⱥ�������Ϣ
     * @return graph ͼ
     */
    public static Graph createGraph(Group group) {
        if (null == group || null == group.getUsers()) {
            return null;
        }

        //���㵽�����ߵ�ӳ��
        Map<Vertex, List<Edge>> v2edges = new HashMap<Vertex, List<Edge>>();
        //����Ԫ�ص������ӳ��
        Map<User, Vertex> user2vertexs = new HashMap<User, Vertex>();
        //����Ԫ�ض��б�
        List<User> users = group.getUsers();

        for (User user : users) {
            user2vertexs.put(user, new Vertex(user));
        }

        for (User srcUser : user2vertexs.keySet()) {
            //��ȡ�����˻���Ԫ�غ��¼��б�
            String[] friendsId = srcUser.getFriendsId().split(SEP);

            for (String dstUserId : friendsId) {
                //��Ӷ��㵽������ӳ��
                addEdge(user2vertexs.get(srcUser), user2vertexs.get(findFriend(users, dstUserId)),
                    1, v2edges);
            }
        }

        return new Graph(v2edges, user2vertexs);
    }

    /**
     * �����û�id�����û�
     * 
     * @param users �������û�����
     * @param userId �û�id
     * @return Ŀ���û�
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
     * ����Դ�㵽Ŀ���ıߣ�����ӵ�ͼ��
     * 
     * @param vSrc Դ��
     * @param vDst Ŀ���
     * @param cnnTimes ��ͨ����
     * @param v2edges ͼ�ж��㵽�ߵĹ�����ϵ
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