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
     * 模版执行器
     * 
     * @param callback 服务内容实现
     * @return Object 返回值
     */
    public Object execute(ServiceCallback callback) {
        RelationManageResult result = new RelationManageResult(false);
        try {
            logger.info("开始服务处理");
            result = (RelationManageResult) callback.run();

        } catch (Exception e) {
            logger.error("服务执行异常", e);
            result.setErrMg(e.getMessage());

        } finally {
            logger.info("服务处理结束");
        }

        return result;
    }
}
