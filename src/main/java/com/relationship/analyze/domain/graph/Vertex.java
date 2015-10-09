package com.relationship.analyze.domain.graph;

import com.relationship.analyze.domain.User;

/**
 * 图中顶点
 * 
 * @author yanjiu.lj
 * @version $Id: Vertex.java, v 0.1 2015-10-5 上午9:47:36 yanjiu.lj Exp $
 */
public class Vertex {
    /** 基本元素 */
    private User    user;

    /** 路径长度（度数） */
    private int     depth;

    /** 路径条数 */
    private int     pathCounter;

    /** 连通次数 */
    private int     connTimes;

    /** 是否已访问 */
    private boolean isVisited;

    /**
     * 构造函数
     * 
     * @param user 图中顶点对应user
     */
    public Vertex(User user) {
        this(user, -1, 0, 0, false);
    }

    /**
     * 构造函数
     * 
     * @param user 用户
     * @param depth 顶点深度
     * @param pathCounter 顶点到源点的路径条数
     * @param connTimes 顶点和源点的连通次数
     * @param isVisited 顶点是否已访问
     */
    public Vertex(User user, int depth, int pathCounter, int connTimes, boolean isVisited) {
        this.user = user;
        this.depth = depth;
        this.pathCounter = pathCounter;
        this.connTimes = connTimes;
        this.isVisited = isVisited;
    }

    /**
     * 重新设置顶点属性
     * 
     * @param depth 深度
     * @param pathCounter 路径数
     * @param connTimes 连通次数
     * @param isVisited 是否已被访问
     */
    public void reset(int depth, int pathCounter, int connTimes, boolean isVisited) {
        this.depth = depth;
        this.pathCounter = pathCounter;
        this.connTimes = connTimes;
        this.isVisited = isVisited;
    }

    /**
     * 判断source是否可以在depthLimit条件内关联到该顶点
     * 
     * @param depthLimit 深度限制
     * @return true：可以关联，false：不可以关联
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
