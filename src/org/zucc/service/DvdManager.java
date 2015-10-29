package org.zucc.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.zucc.dao.DvdDao;
import org.zucc.entity.Dvd;


public class DvdManager {

	DvdDao dd = new DvdDao();
		
	public void addDvd(Dvd dvd) throws SQLException{
		dd.addDvd(dvd);
	}
	
	public void search() throws SQLException{
		System.out.println("序号\t状态\t名称\t\t借出日期\t\t归还日期\t\t借出次数");
		List<Dvd> list = dd.findAll();
		for (Dvd dvd : list) {
			System.out.println(dvd);
		}
	}
	
	public void borrowDvd(int id) throws SQLException{
		List<Dvd> list = dd.findAll();
		Date date = new Date();
		Dvd borDvd = null;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId() == id){
				borDvd = list.get(i);
				break;
			}
		}
		if(borDvd == null){
			System.out.println("未找到该DVD！");
		}else if(borDvd.getState() == 1){
			borDvd.setBorrowDvd(date);
			borDvd.setReturnDvd(null);	
			borDvd.setBorrowCount(borDvd.getBorrowCount() + 1);
			borDvd.setState(0);
			dd.update(borDvd);
			System.out.println("恭喜！借出DVD《" + borDvd.getName() + "》成功！");
		}else{
			System.out.println("该DVD已借出，不可借！");
		}		
	}
	
	public void delete(int id) throws SQLException{
		List<Dvd> list = dd.findAll();
		Dvd delDvd = null;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId() == id){
				delDvd = list.get(i);
				break;
			}
		}
		if(delDvd == null){
			System.out.println("未找到该DVD！");
		}else if(delDvd.getState() == 1){
			dd.delete(id);
			System.out.println("删除DVD《" + delDvd.getName() + "》成功！");
		}else{
			System.out.println("该DVD已借出，不可删除！");
		}	
	}
	
	public void returnDvd(int id) throws SQLException{
		List<Dvd> list = dd.findAll();
		Date date = new Date();
		Dvd reDvd = null;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId() == id){
				reDvd = list.get(i);
				break;
			}
		}
		if(reDvd == null){
			System.out.println("未找到该DVD！");
		}else if(reDvd.getState() == 0){
			reDvd.setReturnDvd(date);
			long day = date.getTime() - reDvd.getBorrowDvd().getTime();
			long money = day/1000/60/60/24;
			reDvd.setBorrowCount(reDvd.getBorrowCount());
			reDvd.setState(1);
			reDvd.setBorrowDvd(null);
			dd.update(reDvd);
			System.out.println("恭喜！归还DVD《" + reDvd.getName() + "》成功！");
			System.out.println("共借出" + money + "天，请支付：" + money + "元！");
		}else{
			System.out.println("输入有误！");
		}
	}
	
	public void topDvd() throws SQLException {
		System.out.println("序号\t状态\t名称\t\t借出日期\t\t归还日期\t\t借出次数");
		List<Dvd> list = dd.findAll();
		Collections.sort(list);
		for (Dvd dvd : list) {
			System.out.println(dvd);
		}
	}

}
