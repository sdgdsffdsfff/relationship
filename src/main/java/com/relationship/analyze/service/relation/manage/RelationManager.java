package com.relationship.analyze.service.relation.manage;

import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.result.RelationManageResult;

/**
 * @author yanjiu.lj
 */
public interface RelationManager {
    /**
     * 两用户增加朋友关系
     * 
     * @param src 源点
     * @param dst 目标点
     * @return 关系管理结果
     */
    public RelationManageResult addFriend(User src, User dst);

    /**
     * 两用户删除朋友关系
     * 
     * @param src 源点
     * @param dst 目标点
     * @return 关系管理结果
     */
    public RelationManageResult deleteFriend(User src, User dst);
}
