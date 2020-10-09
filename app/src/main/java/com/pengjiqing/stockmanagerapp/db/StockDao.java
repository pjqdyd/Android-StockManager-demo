package com.pengjiqing.stockmanagerapp.db;


import com.pengjiqing.stockmanagerapp.utils.AppApplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class StockDao {

	//判断是否存在该条码
	public  boolean isCheckedCode(String code){
		//获取数据库的操作对象
		SQLiteDatabase db = AppApplication.helper.getReadableDatabase();
		//编写sql
		String sql = "select * from stock where stcode='"+code+"'";
		//执行sql
		Cursor cursor = db.rawQuery(sql, null);
		if(cursor!=null){
			if(cursor.moveToNext()){
				//有记录
				cursor.close();
				return true;
			}
		}
		//释放资源
		db.close();
		return false;
	}

	//添加库存信息
	public  long addStock(String stcode,int stcount,String stname){
		//获取数据库的操作对象
		SQLiteDatabase db = AppApplication.helper.getWritableDatabase();
		//实例化ContentValues
		ContentValues values = new ContentValues();
		values.put("stcode", stcode);
		values.put("stname", stname);
		values.put("stcount", stcount);
		//执行添加操作
		long result = db.insert("stock", null, values);
		if(result>0){
			Log.e("TAG", "添加成功");
		}
		else{
			Log.e("TAG", "添加失败");
		}
		//释放资源
		db.close();
		return result;
	}

	//修改库存信息
	public  void updateStock(int count,String code){
		//获取数据库的操作对象
		SQLiteDatabase db = AppApplication.helper.getWritableDatabase();
		//编写sql
		String sql = "update stock set stcount=stcount+"+count
				+" where stcode='"+code+"'";
		//执行sql
		db.execSQL(sql);
		//释放资源
		db.close();
	}

	public  int getCountByCode(String code){
		int count=0;
		try {
			SQLiteDatabase db = AppApplication.helper.getReadableDatabase();
			// 编写sql语句
			String sql = "select * from stock where stcode='" + code + "'";
			// 执行sql
			Cursor cursor = db.rawQuery(sql, null);
			if (cursor != null) {
				if (cursor.moveToNext()) {
					// 获取库存数量
					count = cursor.getInt(cursor
							.getColumnIndex("stcount"));
				}
				cursor.close();
			}
			// 释放资源
			db.close();
		} catch (Exception e) {
		}
		return count;
	}

	public String getNameByCode(String code){
		String name="";
		try {
			SQLiteDatabase db = AppApplication.helper.getReadableDatabase();
			// 编写sql语句
			String sql = "select * from stock where stcode='" + code + "'";
			// 执行sql
			Cursor cursor = db.rawQuery(sql, null);
			if (cursor != null) {
				if (cursor.moveToNext()) {
					// 获取库存数量
					name = cursor.getString(cursor
							.getColumnIndex("stname"));
				}
				cursor.close();
			}
			// 释放资源
			db.close();
		} catch (Exception e) {
		}
		return name;
	}
}
