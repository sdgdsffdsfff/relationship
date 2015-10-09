package com.relationship.analyze.dal.datainterface;

import java.util.List;

import com.relationship.analyze.dal.dataobject.UserDO;

/**
 * UserDAO
 * 
 * @author yanjiu.lj
 */
public interface UserDAO {

    /**
     * ����
     * 
     * @param userDO userDO
     */
    public void insert(UserDO userDO);

    /**
     * ����
     * 
     * @param userDO userDO
     */
    public void update(UserDO userDO);

    /**
     * ɾ��
     * @param userDO userDO
     */
    public void delete(UserDO userDO);

    /**
     * ͨ��userId��ѯUser
     * 
     * @param userId �û�ID
     * @return UserDO �û�ģ��
     */
    public UserDO queryById(String userId);

    /**
     * ͨ��userId����User
     * 
     * @param userId �û�ID
     * @return UserDO �û�ģ��
     */
    public UserDO lockById(String userId);

    /**
     * ͨ��groupId��ѯUser
     * 
     * @param groupId user����Ⱥ��ID
     * @return Ⱥ����user
     */
    public List<UserDO> queryByGroupId(String groupId);
}
