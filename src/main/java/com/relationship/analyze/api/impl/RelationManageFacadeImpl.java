package com.relationship.analyze.api.impl;

import com.relationship.analyze.api.RelationManageFacade;
import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.result.RelationManageResult;
import com.relationship.analyze.service.relation.manage.RelationManager;

/**
 * 用户关系管理服务
 * 
 * @author yanjiu.lj
 */
public class RelationManageFacadeImpl implements RelationManageFacade {

    /**  relationManager   */
    private RelationManager relationManager;

    @Override
    public RelationManageResult addFriend(User src, User dst) {
        return relationManager.addFriend(src, dst);
    }

    @Override
    public RelationManageResult deleteFriend(User src, User dst) {
        return relationManager.deleteFriend(src, dst);
    }

    /**
     * @param relationManager the relationManager to set
     */
    public void setRelationManager(RelationManager relationManager) {
        this.relationManager = relationManager;
    }
}
