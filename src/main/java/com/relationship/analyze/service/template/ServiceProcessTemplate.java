package com.relationship.analyze.service.template;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

import com.relationship.analyze.domain.result.RelationManageResult;

/**
 * @author yanjiu.lj
 */
public class ServiceProcessTemplate {

    /**  logger   */
    private Log logger = LogFactoryImpl.getLog(ServiceProcessTemplate.class);

    /**
     * ģ��ִ����
     * 
     * @param callback ��������ʵ��
     * @return Object ����ֵ
     */
    public Object execute(ServiceCallback callback) {
        RelationManageResult result = new RelationManageResult(false);
        try {
            logger.info("��ʼ������");
            result = (RelationManageResult) callback.run();

        } catch (Exception e) {
            logger.error("����ִ���쳣", e);
            result.setErrMg(e.getMessage());

        } finally {
            logger.info("���������");
        }

        return result;
    }
}
