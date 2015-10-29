package org.zucc.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dvd implements Comparable<Dvd>{
	
	private int id;
	private String name;
	private int state;
	private Date borrowDvd;
	private Date returnDvd;
	private int borrowCount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getBorrowDvd() {
		return borrowDvd;
	}
	public void setBorrowDvd(Date borrowDvd) {
		this.borrowDvd = borrowDvd;
	}
	public Date getReturnDvd() {
		return returnDvd;
	}
	public void setReturnDvd(Date returnDvd) {
		this.returnDvd = returnDvd;
	}
	
	public int getBorrowCount() {
		return borrowCount;
	}
	public void setBorrowCount(int borrowCount) {
		this.borrowCount = borrowCount;
	}
	public Dvd() {
		super();
	}
	
	public Dvd(int id, String name, int state, Date borrowDvd, Date returnDvd) {
		super();
		this.id = id;
		this.name = name;
		this.state = state;
		this.borrowDvd = borrowDvd;
		this.returnDvd = returnDvd;
	}
	@Override
	public String toString() {
		String ss ;
		if(state == 1){
			ss="©и╫Х";
		}else{
			ss="ря╫ХЁЖ";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date1;
		String date2;
		if(borrowDvd != null){
			date1 = sdf.format(borrowDvd);
		}else{
			date1 = "";
		}
		if(returnDvd != null){
			date2 = sdf.format(returnDvd);
		}else{
			date2 = "";
		}
		return id + "\t" + ss + "\t" + name + "\t\t" + date1 + "\t\t" +  date2 + "\t" + borrowCount;
	}
	@Override
	public int compareTo(Dvd o) {
		
		return o.borrowCount - this.borrowCount;
	}
	
}
