package com.relationship.analyze.dal;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.relationship.analyze.RelationTestBase;
import com.relationship.analyze.dal.datainterface.UserDAO;
import com.relationship.analyze.dal.dataobject.UserDO;

/**
 * UserDAOTest
 * 
 * @author yanjiu.lj
 */
public class UserDAOTest extends RelationTestBase {
    /**  userDAO   */
    private UserDAO userDAO;

    /**
     * init before test case
     */
    @Before
    public void init() {
        super.init();
        userDAO = (UserDAO) factory.getBean("userDAO");
    }

    /**
     * test qurey method
     */
    @Test
    public void testQueryById() {
        UserDO userDO = userDAO.queryById("1");

        System.out.print(userDO);

        Assert.assertEquals(userDO != null, true);
    }

    /**
     * test qurey method
     */
    @Test
    public void testQueryByGroupId() {
        List<UserDO> userDOs = userDAO.queryByGroupId("1");

        for (UserDO userDO : userDOs) {
            System.out.print(userDO);
        }

        Assert.assertEquals(userDOs != null, true);
    }

    /**
     * test insert method
     */
    @Test
    public void testInsert() {
        UserDO userDO = new UserDO();
        userDO.setUserId("id_4_test");

        userDAO.insert(userDO);
        userDO = userDAO.queryById("id_4_test");
        System.out.print(userDO);
        Assert.assertEquals(userDO != null, true);

        userDAO.delete(userDO);
        userDO = userDAO.queryById("id_4_test");
        Assert.assertEquals(userDO == null, true);
    }

    /**
     * test update method
     */
    @Test
    public void testUpdate() {
        UserDO userDO = new UserDO();
        userDO.setUserId("id_4_test");

        userDAO.insert(userDO);
        userDO = userDAO.queryById("id_4_test");
        System.out.print(userDO);
        Assert.assertEquals(userDO != null, true);

        userDO.setGroupId("group_4_test");
        userDAO.update(userDO);
        userDO = userDAO.queryById("id_4_test");
        Assert.assertEquals(userDO.getGroupId().equals("group_4_test"), true);

        userDAO.delete(userDO);
        userDO = userDAO.queryById("id_4_test");
        Assert.assertEquals(userDO == null, true);
    }
}
