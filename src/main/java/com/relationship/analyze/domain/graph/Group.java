package com.relationship.analyze.domain.graph;

import java.util.List;

import com.relationship.analyze.domain.User;

/**
 * 群组信息
 * 
 * @author yanjiu.lj
 */
public class Group {

    /** 用户列表，包含群组中用户之间的关联信息 */
    private List<User> users;

    /** 群组ID */
    private String     groupId;

    /**
     * 构造函数
     * 通过groupId和用户列表构造
     * 
     * @param groupId 群组ID
     * @param users 用户列表
     */
    public Group(String groupId, List<User> users) {
        this.users = users;
        this.groupId = groupId;
    }

    /**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * @return the groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
