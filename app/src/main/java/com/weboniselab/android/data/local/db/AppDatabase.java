
package com.weboniselab.android.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.weboniselab.android.data.local.db.dao.UserDao;
import com.weboniselab.android.data.local.db.table.User;



@Database(entities = {User.class}, version = 2,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}
