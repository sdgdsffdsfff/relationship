package com.relationship.analyze.domain.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.graph.Edge;

/**
 * ͼ
 * 
 * @author yanjiu.lj
 */
public class Graph {

    /** ���㵽�ߵ�ӳ�� */
    private Map<Vertex, List<Edge>> vertex2Edges;

    /** user�������ӳ�� */
    private Map<User, Vertex>       user2Vertex;

    /**
     * ���캯��
     * 
     * @param vertex2Edges ���㵽�ߵ�ӳ��
     * @param user2Vertex �û��������ӳ��
     */
    public Graph(Map<Vertex, List<Edge>> vertex2Edges, Map<User, Vertex> user2Vertex) {
        this.vertex2Edges = vertex2Edges != null ? vertex2Edges
            : new HashMap<Vertex, List<Edge>>(1);
        this.user2Vertex = user2Vertex != null ? user2Vertex : new HashMap<User, Vertex>(1);
    }

    /**
     * ����user��ȡ��ͼ�ж�Ӧ����
     * 
     * @param user �û�
     * @return vertex ����
     */
    public Vertex getVertexBy(User user) {
        return user2Vertex.get(user);
    }

    /**
     * ���ݶ����ȡͼ�й����ı�
     * 
     * @param vertex ����
     * @return edges ������
     */
    public List<Edge> getEdgesFor(Vertex vertex) {
        return vertex2Edges.get(vertex);
    }

    /**
     * ��ȡͼ�ж��㵽���ӳ���ϵ
     * 
     * @return ���㵽�ߵ�ӳ���ϵ
     */
    public Map<Vertex, List<Edge>> getVertex2Edges() {
        return vertex2Edges;
    }
}
