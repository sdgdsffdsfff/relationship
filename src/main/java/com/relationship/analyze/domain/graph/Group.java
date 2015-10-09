package com.relationship.analyze.domain.graph;

import java.util.List;

import com.relationship.analyze.domain.User;

/**
 * Ⱥ����Ϣ
 * 
 * @author yanjiu.lj
 */
public class Group {

    /** �û��б�����Ⱥ�����û�֮��Ĺ�����Ϣ */
    private List<User> users;

    /** Ⱥ��ID */
    private String     groupId;

    /**
     * ���캯��
     * ͨ��groupId���û��б���
     * 
     * @param groupId Ⱥ��ID
     * @param users �û��б�
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
