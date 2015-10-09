package com.relationship.analyze.domain.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.relationship.analyze.domain.User;

/**
 * 用户关系计算结果容器
 * 
 * @author yanjiu.lj
 */
public class RelateUserContainer {
    /** 关联度数到朋友集合的映射 */
    private Map<Integer, Set<User>> depth2UserSet;

    /** 公用集合 */
    private Set<User>               userSet = null;

    /**
     * 构造函数
     */
    public RelateUserContainer() {
        depth2UserSet = new HashMap<Integer, Set<User>>();
    }

    /**
     * 清除统计值
     */
    public void clear() {
        for (Set<User> userSet : depth2UserSet.values()) {
            userSet.clear();
        }
    }

    /**
     * 添加一个user到特定度数集合
     * 
     * @param depth 度数
     * @param user 用户
     */
    public void add(int depth, User user) {
        userSet = depth2UserSet.get(depth);
        if (userSet == null) {
            userSet = new HashSet<User>();
        }

        userSet.add(user);
        depth2UserSet.put(depth, userSet);
    }

    /**
     * 获取特定度数的user集合
     * 
     * @param depth 度数
     * @return userSet 用户集合
     */
    public Set<User> realteUsers(int depth) {
        return depth2UserSet.get(depth);
    }
}