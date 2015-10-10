package com.relationship.analyze.service.compute;

import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.graph.Graph;

/**
 * ͼ�������
 * 
 * @author yanjiu.lj
 */
public abstract class GraphCompute implements ComputeService {

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
}