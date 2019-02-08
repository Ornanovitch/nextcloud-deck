package it.niedermann.nextcloud.deck.persistence.sync.adapters.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import it.niedermann.nextcloud.deck.model.User;

@Dao
public interface UserDao extends GenericDao<User> {

    @Query("SELECT * FROM user WHERE accountId = :accountId")
    LiveData<List<User>> getUsersForAccount(final long accountId);

    @Query("SELECT * FROM user WHERE accountId = :accountId and id = :remoteId")
    LiveData<User> getUsersByRemoteId(final long accountId, final long remoteId);

    @Query("SELECT * FROM user WHERE accountId = :accountId and id = :remoteId")
    User getUserByRemoteIdDirectly(final long accountId, final long remoteId);

    @Query("SELECT * FROM user WHERE accountId = :accountId and uid = :uid")
    User getUserByUidDirectly(final long accountId, final String uid);

    @Query("SELECT * FROM user WHERE localId IN (:assignedUserIDs)")
    List<User> getUsersById(List<Long> assignedUserIDs);
}