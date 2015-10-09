package com.relationship.analyze;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ���Ի������ڼ���bean
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
