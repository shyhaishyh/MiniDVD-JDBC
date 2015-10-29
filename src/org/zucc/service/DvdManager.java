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
		System.out.println("���\t״̬\t����\t\t�������\t\t�黹����\t\t�������");
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
			System.out.println("δ�ҵ���DVD��");
		}else if(borDvd.getState() == 1){
			borDvd.setBorrowDvd(date);
			borDvd.setReturnDvd(null);	
			borDvd.setBorrowCount(borDvd.getBorrowCount() + 1);
			borDvd.setState(0);
			dd.update(borDvd);
			System.out.println("��ϲ�����DVD��" + borDvd.getName() + "���ɹ���");
		}else{
			System.out.println("��DVD�ѽ�������ɽ裡");
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
			System.out.println("δ�ҵ���DVD��");
		}else if(delDvd.getState() == 1){
			dd.delete(id);
			System.out.println("ɾ��DVD��" + delDvd.getName() + "���ɹ���");
		}else{
			System.out.println("��DVD�ѽ��������ɾ����");
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
			System.out.println("δ�ҵ���DVD��");
		}else if(reDvd.getState() == 0){
			reDvd.setReturnDvd(date);
			long day = date.getTime() - reDvd.getBorrowDvd().getTime();
			long money = day/1000/60/60/24;
			reDvd.setBorrowCount(reDvd.getBorrowCount());
			reDvd.setState(1);
			reDvd.setBorrowDvd(null);
			dd.update(reDvd);
			System.out.println("��ϲ���黹DVD��" + reDvd.getName() + "���ɹ���");
			System.out.println("�����" + money + "�죬��֧����" + money + "Ԫ��");
		}else{
			System.out.println("��������");
		}
	}
	
	public void topDvd() throws SQLException {
		System.out.println("���\t״̬\t����\t\t�������\t\t�黹����\t\t�������");
		List<Dvd> list = dd.findAll();
		Collections.sort(list);
		for (Dvd dvd : list) {
			System.out.println(dvd);
		}
	}

}
