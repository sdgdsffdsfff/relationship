package com.relationship.analyze.service.compute;

import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.graph.Graph;
import com.relationship.analyze.domain.graph.RelateUserContainer;
import com.relationship.analyze.domain.result.RelationComputeResult;

/**
 * ͼ�������
 * 
 * @author yanjiu.lj
 */
public abstract class GraphCompute {

    /** ͼ��Ϣ */
    protected Graph graph;

    /** ��ʼ�� */
    protected User  source;

    /**
     * ���캯��
     * 
     * @param graph Ŀ��ͼ����
     */
    public GraphCompute(Graph graph) {
        this.graph = graph;
        this.source = null;
    }

    /**
     * ��ѯ���û��Ĺ�����ϵ
     * 
     * @param src Դ��
     * @param dst Ŀ���
     * @param depthLimit �����������
     * @return ��ϵ������
     */
    public abstract RelationComputeResult relationBetween(User src, User dst, Integer depthLimit);

    /**
     * ��ѯ�û��������û���Ϣ
     * 
     * @param src Դ��
     * @param depth �����������
     * @return �����û�����������
     */
    public abstract RelateUserContainer relateElements(User src, Integer depth);

    /**
     * ��ѯ�û��������û�����
     * 
     * @param src Դ��
     * @param depth �����������
     * @return �����û�����������
     */
    public abstract RelateUserContainer relateElementSize(User src, Integer depth);
}