
package com.weboniselab.android.di.module;

import android.app.Application;
import androidx.room.Room;
import android.content.Context;

import com.weboniselab.android.BuildConfig;
import com.weboniselab.android.R;
import com.weboniselab.android.data.AppDataManager;
import com.weboniselab.android.data.DataManager;
import com.weboniselab.android.data.local.db.AppDatabase;
import com.weboniselab.android.data.local.db.AppDbHelper;
import com.weboniselab.android.data.local.db.DbHelper;
import com.weboniselab.android.data.local.prefs.AppPreferencesHelper;
import com.weboniselab.android.data.local.prefs.PreferencesHelper;
import com.weboniselab.android.data.remote.ApiHelper;
import com.weboniselab.android.data.remote.ApiService;
import com.weboniselab.android.di.ApiInfo;
import com.weboniselab.android.di.DatabaseInfo;
import com.weboniselab.android.di.PreferenceInfo;
import com.weboniselab.android.misc.AppLocationManager;
import com.weboniselab.android.utils.app.AppConstants;
import com.weboniselab.android.utils.rx.AppSchedulerProvider;
import com.weboniselab.android.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }


    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    AppLocationManager provideLocationManager(Context context,AppPreferencesHelper appPreferencesHelper) {
        return new AppLocationManager(context,appPreferencesHelper);
    }


    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/gotham_book.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }


    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }


    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public ApiHelper providesNetworkService(
            Retrofit retrofit) {
        return retrofit.create(ApiHelper.class);
    }


    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit,ApiHelper apiHelper){
        return new ApiService(retrofit,apiHelper);
    }


}
