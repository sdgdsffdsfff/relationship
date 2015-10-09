package com.relationship.analyze.domain.graph;

import com.relationship.analyze.domain.User;

/**
 * ͼ�ж���
 * 
 * @author yanjiu.lj
 * @version $Id: Vertex.java, v 0.1 2015-10-5 ����9:47:36 yanjiu.lj Exp $
 */
public class Vertex {
    /** ����Ԫ�� */
    private User    user;

    /** ·�����ȣ������� */
    private int     depth;

    /** ·������ */
    private int     pathCounter;

    /** ��ͨ���� */
    private int     connTimes;

    /** �Ƿ��ѷ��� */
    private boolean isVisited;

    /**
     * ���캯��
     * 
     * @param user ͼ�ж����Ӧuser
     */
    public Vertex(User user) {
        this(user, -1, 0, 0, false);
    }

    /**
     * ���캯��
     * 
     * @param user �û�
     * @param depth �������
     * @param pathCounter ���㵽Դ���·������
     * @param connTimes �����Դ�����ͨ����
     * @param isVisited �����Ƿ��ѷ���
     */
    public Vertex(User user, int depth, int pathCounter, int connTimes, boolean isVisited) {
        this.user = user;
        this.depth = depth;
        this.pathCounter = pathCounter;
        this.connTimes = connTimes;
        this.isVisited = isVisited;
    }

    /**
     * �������ö�������
     * 
     * @param depth ���
     * @param pathCounter ·����
     * @param connTimes ��ͨ����
     * @param isVisited �Ƿ��ѱ�����
     */
    public void reset(int depth, int pathCounter, int connTimes, boolean isVisited) {
        this.depth = depth;
        this.pathCounter = pathCounter;
        this.connTimes = connTimes;
        this.isVisited = isVisited;
    }

    /**
     * �ж�source�Ƿ������depthLimit�����ڹ������ö���
     * 
     * @param depthLimit �������
     * @return true�����Թ�����false�������Թ���
     */
    public boolean isRelate(int depthLimit) {
        return (depth <= depthLimit) && (connTimes > 0);
    }

    /**
     * @return the isVisited
     */
    public boolean isVisited() {
        return isVisited;
    }

    /**
     * @param isVisited the isVisited to set
     */
    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * @param depth the depth to set
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * @return the pathCounter
     */
    public int getPathCounter() {
        return pathCounter;
    }

    /**
     * @param pathCounter the pathCounter to set
     */
    public void setPathCounter(int pathCounter) {
        this.pathCounter = pathCounter;
    }

    /**
     * @return the connTimes
     */
    public int getConnTimes() {
        return connTimes;
    }

    /**
     * @param connTimes the connTimes to set
     */
    public void setConnTimes(int connTimes) {
        this.connTimes = connTimes;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        Vertex other = (Vertex) obj;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Vertex [user=" + user + ", depth=" + depth + ", pathCounter=" + pathCounter
               + ", connTimes=" + connTimes + "]";
    }
}
