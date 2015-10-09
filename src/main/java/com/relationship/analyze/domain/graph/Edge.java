package com.relationship.analyze.domain.graph;

/**
 * 图中边
 * 
 * @author yanjiu.lj
 */
public class Edge {

    /** 另一端顶点。相对的，edge只能挂在vertex下  */
    private Vertex dst;

    /** 边的连通次数 */
    private int    connTimes;

    /**
     * 构造函数
     * 
     * @param dst 目标点
     * @param cnnTimes 连通次数
     */
    public Edge(Vertex dst, int cnnTimes) {
        this.dst = dst;
        this.connTimes = cnnTimes;
    }

    /**
     * @return the dst
     */
    public Vertex getDst() {
        return dst;
    }

    /**
     * @param dst the dst to set
     */
    public void setDst(Vertex dst) {
        this.dst = dst;
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
        result = prime * result + connTimes;
        result = prime * result + ((dst == null) ? 0 : dst.hashCode());
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
        Edge other = (Edge) obj;
        if (connTimes != other.connTimes)
            return false;
        if (dst == null) {
            if (other.dst != null)
                return false;
        } else if (!dst.equals(other.dst))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Edge [dst=" + dst + ", connTimes=" + connTimes + "]";
    }
}
