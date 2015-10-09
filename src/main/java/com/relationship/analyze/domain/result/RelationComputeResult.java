package com.relationship.analyze.domain.result;

import java.util.List;

import com.relationship.analyze.domain.User;

/**
 * 关系分析结果
 * @author yanjiu.lj
 */
public class RelationComputeResult {

    /** 度数 */
    private int        depth     = Integer.MAX_VALUE;

    /** 路径条数 */
    private int        pathSize  = 0;

    /** 连通次数 */
    private int        connTimes = 0;

    /** 过渡朋友列表    */
    private List<User> adjFriendList;

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
     * @return the pathSize
     */
    public int getPathSize() {
        return pathSize;
    }

    /**
     * @param pathSize the pathSize to set
     */
    public void setPathSize(int pathSize) {
        this.pathSize = pathSize;
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

    /**
     * @return the adjFriendList
     */
    public List<User> getAdjFriendList() {
        return adjFriendList;
    }

    /**
     * @param adjFriendList the adjFriendList to set
     */
    public void setAdjFriendList(List<User> adjFriendList) {
        this.adjFriendList = adjFriendList;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RelationResult [depth=" + depth + ", pathSize=" + pathSize + ", connTimes="
               + connTimes + "]";
    }
}
