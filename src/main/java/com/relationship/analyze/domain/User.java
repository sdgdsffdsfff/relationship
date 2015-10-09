package com.relationship.analyze.domain;

/**
 * �û�
 * 
 * @author yanjiu.lj
 */
public class User {
    /** �û�ID    */
    private String userId;

    /** �û�����Id�б�*/
    private String friendsId;

    /** �û�name    */
    private String name;

    /** �û�����Ⱥ��ID*/
    private String groupId;

    /** �û���������*/
    private String memo;

    /**
     * ���캯��
     */
    public User() {
    }

    /**
     * ���캯��
     * 
     * @param userId �û�ID
     * @param friendsId ����ID�б�
     */
    public User(String userId, String friendsId) {
        this.userId = userId;
        this.friendsId = friendsId;
    }

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

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "User [userId=" + userId + ", friendsId=" + friendsId + "]";
    }
}
