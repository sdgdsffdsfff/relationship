package com.relationship.analyze.dal.dataobject;

import java.io.Serializable;

/**
 * UserDO
 * 
 * @author yanjiu.lj
 */
public class UserDO implements Serializable {
    /**   serialVersionUID  */
    private static final long serialVersionUID = -3275378188512857880L;

    /** �û�ID    */
    private String            userId;

    /** �û�����Id�б�*/
    private String            friendsId;

    /** �û�name    */
    private String            name;

    /** �û�����Ⱥ��ID*/
    private String            groupId;

    /** �û���������*/
    private String            memo;

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return the friendsId
     */
    public String getFriendsId() {
        return friendsId;
    }

    /**
     * @param friendsId the friendsId to set
     */
    public void setFriendsId(String friendsId) {
        this.friendsId = friendsId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserDO [userId=" + userId + ", friendsId=" + friendsId + ", name=" + name
               + ", groupId=" + groupId + ", memo=" + memo + "]";
    }
}
