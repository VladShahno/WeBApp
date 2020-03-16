package webApp.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import webApp.beans.*;

public class DBUtils {
	
	public static UserData findUser(Connection conn,
			String userName,String password) throws SQLException {
		
		String sql = "Select a.User_Name, a.Password, a.Sex from User_Data a " //
                + " where a.User_Name = ? and a.password= ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		pstm.setString(2,password);
		ResultSet rs = pstm.executeQuery();
		
		if(rs.next()) {
			String sex = rs.getString("Sex");
			UserData user = new UserData();
			user.setUserName(userName);
			user.setPassword(password);
			user.setSex(sex);
			return user;
		}
		return null;
	}
	
	 public static UserData findUser(Connection conn, String userName) throws SQLException {
		 
	        String sql = "Select a.User_Name, a.Password, a.Sex from User_Data a "//
	                + " where a.User_Name = ? ";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, userName);
	 
	        ResultSet rs = pstm.executeQuery();
	 
	        if (rs.next()) {
	            String password = rs.getString("Password");
	            String gender = rs.getString("Sex");
	            UserData user = new UserData();
	            user.setUserName(userName);
	            user.setPassword(password);
	            user.setSex(gender);
	            return user;
	        }
	        return null;
	    }

	public static List<Lists> queryLists(Connection conn) throws SQLException {
		String sql = "Select a.listCode, a.listName, a.listType, a.listGeo";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		ResultSet rs = pstm.executeQuery();
		List<Lists> list = new ArrayList<Lists>();
		while(rs.next()) {
			String Code = rs.getString("listCode");
			String Name = rs.getString("listName");
			String Type = rs.getString("listType");
			String Geo = rs.getString("listGeo");
			Lists lst = new Lists();
			lst.setListCode(Code);
			lst.set(Name);
			lst.setListType(Type);
			lst.setListGeo(Geo);
			list.add(lst);
		}
		
		return list;
	}
	
	public static Lists findLists(Connection conn, String listCode) throws SQLException {
		String sql = "Select a.listCode, a.listName,a.listType,a.listGeo from list a where a.listCode?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, listCode);
		
		ResultSet rs = pstm.executeQuery();
		
		while(rs.next()) {
			String Code = rs.getString("listCode");
			String Name = rs.getString("listName");
			String Type = rs.getString("listType");
			String Geo = rs.getString("listGeo");
			Lists list = new Lists(Code, Name, Type, Geo);
			return list;
		}
		return null;
	}
	
	public static void updateLists(Connection conn, Lists list) throws SQLException {
		String sql = "Upddate list set Name = ?, Type = ?, Geo = ? where Code = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setString(1, list.getListCode());
		pstm.setString(2, list.getListName());
		pstm.setString(3, list.getListType());
		pstm.setString(4, list.getListGeo());
		
		pstm.executeUpdate();
	}
	
	public static void InsertLists(Connection conn, Lists list) throws SQLException {
		String sql = "Insert into Roser(Code, Name, Type, Geo) values (?, ?, ?, ?)";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, list.getListCode());
		pstm.setString(2,list.getListName());
		pstm.setString(3, list.getListType());
		pstm.setString(4, list.getListGeo());
		
		pstm.executeUpdate();
	}
	
	public static void deleteLists(Connection conn, String listCode) throws SQLException {
		String sql = "Delete From List where = Code = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1,listCode);
		
		pstm.executeUpdate();
	}
}
