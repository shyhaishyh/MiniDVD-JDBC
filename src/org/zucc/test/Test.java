package org.zucc.test;

import java.sql.SQLException;

import org.zucc.view.DvdMenu;

public class Test {

	public static void main(String[] args) throws SQLException {
		
//		DvdDao dao = new DvdDao();
//		Dvd dvd = new Dvd();
//		dvd.setName("死神1111");
//		dao.addDvd(dvd);
		
//		dao.findAll();
//		dao.findName("罗马假日");
//		dao.findId(2);
		
//		dvd.setName("死神");
//		dvd.setState(0);
//		dvd.setId(2);
//		dao.update(dvd);

//		dao.delete(7);
		
		DvdMenu dmenu = new DvdMenu();
		dmenu.Menu();
//		DvdManager dm = new DvdManager();
//		dm.topDvd();
	}
}
