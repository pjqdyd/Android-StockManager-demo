package com.pengjiqing.stockmanagerapp.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pengjiqing.stockmanagerapp.utils.AppApplication;

public class StoreDao {

	public  long addStore(String scode,String scount,String sname,String status){
		long result=0;
		try {
			ContentValues values = new ContentValues();
			values.put("scode", scode);
			values.put("scount", scount);
			values.put("sname", sname);
			values.put("status", status);
			values.put("stime", System.currentTimeMillis());
			SQLiteDatabase db= AppApplication.helper.getWritableDatabase();
			result = db.insert("store", null, values);
		} catch (Exception e) {
		}
		return result;
	}

	public  int delStore(String sid){
		int num=0;
		try {
			//获取数据库的操作对象
			SQLiteDatabase db = AppApplication.helper.getWritableDatabase();
			//第一种写法
			//编写删除的sql语句
			//String sql = "delete from store where sid="+sid;
			//执行sql语句
			//db.execSQL(sql);
			//第二种写法
			String [] whereArgs={sid};
			num = db.delete("store", "sid=?",whereArgs);
			//释放资源
			db.close();
		} catch (Exception e) {
		}
		return num;
	}

	public  List<Map<String, Object>> queryStore(String status){
		List<Map<String, Object>> dataList=new ArrayList<>();
		try {
			String sql="select * from store where status = '"+status+"'";
			query(dataList, sql);
		} catch (Exception e) {
		}
		return dataList;
	}

	public  void query(List<Map<String, Object>> dataList, String sql) {
		SQLiteDatabase db=AppApplication.helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		if(null!=cursor){
			while(cursor.moveToNext()){
				Map<String, Object> map=new HashMap<>();
				int sid=cursor.getInt(cursor.getColumnIndex("sid"));
				String sname=cursor.getString(cursor.getColumnIndex("sname"));
				String scode=cursor.getString(cursor.getColumnIndex("scode"));
				int scount=cursor.getInt(cursor.getColumnIndex("scount"));
				String st=cursor.getString(cursor.getColumnIndex("status"));
				long stime=cursor.getLong(cursor.getColumnIndex("stime"));
				map.put("sid", sid);
				map.put("sname", sname);
				map.put("scode", scode);
				map.put("scount", scount);
				map.put("status", st);
				map.put("stime", stime);
				dataList.add(map);
			}
			cursor.close();
		}//end while
		db.close();
	}

	public  List<Map<String, Object>> queryStoreByCode(String code){
		List<Map<String, Object>> dataList=new ArrayList<>();
		try {
			SQLiteDatabase db=AppApplication.helper.getReadableDatabase();
			String sql="select * from store where scode='"+code+"'";
			Cursor cursor = db.rawQuery(sql, null);
			if(null!=cursor){
				while(cursor.moveToNext()){
					Map<String, Object> map=new HashMap<>();
					int sid=cursor.getInt(cursor.getColumnIndex("sid"));
					String sname=cursor.getString(cursor.getColumnIndex("sname"));
					String scode=cursor.getString(cursor.getColumnIndex("scode"));
					int scount=cursor.getInt(cursor.getColumnIndex("scount"));
					String st=cursor.getString(cursor.getColumnIndex("status"));
					long stime=cursor.getLong(cursor.getColumnIndex("stime"));
					map.put("sid", sid);
					map.put("sname", sname);
					map.put("scode", scode);
					map.put("scount", scount);
					map.put("status", st);
					map.put("stime", stime);
					dataList.add(map);
				}
				cursor.close();
			}//end while
			db.close();
		} catch (Exception e) {
		}
		return dataList;
	}
}
