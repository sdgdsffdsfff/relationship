package com.relationship.analyze.domain.result;

/**
 * 关系关系结果
 * 
 * @author yanjiu.lj
 */
public class RelationManageResult {

    /**  isSuccess   */
    boolean isSuccess;

    /**  errMg   */
    String  errMg;

    /**
     * @param isSuccess
     */
    public RelationManageResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * @return the isSuccess
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * @param isSuccess the isSuccess to set
     */
    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * @return the errMg
     */
    public String getErrMg() {
        return errMg;
    }

    /**
     * @param errMg the errMg to set
     */
    public void setErrMg(String errMg) {
        this.errMg = errMg;
    }
}
