package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.DBConn;
import com.models.PublisherModel;
import com.interfaces.DAO;
import com.interfaces.Listable;

public class PublisherDAO implements DAO{
	private Connection conn = new DBConn().getConnection();

	@Override
	public String insert(Listable item) {
		String sql="INSERT INTO publisher(id, name, address) VALUES ("
				+ ((PublisherModel) item).getId() + ","
				+ "'" + ((PublisherModel) item).getName() + "'" + ","
				+ "'" + ((PublisherModel) item).getAddress() + "'" + ");";
        
		PreparedStatement ps = null; //Prepara a query e evita sql injection
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            return "Insert Publisher - Sucessful";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Insert Publisher - Failed";
		}
		
	}

	@Override
	public Listable get(int id) throws SQLException {
		String sql="SELECT * from publisher WHERE id = " + id;
        
		PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
 
        } catch (SQLException e) {
        	System.out.println("Get Data Publisher - Failed");
			e.printStackTrace();
		}
		
        rs.next();
        PublisherModel aux = new PublisherModel();
    	aux.setId(rs.getInt("id"));
    	aux.setName(rs.getString("name"));
    	aux.setAddress(rs.getString("address"));
		
		return aux;
	}

	@Override
	public List<Listable> getAll() throws SQLException {
		List<Listable> publishers = new ArrayList<>();
		String sql="Select * from publisher";
        
		PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
 
        } catch (SQLException e) {
        	System.out.println("Get All Data Publisher - Failed");
			e.printStackTrace();
		}
		
        while(rs.next()) {
        	PublisherModel aux = new PublisherModel();
        	aux.setId(rs.getInt("id"));
        	aux.setName(rs.getString("name"));
        	
        	publishers.add(aux);
        }
        
		return publishers;
	}

	@Override
	public String remove(int id) {
		String sql="DELETE from publisher WHERE id = " + id;
        
		PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            
            return "Remove Publisher - Sucessful";
 
        } catch (SQLException e) {
			e.printStackTrace();
			
			return "Remove Publisher - Failed";
		}
	}

	@Override
	public String update(int id, Listable item) throws SQLException {
		String sql = "SELECT * FROM publisher WHERE id = " + id;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		String newName = ((PublisherModel) item).getName() != null ? ((PublisherModel) item).getName() : rs.getString("name");
		String newAddress = ((PublisherModel) item).getAddress() != null ? ((PublisherModel) item).getAddress() : rs.getString("address");
		
		sql = "UPDATE publisher SET "
				+ "name = '" + newName + "', "				
				+ "address = '" + newAddress + "'"
			+ "WHERE id = " + id + ";";
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            return "Alterate Publisher - Sucessful";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Alterate Publisher - Failed";
		}
	}
}
