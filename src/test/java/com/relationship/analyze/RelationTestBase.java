package com.relationship.analyze;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试基类用于加载bean
 * 
 * @author yanjiu.lj
 */
public class RelationTestBase {

    /** factory    */
    protected ApplicationContext factory;

    /**   init  */
    protected void init() {
        factory = new ClassPathXmlApplicationContext("context/spring_context.xml");
    }
}
