package com.relationship.analyze.service.relation.manage;

import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.result.RelationManageResult;

public interface RelationManager {
    /**
     * ���û��������ѹ�ϵ
     * 
     * @param src Դ��
     * @param dst Ŀ���
     * @return ��ϵ������
     */
    public RelationManageResult addFriend(User src, User dst);

    /**
     * ���û�ɾ�����ѹ�ϵ
     * 
     * @param src Դ��
     * @param dst Ŀ���
     * @return ��ϵ������
     */
    public RelationManageResult deleteFriend(User src, User dst);
}
