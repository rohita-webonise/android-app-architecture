
package com.weboniselab.android.data.local.db;

import com.weboniselab.android.data.local.db.table.User;

import java.util.List;

import io.reactivex.Observable;



public interface DbHelper {

    Observable<Boolean> insertUser(final User user);

    Observable<List<User>> getAllUsers();

    Observable<List<User>> getUserById(String userId);

}
