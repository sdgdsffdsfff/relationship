package com.relationship.analyze.dal.datainterface;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.relationship.analyze.dal.dataobject.UserDO;

/**
 * UserDAOImpl
 * 
 * @author yanjiu.lj
 */
public class UserDAOImpl extends SqlMapClientDaoSupport implements UserDAO {

    @Override
    public void insert(UserDO userDO) {
        getSqlMapClientTemplate().insert("insert", userDO);
    }

    @Override
    public void update(UserDO userDO) {
        getSqlMapClientTemplate().update("update", userDO);

    }

    @Override
    public void delete(UserDO userDO) {
        getSqlMapClientTemplate().delete("delete", userDO);
    }

    @Override
    public UserDO queryById(String userId) {
        return (UserDO) getSqlMapClientTemplate().queryForObject("queryById", userId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserDO> queryByGroupId(String groupId) {
        return (List<UserDO>) getSqlMapClientTemplate().queryForList("queryByGroupId", groupId);
    }

    @Override
    public UserDO lockById(String userId) {
        return (UserDO) getSqlMapClientTemplate().queryForObject("lockById", userId);
    }
}
