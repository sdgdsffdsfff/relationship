package com.relationship.analyze.service.compute;

import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.graph.Graph;
import com.relationship.analyze.domain.graph.RelateUserContainer;
import com.relationship.analyze.domain.result.RelationComputeResult;

/**
 * 图计算基类
 * 
 * @author yanjiu.lj
 */
public abstract class GraphCompute {

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

    /**
     * 查询两用户的关联关系
     * 
     * @param src 源点
     * @param dst 目标点
     * @param depthLimit 计算度数限制
     * @return 关系计算结果
     */
    public abstract RelationComputeResult relationBetween(User src, User dst, Integer depthLimit);

    /**
     * 查询用户关联的用户信息
     * 
     * @param src 源点
     * @param depth 计算度数限制
     * @return 关联用户计算结果容器
     */
    public abstract RelateUserContainer relateElements(User src, Integer depth);

    /**
     * 查询用户关联的用户个数
     * 
     * @param src 源点
     * @param depth 计算度数限制
     * @return 关联用户计算结果容器
     */
    public abstract RelateUserContainer relateElementSize(User src, Integer depth);
}