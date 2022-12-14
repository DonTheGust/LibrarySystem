package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.DBConn;
import com.models.ClientModel;
import com.interfaces.DAO;
import com.interfaces.Listable;

public class ClientDAO implements DAO{
	private Connection conn = new DBConn().getConnection();

	@Override
	public String insert(Listable item) {
		String sql="INSERT INTO client(id, name) VALUES ("
				+ ((ClientModel) item).getId() + ","				
				+ "'" + ((ClientModel) item).getName() + "'" + ");";
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            return "Insert Client - Sucessful";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Insert Client - Failed";
		}
		
	}

	@Override
	public Listable get(int id) throws SQLException {
		String sql="SELECT * from client WHERE id = " + id;
        
		PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
 
        } catch (SQLException e) {
        	System.out.println("Get Data Client - Failed");
			e.printStackTrace();
		}
		
        rs.next();
        ClientModel aux = new ClientModel();
    	aux.setId(rs.getInt("id"));
    	aux.setName(rs.getString("name"));
    	
		
		return aux;
	}

	@Override
	public List<Listable> getAll() throws SQLException {
		List<Listable> clients = new ArrayList<>();
		String sql="Select * from client";
        
		PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
 
        } catch (SQLException e) {
        	System.out.println("Get All Data Client - Failed");
			e.printStackTrace();
		}
		
        while(rs.next()) {
        	ClientModel aux = new ClientModel();
        	aux.setId(rs.getInt("id"));
        	aux.setName(rs.getString("name"));
        	
        	clients.add(aux);
        }
        
		return clients;
	}

	@Override
	public String remove(int id) {
		String sql="DELETE from client WHERE id = " + id;
        
		PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            
            return "Remove Client - Sucessful";
 
        } catch (SQLException e) {
			e.printStackTrace();
			
			return "Remove Client - Failed";
		}
	}

	@Override
	public String update(int id, Listable item) throws SQLException {
		String sql = "SELECT * FROM client WHERE id = " + id;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		String newName = ((ClientModel) item).getName() != null ? ((ClientModel) item).getName() : rs.getString("name");
		
		sql = "UPDATE client SET "
				+ "name = '" + newName + "' "				
			+ "WHERE id = " + id + ";";
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            return "Alterate Client - Sucessful";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Alterate Client - Failed";
		}
	}

}
