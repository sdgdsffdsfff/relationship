package com.relationship.analyze.domain.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.relationship.analyze.domain.User;

/**
 * �û���ϵ����������
 * 
 * @author yanjiu.lj
 */
public class RelateUserContainer {
    /** �������������Ѽ��ϵ�ӳ�� */
    private Map<Integer, Set<User>> depth2UserSet;

    /** ���ü��� */
    private Set<User>               userSet = null;

    /**
     * ���캯��
     */
    public RelateUserContainer() {
        depth2UserSet = new HashMap<Integer, Set<User>>();
    }

    /**
     * ���ͳ��ֵ
     */
    public void clear() {
        for (Set<User> userSet : depth2UserSet.values()) {
            userSet.clear();
        }
    }

    /**
     * ���һ��user���ض���������
     * 
     * @param depth ����
     * @param user �û�
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
     * ��ȡ�ض�������user����
     * 
     * @param depth ����
     * @return userSet �û�����
     */
    public Set<User> realteUsers(int depth) {
        return depth2UserSet.get(depth);
    }
}