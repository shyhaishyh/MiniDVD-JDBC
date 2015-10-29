package org.zucc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.zucc.entity.Dvd;

public class DvdDao {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void close() throws SQLException{
		if(rs != null){
			rs.close();
		}
		if(pstmt != null){
			pstmt.close();
		}
		if(conn != null){
			conn.close();
		}		
	}
	
	public DvdDao(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("无法找到驱动类！");
			e.printStackTrace();
		}
	}
	
	public void addDvd(Dvd dvd) throws SQLException{
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minidvd", "root", "root");
			String sql = "insert into dvd(name) values(?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dvd.getName());
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println("插入失败！");
			e.printStackTrace();
		} finally{
			close();
		}
	}
	
	public void delete(int id) throws SQLException{
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minidvd", "root", "root");
			String sql = "delete from dvd where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println("内部错误！删除失败！");
			e.printStackTrace();
		} finally{
			close();
		}		
	}
	
		public void update(Dvd dvd) throws SQLException{
		java.sql.Date borDate = null;
		java.sql.Date reDate = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minidvd", "root", "root");
			String sql = "update dvd set name = ?, state = ?, borrowDvd = ?, returnDvd = ?,borrowcount=? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dvd.getName());
			pstmt.setInt(2, dvd.getState());
			borDate = dvd.getBorrowDvd() == null ? null : new Date(dvd.getBorrowDvd().getTime());
			pstmt.setDate(3, borDate);
			reDate = dvd.getReturnDvd() == null ? null : new Date(dvd.getReturnDvd().getTime());
			pstmt.setDate(4, reDate);
			pstmt.setInt(5, dvd.getBorrowCount());
			pstmt.setInt(6, dvd.getId());
			pstmt.execute();
//			stmt.execute(sql.toString());	//execute执行给定的 SQL语句，该语句可能返回多个结果。
//			rs = stmt.executeQuery(sql); 	//executeQuery执行给定的 SQL语句，该语句返回单个 ResultSet 对象。
		} catch (SQLException e) {
			System.out.println("修改失败！");
			e.printStackTrace();
		} finally{
			close();
		}	
	}
	
		public Dvd findId(int id) throws SQLException{
		Dvd dvd = new Dvd();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minidvd", "root", "root");
			String sql = "select * from dvd where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				dvd.setId(rs.getInt("id"));
				dvd.setName(rs.getString("name"));
				dvd.setState(rs.getInt("state"));
				dvd.setBorrowDvd(rs.getDate("borrowDvd"));
				dvd.setReturnDvd(rs.getDate("returnDvd"));
				dvd.setBorrowCount(rs.getInt("borrowcount"));
			}
		} catch (SQLException e) {
			System.out.println("查找失败！");
			e.printStackTrace();
		} finally{
			close();
		}
		return dvd;
	}
		
		public Dvd findName(String name) throws SQLException{
			Dvd dvd = new Dvd();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minidvd", "root", "root");
			String sql = "select * from dvd where name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			List<Dvd> list = new ArrayList<Dvd>();
			while(rs.next()){
				dvd.setId(rs.getInt("id"));
				dvd.setName(rs.getString("name"));
				dvd.setState(rs.getInt("state"));
				dvd.setBorrowDvd(rs.getDate("borrowDvd"));
				dvd.setReturnDvd(rs.getDate("returnDvd"));
				dvd.setBorrowCount(rs.getInt("borrowcount"));
				list.add(dvd);
			}
		} catch (SQLException e) {
			System.out.println("查找失败！");
			e.printStackTrace();
		} finally{
			close();
		}
		return dvd;
	}
	
		public List<Dvd> findAll() throws SQLException{
			List<Dvd> list = new ArrayList<Dvd>();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minidvd", "root", "root");
			String sql = "select * from dvd";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Dvd dvd = new Dvd();
				dvd.setId(rs.getInt("id"));
				dvd.setName(rs.getString("name"));
				dvd.setState(rs.getInt("state"));
				dvd.setBorrowDvd(rs.getDate("borrowDvd"));
				dvd.setReturnDvd(rs.getDate("returnDvd"));
				dvd.setBorrowCount(rs.getInt("borrowcount"));
				list.add(dvd);
			}
			
		} catch (SQLException e) {
			System.out.println("查找失败！");
			e.printStackTrace();
		} finally{
			close();
		}
		return list;	
	}
}
