package com.relationship.analyze.service.relation.manage;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.relationship.analyze.dal.datainterface.UserDAO;
import com.relationship.analyze.dal.dataobject.UserDO;
import com.relationship.analyze.domain.User;
import com.relationship.analyze.domain.result.RelationManageResult;
import com.relationship.analyze.service.template.ServiceCallback;
import com.relationship.analyze.service.template.ServiceProcessTemplate;

/**
 * RelationManagerImpl
 * 
 * @author yanjiu.lj
 */
public class RelationManagerImpl extends ServiceProcessTemplate implements RelationManager {
    /**   userDAO  */
    private UserDAO             userDAO;

    /** transactionTemplate */
    private TransactionTemplate transactionTemplate;

    @Override
    public RelationManageResult addFriend(final User src, final User dst) {
        return (RelationManageResult) execute(new ServiceCallback() {
            @Override
            public Object run() {
                return transactionTemplate.execute(new TransactionCallback<RelationManageResult>() {
                    @Override
                    public RelationManageResult doInTransaction(TransactionStatus paramTransactionStatus) {
                        UserDO srcLocked = userDAO.lockById(src.getUserId());
                        String friendsId = srcLocked.getFriendsId();

                        if (!friendsId.contains(dst.getUserId())) {
                            userDAO.update(addFriendIdToUser(srcLocked, friendsId));

                            UserDO dstLocked = userDAO.lockById(dst.getUserId());
                            userDAO.update(addFriendIdToUser(dstLocked, srcLocked.getUserId()));
                        }

                        return new RelationManageResult(true);
                    }
                });
            }
        });
    }

    @Override
    public RelationManageResult deleteFriend(final User src, final User dst) {
        return (RelationManageResult) execute(new ServiceCallback() {
            @Override
            public Object run() {
                return transactionTemplate.execute(new TransactionCallback<RelationManageResult>() {
                    @Override
                    public RelationManageResult doInTransaction(TransactionStatus paramTransactionStatus) {
                        UserDO srcLocked = userDAO.lockById(src.getUserId());
                        String friendsId = srcLocked.getFriendsId();

                        if (friendsId.contains(dst.getUserId())) {
                            userDAO.update(delFriendIdFromUser(srcLocked, friendsId));

                            UserDO dstLocked = userDAO.lockById(dst.getUserId());
                            userDAO.update(delFriendIdFromUser(dstLocked, srcLocked.getUserId()));
                        }

                        return new RelationManageResult(true);
                    }
                });
            }
        });
    }

    /**
     * 增加朋友关系
     * 
     * @param user 用户
     * @param friendsId 朋友ID
     * @return 用户DO
     */
    private UserDO addFriendIdToUser(final UserDO user, String friendsId) {
        StringBuffer buffer = new StringBuffer(user.getFriendsId());

        buffer.append(",");
        buffer.append(friendsId);
        user.setFriendsId(buffer.toString());

        return user;
    }

    /**
     * 删除朋友关系
     * 
     * @param user 用户
     * @param friendsId 朋友ID
     * @return 用户DO
     */
    private UserDO delFriendIdFromUser(final UserDO user, String friendsId) {
        String[] frinendsId = user.getFriendsId().split(",");

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < frinendsId.length; i++) {
            if (!frinendsId[i].equals(friendsId)) {
                buffer.append(frinendsId[i]);
                if (i != frinendsId.length - 1) {
                    buffer.append(",");
                }
            }
        }

        return user;
    }

    /**
     * @param transactionTemplate the transactionTemplate to set
     */
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    /**
     * @param userDAO the userDAO to set
     */
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
