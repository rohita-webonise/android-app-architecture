
package com.weboniselab.android.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.weboniselab.android.data.local.db.dao.UserDao;
import com.weboniselab.android.data.local.db.table.User;



@Database(entities = {User.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}
