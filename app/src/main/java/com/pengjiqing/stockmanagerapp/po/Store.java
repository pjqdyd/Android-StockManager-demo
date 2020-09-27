package com.pengjiqing.stockmanagerapp.po;
/**
 * 入库出库对象
 * @author fujiansheng
 */
public class Store {

	private int sid;
	private String sname;
	private String scode;
	private String status;
	private int scount;
	private long stime;
	
	public Store() {
	}

	public Store(int sid, String sname, String scode, String status,
			int scount, long stime) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.scode = scode;
		this.status = status;
		this.scount = scount;
		this.stime = stime;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getScode() {
		return scode;
	}

	public void setScode(String scode) {
		this.scode = scode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getScount() {
		return scount;
	}

	public void setScount(int scount) {
		this.scount = scount;
	}

	public long getStime() {
		return stime;
	}

	public void setStime(long stime) {
		this.stime = stime;
	}

	@Override
	public String toString() {
		return "Store [sid=" + sid + ", sname=" + sname + ", scode=" + scode
				+ ", status=" + status + ", scount=" + scount + ", stime="
				+ stime + "]";
	}
	
}
