package com.pengjiqing.stockmanagerapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{
	//数据库名称
	private final static String DBNAME = "ztkj";
	//表名
	private final static String TBUSER = "user";
	//数据库版本
	private final static int VERSION = 1;

	public DBHelper(Context context) {
		super(context, DBNAME, null, VERSION);//创建数据库
		Log.i("DB", "创建数据库");
	}

	//初始化，只会被执行一次，创建数据库的时候执行
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		//一般用于创建表，添加默认数据
		//编写sql
		String sql = "create table "+TBUSER+"(uid integer primary key autoincrement,"
				+ "username varchar unique,"
				+ "password varchar)";
		//执行sql
		arg0.execSQL(sql);

		//编写入库的sql
		String sql2 = "create table store(sid integer primary key autoincrement,"
				+ "sname varchar,"
				+ "scode varchar,"
				+ "scount int,"
				+ "status varchar,"
				+ "stime long)";
		//执行sql
		arg0.execSQL(sql2);

		//编写库存表sql
		String sql3 = "create table stock(stid integer primary key autoincrement,"
				+ "stname varchar,"
				+ "stcode varchar unique,"
				+ "stcount int)";
		//执行sql
		arg0.execSQL(sql3);
	}

	//数据库版本升级的时候
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
