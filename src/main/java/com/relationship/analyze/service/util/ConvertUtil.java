package com.relationship.analyze.service.util;

import org.springframework.beans.BeanUtils;

import com.relationship.analyze.dal.dataobject.UserDO;
import com.relationship.analyze.domain.User;

/**
 * 模型转换工具类
 * 
 * @author yanjiu.lj
 */
public class ConvertUtil {

    /**
     * UserDO转为User
     * 
     * @param userDO userDO
     * @return user
     */
    public static User toUser(UserDO userDO) {
        User user = new User();
        BeanUtils.copyProperties(userDO, user);
        return user;
    }
}
