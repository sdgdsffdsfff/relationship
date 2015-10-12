package com.relationship.analyze.service.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.graph.RelateUserContainer;
import com.relationship.analyze.domain.result.RelationComputeResult;
import com.relationship.analyze.service.compute.ComputeService;
import com.relationship.analyze.service.util.RelationshipConstants;

/**
 * 带缓存的计算服务
 * 
 * @author yanjiu.lj
 */
public class ComputeServiceWithCache implements ComputeService {
    /** 计算结果缓存MAP(用于查询) */
    private volatile Map<String, CacheResult> resultCacheMap = new HashMap<String, CacheResult>();

    /** 已排序缓存列表用于排序，维护在内存中，提高排序效率) */
    private List<CacheResult>                 cacheRankList  = new ArrayList<CacheResult>();

    /**  计算服务   */
    private ComputeService                    computeService;

    /** 缓存大小 */
    private static final int                  CACHE_SIZE     = 100;

    /** 数量1 */
    private static final int                  ONE            = 1;

    @Override
    public RelationComputeResult relationBetween(User src, User dst, Integer depthLimit) {
        String cacheKey = composeCacheKey(src.getUserId(), dst.getUserId(),
            String.valueOf(depthLimit));

        CacheResult cacheResult = resultCacheMap.get(cacheKey);

        if (null == cacheResult) {
            RelationComputeResult computeResult = computeService.relationBetween(src, dst,
                depthLimit);

            cacheResult = new CacheResult(computeResult);
            addComputeResultToCache(cacheResult);
        }

        return (RelationComputeResult) cacheResult.getCacheValue();
    }

    @Override
    public RelateUserContainer relateElements(User src, Integer depth) {
        String cacheKey = composeCacheKey(src.getUserId(), String.valueOf(depth));

        CacheResult cacheResult = resultCacheMap.get(cacheKey);

        if (null == cacheResult) {
            RelateUserContainer computeResult = computeService.relateElements(src, depth);

            cacheResult = new CacheResult(computeResult);
            addComputeResultToCache(cacheResult);
        }

        return (RelateUserContainer) cacheResult.getCacheValue();
    }

    @Override
    public RelateUserContainer relateElementSize(User src, Integer depth) {
        String cacheKey = composeCacheKey(src.getUserId(), String.valueOf(depth));

        CacheResult cacheResult = resultCacheMap.get(cacheKey);

        if (null == cacheResult) {
            RelateUserContainer computeResult = computeService.relateElementSize(src, depth);

            cacheResult = new CacheResult(computeResult);
            addComputeResultToCache(cacheResult);
        }

        return (RelateUserContainer) cacheResult.getCacheValue();
    }

    /**
     * 构造缓存key
     * 
     * @param values 入参
     * @return 拼接值
     */
    private String composeCacheKey(String... values) {
        StringBuilder builder = new StringBuilder();
        for (String value : values) {
            builder.append(value);
            builder.append(RelationshipConstants.SEP);
        }
        return builder.toString();
    }

    /**
     * 添加缓存并对缓存按照访问次数和最近访问时间排序
     * 
     * @param result 当前计算结果
     */
    public synchronized void addComputeResultToCache(CacheResult result) {
        // 添加缓存
        List<CacheResult> cacheRankList = composeCacheRankList(result);

        // 按照访问次数和最近访问时间对缓存排序        
        sortCache(cacheRankList);

        resultCacheMap.put(result.getCacheKey(), result);

        if (cacheRankList.size() >= CACHE_SIZE) {
            CacheResult lastRankResult = cacheRankList.get(cacheRankList.size() - 1);
            resultCacheMap.remove(lastRankResult.getCacheKey());
        }
    }

    /**
     * 添加缓存
     * 
     * @param result 当前计算结果
     * @return cacheRankList 待排序缓存列表
     */
    private List<CacheResult> composeCacheRankList(CacheResult result) {
        result.setVisitCnt(ONE);
        result.setVisitTime(new Date());

        CacheResult cachedResult = resultCacheMap.get(result.getCacheKey());

        if (null != cachedResult) {
            Integer currentVisitCnt = result.getVisitCnt() + ONE;
            result.setVisitCnt(currentVisitCnt);

            for (CacheResult tempResult : cacheRankList) {
                if (tempResult.getCacheKey().equals(result.getCacheKey())) {
                    tempResult.setVisitCnt(currentVisitCnt);
                }
            }
        } else {
            cacheRankList.add(result);
        }

        return cacheRankList;
    }

    /**
     * 按照访问次数和最近访问时间对缓存排序
     * 
     * @param cacheRankList 待排序缓存列表
     */
    private void sortCache(List<CacheResult> cacheRankList) {
        Collections.sort(cacheRankList, new Comparator<CacheResult>() {
            @Override
            public int compare(CacheResult left, CacheResult right) {
                return left.getVisitTime().after(right.getVisitTime()) ? -1 : 1;
            }
        });

        Collections.sort(cacheRankList, new Comparator<CacheResult>() {
            @Override
            public int compare(CacheResult left, CacheResult right) {
                return left.getVisitCnt() - right.getVisitCnt();
            }
        });
    }

    /**
     * 缓存
     * @author yanjiu.lj
     */
    private class CacheResult {
        /** 计算结果缓存key */
        private String  cacheKey;
        /** 计算结果访问次数 */
        private Integer visitCnt;
        /** 计算结果最近访问时间  */
        private Date    visitTime;
        /** 关系计算结果*/
        private Object  cacheValue;

        /**
         * @param computeResult  关系计算结果
         */
        public CacheResult(Object computeResult) {
            this.cacheValue = computeResult;
        }

        /**
         * Getter method for property <tt>visitTime</tt>.
         * 
         * @return property value of visitTime
         */
        public Date getVisitTime() {
            return visitTime;
        }

        /**
         * Setter method for property <tt>visitTime</tt>.
         * 
         * @param visitTime value to be assigned to property visitTime
         */
        public void setVisitTime(Date visitTime) {
            this.visitTime = visitTime;
        }

        /**
         * Getter method for property <tt>cacheKey</tt>.
         * 
         * @return property value of cacheKey
         */
        public String getCacheKey() {
            return cacheKey;
        }

        /**
         * Setter method for property <tt>cacheKey</tt>.
         * 
         * @param cacheKey value to be assigned to property cacheKey
         */
        public void setCacheKey(String cacheKey) {
            this.cacheKey = cacheKey;
        }

        /**
         * Getter method for property <tt>visitCnt</tt>.
         * 
         * @return property value of visitCnt
         */
        public Integer getVisitCnt() {
            return visitCnt;
        }

        /**
         * Setter method for property <tt>visitCnt</tt>.
         * 
         * @param visitCnt value to be assigned to property visitCnt
         */
        public void setVisitCnt(Integer visitCnt) {
            this.visitCnt = visitCnt;
        }

        /**
         * @return the cacheValue
         */
        public Object getCacheValue() {
            return cacheValue;
        }

        /**
         * @param cacheValue the cacheValue to set
         */
        public void setCacheValue(Object cacheValue) {
            this.cacheValue = cacheValue;
        }
    }

    /**
     * @param computeService the computeService to set
     */
    public void setComputeService(ComputeService computeService) {
        computeService = computeService;
    }
}
