package com.relationship.analyze.compute;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.relationship.analyze.RelationTestBase;
import com.relationship.analyze.dal.datainterface.UserDAO;
import com.relationship.analyze.dal.dataobject.UserDO;
import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.graph.Graph;
import com.relationship.analyze.domain.graph.Group;
import com.relationship.analyze.domain.result.RelationComputeResult;
import com.relationship.analyze.service.compute.GraphComputeBFSImpl;
import com.relationship.analyze.service.factory.GraphFactory;
import com.relationship.analyze.service.util.ConvertUtil;

/**
 * 测试类
 * 
 * @author yanjiu.lj
 */
public class GraphFactoryTest extends RelationTestBase {
    /** 测试用户列表    */
    private List<User> users = new ArrayList<User>();

    /**  userDAO   */
    private UserDAO    userDAO;

    /**
     * init before test case
     */
    @Before
    public void init() {
        super.init();
        userDAO = (UserDAO) factory.getBean("userDAO");
    }

    /**
     * 初始话测试群组信息
     * 
     * @return 群组信息
     */
    private Group initGroup() {
        //        graph for test
        //        
        //        users.add(new User("1", "2,3"));
        //        users.add(new User("2", "1,4,5"));
        //        users.add(new User("3", "1,5,6"));
        //        users.add(new User("4", "2"));
        //        users.add(new User("5", "2,3"));
        //        users.add(new User("6", "3"));

        List<UserDO> userDOs = userDAO.queryByGroupId("1");

        for (UserDO userDO : userDOs) {
            users.add(ConvertUtil.toUser(userDO));
        }

        return new Group("1", users);
    }

    /**
     * 测试图创建 
     */
    @Test
    public void testCreateGraph() {
        Graph graph = GraphFactory.createGraph(initGroup());
        Assert.assertEquals(graph.getVertexBy(new User("3", null)).getDepth(), -1);
    }

    /**
     * 测试图计算
     */
    @Test
    public void testGraphCompute() {
        GraphComputeBFSImpl graphComputeBFSImpl = new GraphComputeBFSImpl(
            GraphFactory.createGraph(initGroup()));

        Set<User> result = graphComputeBFSImpl.relateElements(new User("1", null), 3)
            .realteUsers(2);

        Assert.assertEquals(result.size(), 3);

        graphComputeBFSImpl = new GraphComputeBFSImpl(GraphFactory.createGraph(initGroup()));
        RelationComputeResult relation = graphComputeBFSImpl.relationBetween(new User("1", null),
            new User("5", null), 3);

        Assert.assertEquals(relation.getConnTimes(), 1);
        Assert.assertEquals(relation.getPathSize(), 2);
        Assert.assertEquals(relation.getAdjFriendList().size(), 3);
    }
}