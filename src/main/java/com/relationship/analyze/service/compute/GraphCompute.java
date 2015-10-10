package com.relationship.analyze.service.compute;

import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.graph.Graph;

/**
 * 图计算基类
 * 
 * @author yanjiu.lj
 */
public abstract class GraphCompute implements ComputeService {

    /** 图信息 */
    protected Graph graph;

    /** 起始点 */
    protected User  source;

    /**
     * 构造函数
     * 
     * @param graph 目标图对象
     */
    public GraphCompute(Graph graph) {
        this.graph = graph;
        this.source = null;
    }
}