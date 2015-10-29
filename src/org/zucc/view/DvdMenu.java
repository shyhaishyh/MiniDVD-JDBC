package org.zucc.view;

import java.sql.SQLException;
import java.util.Scanner;

import org.zucc.entity.Dvd;
import org.zucc.service.DvdManager;


public class DvdMenu {
	
	DvdManager dm = new DvdManager();
	Scanner sc = new Scanner(System.in);
	
	public void Menu() throws SQLException{
		System.out.println("欢迎使用迷你DVD管理器");
		System.out.println("----------------------------------------");
		System.out.println("1、新增DVD");
		System.out.println("2、查看DVD");
		System.out.println("3、删除DVD");
		System.out.println("4、借出DVD");
		System.out.println("5、归还DVD");
		System.out.println("6、排行榜");
		System.out.println("7、退出");
		System.out.println("----------------------------------------");
		System.out.print("请选择：");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			System.out.println("--->新增DVD：\n");
			System.out.println("请输入要添加的DVD名称：");
			String add = sc.next();
			Dvd addDvd = new Dvd();
			addDvd.setName(add);
			dm.addDvd(addDvd);
			System.out.println("新增DVD《" + add + "》成功！");
			break;

		case 2:
			System.out.println("--->查看DVD：\n");
			dm.search();
			break;
			
		case 3:
			System.out.println("--->删除DVD：\n");
			System.out.println("请输入要删除的DVD编号：");
			int del = sc.nextInt();
			dm.delete(del);
			break;
			
		case 4:
			System.out.println("--->借出DVD：\n");
			System.out.println("请输入要借出的DVD编号：");
			int bor = sc.nextInt();
			dm.borrowDvd(bor);
			break;
			
		case 5:
			System.out.println("--->归还DVD：\n");
			System.out.println("请输入要归还的DVD编号：");
			int ret = sc.nextInt();
			dm.returnDvd(ret);
			break;
			
		case 6:
			dm.topDvd();
			break;
			
		case 7:
			System.out.println("谢谢使用！");
			break;
		}
			
		System.out.println("\n************************************");
		System.out.print("按0返回：");
		int tuichu = sc.nextInt();
		if(tuichu == 0){
			Menu();
		}		
	}
}
