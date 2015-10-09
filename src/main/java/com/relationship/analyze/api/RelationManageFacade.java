package com.relationship.analyze.api;

import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.result.RelationManageResult;

/**
 * �û���ϵ�������
 * 
 * @author yanjiu.lj
 */
public interface RelationManageFacade {
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
