package com.relationship.analyze.api.impl;

import com.relationship.analyze.api.RelationComputeFacade;
import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.graph.RelateUserContainer;
import com.relationship.analyze.domain.result.RelationComputeResult;
import com.relationship.analyze.service.compute.GraphComputeBFSImpl;

/**
 * 用户关系计算服务实现
 * 
 * @author yanjiu.lj
 */
public class RelationComputeFacadeImpl implements RelationComputeFacade {
    /**  graphComputeBFSImpl   */
    private GraphComputeBFSImpl graphComputeBFSImpl;

    @Override
    public RelationComputeResult relationBetween(User src, User dst, Integer depthLimit) {
        return graphComputeBFSImpl.relationBetween(src, dst, depthLimit);
    }

    @Override
    public RelateUserContainer relateElements(User src, Integer depth) {
        return graphComputeBFSImpl.relateElements(src, depth);
    }

    @Override
    public RelateUserContainer relateElementSize(User src, Integer depth) {
        return graphComputeBFSImpl.relateElementSize(src, depth);
    }

    /**
     * @param graphComputeBFSImpl the graphComputeBFSImpl to set
     */
    public void setGraphComputeBFSImpl(GraphComputeBFSImpl graphComputeBFSImpl) {
        this.graphComputeBFSImpl = graphComputeBFSImpl;
    }
}
