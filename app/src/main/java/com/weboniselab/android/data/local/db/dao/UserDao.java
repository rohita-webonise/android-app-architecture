
package com.weboniselab.android.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.weboniselab.android.data.local.db.table.User;

import java.util.List;


@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    List<User> loadAll();

    @Query("SELECT * FROM users WHERE id IN (:userIds)")
    List<User> loadAllByIds(List<Integer> userIds);

    @Query("SELECT * FROM users WHERE name LIKE :name LIMIT 1")
    User findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM users WHERE user_id = :userId LIMIT 1")
    List<User> loadByIds(String userId);
}
