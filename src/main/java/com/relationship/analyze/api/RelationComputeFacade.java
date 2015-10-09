package com.relationship.analyze.api;

import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.graph.RelateUserContainer;
import com.relationship.analyze.domain.result.RelationComputeResult;

/**
 * 用户关系计算对外api
 * 
 * @author yanjiu.lj
 */
public interface RelationComputeFacade {
    /**
     * 查询两用户的关联关系
     * 
     * @param src 源点
     * @param dst 目标点
     * @param depthLimit 计算度数限制
     * @return 关系计算结果
     */
    public RelationComputeResult relationBetween(User src, User dst, Integer depthLimit);

    /**
     * 查询用户关联的用户信息
     * 
     * @param src 源点
     * @param depth 计算度数限制
     * @return 关联用户计算结果容器
     */
    public RelateUserContainer relateElements(User src, Integer depth);

    /**
     * 查询用户关联的用户个数
     * 
     * @param src 源点
     * @param depth 计算度数限制
     * @return 关联用户计算结果容器
     */
    public RelateUserContainer relateElementSize(User src, Integer depth);
}
