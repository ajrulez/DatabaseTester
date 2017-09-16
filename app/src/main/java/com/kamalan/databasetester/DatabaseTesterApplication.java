package com.kamalan.databasetester;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.objectbox.BoxStore;

/**
 * Created by hesam on 9/5/16.
 *
 * Modified by: alifesoftware on 9/16/17
 * to include ObjectBox
 *
 */
public class DatabaseTesterApplication extends Application {
    // CoinTraderApplication Instance
    private static DatabaseTesterApplication dbTesterApplication;

    // BoxStore - ObjectBox Database
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();

        // Stetho must be used in DEBUG build only. However this is not production app.
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        // Init BoxStore - ObjectBox DB
        boxStore = MyObjectBox.builder()
                .androidContext(DatabaseTesterApplication.this)
                .name("dbtester")
                .build();
    }

    /**
     * Method to get CoinTraderApplication instance
     *
     * @return CoinTraderApplication instance
     */
    public static DatabaseTesterApplication getDatabaseTesterApplication() {
        return dbTesterApplication;
    }

    /**
     * Method to get BoxStore ObjectBox
     * database
     *
     * @return - BoxStore Database
     */
    public BoxStore getBoxStore() {
        return boxStore;
    }
}
