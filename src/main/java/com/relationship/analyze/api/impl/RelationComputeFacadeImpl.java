package com.relationship.analyze.api.impl;

import com.relationship.analyze.api.RelationComputeFacade;
import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.graph.RelateUserContainer;
import com.relationship.analyze.domain.result.RelationComputeResult;
import com.relationship.analyze.service.compute.ComputeService;

/**
 * 用户关系计算服务实现
 * 
 * @author yanjiu.lj
 */
public class RelationComputeFacadeImpl implements RelationComputeFacade {
    /**  graphComputeBFSImpl   */
    private ComputeService computeService;

    @Override
    public RelationComputeResult relationBetween(User src, User dst, Integer depthLimit) {
        return computeService.relationBetween(src, dst, depthLimit);
    }

    @Override
    public RelateUserContainer relateElements(User src, Integer depth) {
        return computeService.relateElements(src, depth);
    }

    @Override
    public RelateUserContainer relateElementSize(User src, Integer depth) {
        return computeService.relateElementSize(src, depth);
    }

    /**
     * @param computeService the computeService to set
     */
    public void setComputeService(ComputeService computeService) {
        this.computeService = computeService;
    }
}
