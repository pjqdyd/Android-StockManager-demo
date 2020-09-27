package com.pengjiqing.stockmanagerapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

	private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		getNowTime();
	}

	/**
	 * 获取当前时间字符串
	 * @return
	 */
	private static String getNowTime() {
		Date date = new Date();
		String nowTime = sf.format(date);
		System.out.println(nowTime);
		return nowTime;
	}
	/**
	 * 将时间戳转为时间字符串
	 * @param time
	 * @return
	 */
	public static String format(long time){
		return sf.format(time);
	}
}