package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.DBConn;
import com.models.BookModel;
import com.interfaces.DAO;
import com.interfaces.Listable;

public class BookDAO implements DAO {
	private Connection conn = new DBConn().getConnection();

	@Override
	public String insert(Listable item) {
		String sql="INSERT INTO books(id, title, author, price, fkpuid, fkauid) VALUES ("
				+ ((BookModel) item).getId() + ","
				+ "'" + ((BookModel) item).getTitle() + "'" + ","
				+ "'" + ((BookModel) item).getAuthor() + "'" + ","
				+ ((BookModel) item).getPrice() + ","
				+ ((BookModel) item).getPuId() + ","
				+ ((BookModel) item).getAuId() + ");";
        
		PreparedStatement ps = null; //Previne sql injection
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            return "Insert Book - Sucessful";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Insert Book - Failed";
		}
		
	}

	@Override
	public Listable get(int id) throws SQLException {
		String sql="SELECT * from books WHERE id = " + id;
        
		PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
 
        } catch (SQLException e) {
        	System.out.println("Get Data Book - Failed");
			e.printStackTrace();
		}
		
        rs.next();
        BookModel aux = new BookModel();
    	aux.setId(rs.getInt("id"));
    	aux.setTitle(rs.getString("title"));
    	aux.setPrice(rs.getDouble("price"));
    	aux.setAuthor(rs.getString("author"));
    	aux.setPuId(rs.getInt("fkpuid"));
    	aux.setAuId(rs.getInt("fkauid"));
		
		return aux;
	}

	@Override
	public List<Listable> getAll() throws SQLException {
		List<Listable> books = new ArrayList<>();
		String sql="Select * from books";
        
		PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
 
        } catch (SQLException e) {
        	System.out.println("Get All Data Book - Failed");
			e.printStackTrace();
		}
		
        while(rs.next()) {
        	BookModel aux = new BookModel();
        	aux.setId(rs.getInt("id"));
        	aux.setTitle(rs.getString("title"));
        	aux.setPrice(rs.getDouble("price"));
        	aux.setAuthor(rs.getString("author"));
        	aux.setPuId(rs.getInt("fkpuid"));
        	aux.setAuId(rs.getInt("fkauid"));
        	
        	books.add(aux);
        }
        
		return books;
	}

	@Override
	public String remove(int id) {
		String sql="DELETE from books WHERE id = " + id;
        
		PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            
            return "Remove Book - Sucessful";
 
        } catch (SQLException e) {
			e.printStackTrace();
			
			return "Remove Book - Failed";
		}
		
	}

	@Override
	public String update(int id, Listable item) throws SQLException {
		String sql = "SELECT * FROM books WHERE id = " + id;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		String newTitle = ((BookModel) item).getTitle() != null ? ((BookModel) item).getTitle() : rs.getString("title");
		String newAuthor = ((BookModel) item).getAuthor() != null ? ((BookModel) item).getAuthor() : rs.getString("author");
		Double newPrice = ((BookModel) item).getPrice() != null ? ((BookModel) item).getPrice() : rs.getDouble("price");
		int newPuId = ((BookModel) item).getPuId() != 0 ? ((BookModel) item).getPuId() : rs.getInt("fkpuid");
		int newAuId = ((BookModel) item).getAuId() != 0 ? ((BookModel) item).getAuId() : rs.getInt("fkauid");
		
		sql = "UPDATE books SET "
				+ "title= '" + newTitle + "',"
				+ "author= '" + newAuthor + "',"
				+ "price=" + newPrice + ","
				+ "fkpuid=" + newPuId + ","
				+ "fkauid=" + newAuId
			+ " WHERE id = " + id + ";";
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            return "Alterate Book - Sucessful";
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Alterate Book - Failed";
		}
		
	}

}
