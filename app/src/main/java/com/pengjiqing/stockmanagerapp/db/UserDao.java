package com.pengjiqing.stockmanagerapp.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.pengjiqing.stockmanagerapp.utils.AppApplication;


public class UserDao implements IUserDao {

	@Override
	public boolean login(String username,String pwd){
		boolean falg=false;
		String sql="select * from user where username='"
				+username+"' and  password='"+pwd+"'";
		try {
			SQLiteDatabase db = AppApplication.helper.getReadableDatabase();
			Cursor cursor = db.rawQuery(sql, null);
			if(null!=cursor){
				if(cursor.moveToNext()){
					String name = cursor.getString(cursor.getColumnIndex("username"));
					if(name!=null || !"".equals(name)){
						Log.i("LOG","登录成功");
						return true;
					}
				}
			}
		} catch (Exception e) {
			Log.i("LOG","登录失败！");
		}
		return falg;
	}

	@Override
	public boolean isUserName(String username){
		boolean falg=false;
		String sql="select * from user where username='"
				+username+"'";
		try {
			SQLiteDatabase db = AppApplication.helper.getReadableDatabase();
			Cursor cursor = db.rawQuery(sql, null);
			if(null!=cursor){
				if(cursor.moveToNext()){
					String name = cursor.getString(cursor.getColumnIndex("username"));
					if(name!=null || !"".equals(name)){
						Log.i("LOG","有重复的用户！");
						return true;
					}
				}
			}
		} catch (Exception e) {
			Log.i("LOG","查询失败！");
		}
		return falg;
	}


	@Override
	public long addUser(String username,String pwd){
		long result=0;
		SQLiteDatabase db = AppApplication.helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("username", username);
		values.put("password", pwd);
		try {
			result= db.insert("user", null, values);
		} catch (Exception e) {
			Log.i("REG", "注册失败");
		}
		db.close();
		return result;
	}
}
