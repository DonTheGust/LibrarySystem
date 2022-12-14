package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.DBConn;
import com.models.AuthorModel;
import com.interfaces.DAO;
import com.interfaces.Listable;

public class AuthorDAO implements DAO{
	private Connection conn = new DBConn().getConnection();

	@Override
	public String insert(Listable item) {
		String sql="INSERT INTO author(id, name) VALUES ("
				+ ((AuthorModel) item).getId() + ","				
				+ "'" + ((AuthorModel) item).getName() + "'" + ");";
        
		PreparedStatement ps = null; //Previne sql injection
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            return "Insert Author - Sucessful";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Insert Author - Failed";
		}
	}

	@Override
	public Listable get(int id) throws SQLException {
		String sql="SELECT * from author WHERE id = " + id;
        
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
        AuthorModel aux = new AuthorModel();
    	aux.setId(rs.getInt("id"));
    	aux.setName(rs.getString("name"));
    	
		
		return aux;
	}

	@Override
	public List<Listable> getAll() throws SQLException {
		List<Listable> authors = new ArrayList<>();
		String sql="Select * from author";
        
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
        	AuthorModel aux = new AuthorModel();
        	aux.setId(rs.getInt("id"));
        	aux.setName(rs.getString("name"));
        	
        	authors.add(aux);
        }
        
		return authors;
	}

	@Override
	public String remove(int id) {
		String sql="DELETE from author WHERE id = " + id;
        
		PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            
            return "Remove Author - Sucessful";
 
        } catch (SQLException e) {
			e.printStackTrace();
			
			return "Remove Author - Failed";
		}
	}

	@Override
	public String update(int id, Listable item) throws SQLException {
		String sql = "SELECT * FROM author WHERE id = " + id;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		String newName = ((AuthorModel) item).getName() != null ? ((AuthorModel) item).getName() : rs.getString("name");
		
		sql = "UPDATE author SET "
				+ "name = '" + newName + "' "				
			+ "WHERE id = " + id + ";";
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            return "Alterate Author - Sucessful";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Alterate Author - Failed";
		}
	}

}
