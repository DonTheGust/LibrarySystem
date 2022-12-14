package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.DBConn;
import com.models.AccountModel;
import com.interfaces.DAO;
import com.interfaces.Listable;

public class AccountDAO implements DAO{
	private Connection conn = new DBConn().getConnection();

	@Override
	public String insert(Listable item) throws SQLException {
		String sql="INSERT INTO account(id, email, password) VALUES "
				+ "(" + ((AccountModel) item).getId() + ","
				+ "'" + ((AccountModel) item).getEmail() + "',"
				+ "'" + ((AccountModel) item).getPassword() + "');";
        
		PreparedStatement ps = null; //Previne sql injection
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            return "Insert Account - Sucessful";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Insert Account - Failed";
		}
	}

	@Override
	public Listable get(int id) throws SQLException {
		String sql="SELECT * FROM account WHERE id = " + id;
        
		PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
 
        } catch (SQLException e) {
        	System.out.println("Get Data Account - Failed");
			e.printStackTrace();
		}
		
        rs.next();
        AccountModel aux = new AccountModel();
    	aux.setId(rs.getInt("id"));
    	aux.setEmail(rs.getString("email"));
    	
		return aux;
	}

	@Override
	public List<Listable> getAll() throws SQLException {
		List<Listable> accounts = new ArrayList<>();
		String sql="SELECT * FROM account";
        
		PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
        } catch (SQLException e) {
        	System.out.println("Get All Data Account - Failed");
			e.printStackTrace();
		}
		
        while(rs.next()) {
        	AccountModel aux = new AccountModel();
        	aux.setId(rs.getInt("id"));
        	aux.setEmail(rs.getString("email"));
        	
        	accounts.add(aux);
        }
        
		return accounts;
	}

	@Override
	public String remove(int id) throws SQLException {
		String sql="DELETE FROM account WHERE id = " + id;
        
		PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            
            return "Remove Account - Sucessful";
 
        } catch (SQLException e) {
			e.printStackTrace();
			
			return "Remove Account - Failed";
		}
	}

	@Override
	public String update(int id, Listable item) throws SQLException {
		String sql = "SELECT * FROM account WHERE id = " + id;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		String newEmail = ((AccountModel) item).getEmail() != null ? ((AccountModel) item).getEmail() : rs.getString("email");
		String newPassword = ((AccountModel) item).getPassword() != null ? ((AccountModel) item).getPassword() : rs.getString("password");
		
		sql = "UPDATE account SET "
				+ "email = '" + newEmail + "',"
				+ "password = '" + newPassword+ "' "
			+ "WHERE id = " + id + ";";
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            return "Alterate Account - Sucessful";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Alterate Account - Failed";
		}
	}

}
