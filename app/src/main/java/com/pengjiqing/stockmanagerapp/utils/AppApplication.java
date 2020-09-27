package com.pengjiqing.stockmanagerapp.utils;

import com.pengjiqing.stockmanagerapp.db.DBHelper;
import android.app.Application;
import android.util.Log;

public class AppApplication extends Application{
	public static DBHelper helper;
	@Override
	public void onCreate() {
		super.onCreate(); 
		helper = new DBHelper(this);
		Log.i("APP", "启动项目...");
	}
}
