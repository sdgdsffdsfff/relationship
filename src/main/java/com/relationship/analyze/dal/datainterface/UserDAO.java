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
     * 插入
     * 
     * @param userDO userDO
     */
    public void insert(UserDO userDO);

    /**
     * 更新
     * 
     * @param userDO userDO
     */
    public void update(UserDO userDO);

    /**
     * 删除
     * @param userDO userDO
     */
    public void delete(UserDO userDO);

    /**
     * 通过userId查询User
     * 
     * @param userId 用户ID
     * @return UserDO 用户模型
     */
    public UserDO queryById(String userId);

    /**
     * 通过userId锁定User
     * 
     * @param userId 用户ID
     * @return UserDO 用户模型
     */
    public UserDO lockById(String userId);

    /**
     * 通过groupId查询User
     * 
     * @param groupId user所属群组ID
     * @return 群组内user
     */
    public List<UserDO> queryByGroupId(String groupId);
}
