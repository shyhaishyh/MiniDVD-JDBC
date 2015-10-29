package org.zucc.view;

import java.sql.SQLException;
import java.util.Scanner;

import org.zucc.entity.Dvd;
import org.zucc.service.DvdManager;


public class DvdMenu {
	
	DvdManager dm = new DvdManager();
	Scanner sc = new Scanner(System.in);
	
	public void Menu() throws SQLException{
		System.out.println("��ӭʹ������DVD������");
		System.out.println("----------------------------------------");
		System.out.println("1������DVD");
		System.out.println("2���鿴DVD");
		System.out.println("3��ɾ��DVD");
		System.out.println("4�����DVD");
		System.out.println("5���黹DVD");
		System.out.println("6�����а�");
		System.out.println("7���˳�");
		System.out.println("----------------------------------------");
		System.out.print("��ѡ��");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			System.out.println("--->����DVD��\n");
			System.out.println("������Ҫ��ӵ�DVD���ƣ�");
			String add = sc.next();
			Dvd addDvd = new Dvd();
			addDvd.setName(add);
			dm.addDvd(addDvd);
			System.out.println("����DVD��" + add + "���ɹ���");
			break;

		case 2:
			System.out.println("--->�鿴DVD��\n");
			dm.search();
			break;
			
		case 3:
			System.out.println("--->ɾ��DVD��\n");
			System.out.println("������Ҫɾ����DVD��ţ�");
			int del = sc.nextInt();
			dm.delete(del);
			break;
			
		case 4:
			System.out.println("--->���DVD��\n");
			System.out.println("������Ҫ�����DVD��ţ�");
			int bor = sc.nextInt();
			dm.borrowDvd(bor);
			break;
			
		case 5:
			System.out.println("--->�黹DVD��\n");
			System.out.println("������Ҫ�黹��DVD��ţ�");
			int ret = sc.nextInt();
			dm.returnDvd(ret);
			break;
			
		case 6:
			dm.topDvd();
			break;
			
		case 7:
			System.out.println("ллʹ�ã�");
			break;
		}
			
		System.out.println("\n************************************");
		System.out.print("��0���أ�");
		int tuichu = sc.nextInt();
		if(tuichu == 0){
			Menu();
		}		
	}
}
