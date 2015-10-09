package com.relationship.analyze.api;

import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.graph.RelateUserContainer;
import com.relationship.analyze.domain.result.RelationComputeResult;

/**
 * �û���ϵ�������api
 * 
 * @author yanjiu.lj
 */
public interface RelationComputeFacade {
    /**
     * ��ѯ���û��Ĺ�����ϵ
     * 
     * @param src Դ��
     * @param dst Ŀ���
     * @param depthLimit �����������
     * @return ��ϵ������
     */
    public RelationComputeResult relationBetween(User src, User dst, Integer depthLimit);

    /**
     * ��ѯ�û��������û���Ϣ
     * 
     * @param src Դ��
     * @param depth �����������
     * @return �����û�����������
     */
    public RelateUserContainer relateElements(User src, Integer depth);

    /**
     * ��ѯ�û��������û�����
     * 
     * @param src Դ��
     * @param depth �����������
     * @return �����û�����������
     */
    public RelateUserContainer relateElementSize(User src, Integer depth);
}
