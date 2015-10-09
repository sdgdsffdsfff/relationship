package com.relationship.analyze.domain.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.graph.Edge;

/**
 * 图
 * 
 * @author yanjiu.lj
 */
public class Graph {

    /** 顶点到边的映射 */
    private Map<Vertex, List<Edge>> vertex2Edges;

    /** user到顶点的映射 */
    private Map<User, Vertex>       user2Vertex;

    /**
     * 构造函数
     * 
     * @param vertex2Edges 顶点到边的映射
     * @param user2Vertex 用户到顶点的映射
     */
    public Graph(Map<Vertex, List<Edge>> vertex2Edges, Map<User, Vertex> user2Vertex) {
        this.vertex2Edges = vertex2Edges != null ? vertex2Edges
            : new HashMap<Vertex, List<Edge>>(1);
        this.user2Vertex = user2Vertex != null ? user2Vertex : new HashMap<User, Vertex>(1);
    }

    /**
     * 根据user获取在图中对应顶点
     * 
     * @param user 用户
     * @return vertex 顶点
     */
    public Vertex getVertexBy(User user) {
        return user2Vertex.get(user);
    }

    /**
     * 根据顶点获取图中关联的边
     * 
     * @param vertex 顶点
     * @return edges 关联边
     */
    public List<Edge> getEdgesFor(Vertex vertex) {
        return vertex2Edges.get(vertex);
    }

    /**
     * 获取图中顶点到变的映射关系
     * 
     * @return 顶点到边的映射关系
     */
    public Map<Vertex, List<Edge>> getVertex2Edges() {
        return vertex2Edges;
    }
}
