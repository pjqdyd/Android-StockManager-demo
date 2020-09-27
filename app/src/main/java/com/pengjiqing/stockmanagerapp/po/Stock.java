package com.pengjiqing.stockmanagerapp.po;
/**
 * 库存对象
 * @author fujiansheng
 */
public class Stock {
	private int stid;
	private String stname;
	private String stcode;
	private int stcount;
	public Stock() {
	}
	public Stock(int stid, String stname, String stcode, int stcount) {
		super();
		this.stid = stid;
		this.stname = stname;
		this.stcode = stcode;
		this.stcount = stcount;
	}
	public int getStid() {
		return stid;
	}
	public void setStid(int stid) {
		this.stid = stid;
	}
	public String getStname() {
		return stname;
	}
	public void setStname(String stname) {
		this.stname = stname;
	}
	public String getStcode() {
		return stcode;
	}
	public void setStcode(String stcode) {
		this.stcode = stcode;
	}
	public int getStcount() {
		return stcount;
	}
	public void setStcount(int stcount) {
		this.stcount = stcount;
	}
	@Override
	public String toString() {
		return "Stock [stid=" + stid + ", stname=" + stname + ", stcode="
				+ stcode + ", stcount=" + stcount + "]";
	}

}
