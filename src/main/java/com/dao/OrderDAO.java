package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.DBConn;
import com.models.OrderModel;
import com.models.BookModel;
import com.models.ClientModel;
import com.models.OrderItemModel;
import com.interfaces.DAO;
import com.interfaces.Listable;

public class OrderDAO implements DAO{
	Connection conn = new DBConn().getConnection();
	
	@Override
	public String insert(Listable item) throws SQLException {
		List<OrderItemModel> items = ((OrderModel) item).getItems();
		
		String sql="INSERT INTO orders(id, fkclid, address, payment) VALUES ("
				+ ((OrderModel) item).getId() + ","
				+ ((OrderModel) item).getClId() + ","
				+ "'" + ((OrderModel) item).getAddress() + "',"
				+ "'" + ((OrderModel) item).getPayment() + "'" + ");";
        
		PreparedStatement ps = null; //Previne sql injection
        
        try {
        	System.out.println(sql);
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            
        } catch (SQLException e) {
			e.printStackTrace();
			return "Insert Order (orders) - Failed";
		}
        
        try{
        	for(OrderItemModel i : items) {
            	sql="INSERT INTO order_items(fkorid, fkboid, quantity) VALUES ("
            			+ ((OrderModel) item).getId() + ","
        				+ i.getBookId() + ","
        				+ i.getQuantity() + ");";
            	
            	ps = conn.prepareStatement(sql);
                ps.executeQuery();
            }
        
        	return "Insert Order (order_items) - Sucessful";
        	
        }catch (Exception e) {
        	e.printStackTrace();
        	return "Insert Order (order_items) - Failed";
        }
        
        
	}

	@Override
	public Listable get(int id) throws SQLException {
		String sql = "SELECT * from orders WHERE id = " + id;
        
		PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsAux = null;
        
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
 
        } catch (SQLException e) {
        	System.out.println("Get Data Orders (orders) - Failed");
			e.printStackTrace();
		}
		
        rs.next();
        OrderModel aux = new OrderModel();
    	aux.setId(rs.getInt("id"));
    	aux.setDate(rs.getDate("date"));
    	aux.setClId(rs.getInt("fkclid"));
    	aux.setAddress(rs.getString("address"));
    	aux.setPayment(rs.getString("payment"));
    	
    	ClientModel cli = new ClientModel();
    	
    	sql = "SELECT * FROM client WHERE id = " + aux.getClId();
    	try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
 
        } catch (SQLException e) {
        	System.out.println("Get Data Orders (client) - Failed");
			e.printStackTrace();
		}
    	
    	rs.next();
    	cli.setId(rs.getInt("id"));
    	cli.setName(rs.getString("name"));
    	
    	aux.setClient(cli);
    	
    	sql = "SELECT * FROM order_items WHERE fkorid = " + id;
    	try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
 
        } catch (SQLException e) {
        	System.out.println("Get Data Orders (order_items) - Failed");
			e.printStackTrace();
		}
    	
    	while(rs.next()) {
    		OrderItemModel item = new OrderItemModel();
    		item.setBookId(rs.getInt("fkboid"));
    		item.setQuantity(rs.getInt("quantity"));
    		
    		
			sql = "SELECT * from books WHERE id = " + rs.getInt("fkboid");
	        
	        try {
	            ps = conn.prepareStatement(sql);
	            rsAux = ps.executeQuery();
	 
	        } catch (SQLException e) {
	        	System.out.println("Get Data Books (orders) - Failed");
				e.printStackTrace();
			}
	        
	        rsAux.next();
	        
	        BookModel books = new BookModel();
	        
	        books.setId(rsAux.getInt("id"));
	        books.setPuId(rsAux.getInt("fkpuid"));
	        books.setAuId(rsAux.getInt("fkauid"));
	        books.setTitle(rsAux.getString("title"));
	        books.setAuthor(rsAux.getString("author"));
	        books.setPrice(rsAux.getDouble("price"));
    	
    		item.setBookInfo(books);
    		
    		aux.addBook(item);
    	}
    	
		return aux;
	}

	@Override
	public List<Listable> getAll() throws SQLException {
		List<Listable> orders = new ArrayList<>();
		String sql="SELECT * FROM orders";
        
		PreparedStatement ps = null;
        ResultSet rs = null;
		ResultSet rsAux = null;
		ResultSet rsBooks = null;
        
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
 
        } catch (SQLException e) {
        	System.out.println("Get All Data Orders (orders) - Failed");
			e.printStackTrace();
		}
		
        while(rs.next()) {
        	OrderModel aux = new OrderModel();
        	aux.setId(rs.getInt("id"));
        	aux.setDate(rs.getDate("date"));
        	aux.setClId(rs.getInt("fkclid"));
        	aux.setAddress(rs.getString("address"));
        	aux.setPayment(rs.getString("payment"));

			ClientModel cli = new ClientModel();
    	
			sql = "SELECT * FROM client WHERE id = " + aux.getClId();
			try {
				ps = conn.prepareStatement(sql);
				rsAux = ps.executeQuery();
	
			} catch (SQLException e) {
				System.out.println("Get Data Orders (client) - Failed");
				e.printStackTrace();
			}
			
			rsAux.next();
			cli.setId(rsAux.getInt("id"));
			cli.setName(rsAux.getString("name"));
			
			aux.setClient(cli);
        	
        	sql = "SELECT * FROM order_items WHERE fkorid = " + aux.getId();
        	
        	try {
                ps = conn.prepareStatement(sql);
                rsAux = ps.executeQuery();
     
            } catch (SQLException e) {
            	System.out.println("Get All Data Orders (order_items) - Failed");
    			e.printStackTrace();
    		}
        	
        	while(rsAux.next()) {
        		OrderItemModel item = new OrderItemModel();
        		item.setBookId(rsAux.getInt("fkboid"));
        		item.setQuantity(rsAux.getInt("quantity"));

				sql = "SELECT * from books WHERE id = " + rsAux.getInt("fkboid");
	        
				try {
					ps = conn.prepareStatement(sql);
					rsBooks = ps.executeQuery();
		
				} catch (SQLException e) {
					System.out.println("Get Data Books (orders) - Failed");
					e.printStackTrace();
				}
				
				rsBooks.next();

				BookModel books = new BookModel();
	        
				books.setId(rsBooks.getInt("id"));
				books.setPuId(rsBooks.getInt("fkpuid"));
				books.setAuId(rsBooks.getInt("fkauid"));
				books.setTitle(rsBooks.getString("title"));
				books.setAuthor(rsBooks.getString("author"));
				books.setPrice(rsBooks.getDouble("price"));
			
				item.setBookInfo(books);
        		
        		aux.addBook(item);
        	}
        	
			//rs.next();
        	orders.add(aux);
        }
        
		return orders;
	}

	@Override
	public String remove(int id) throws SQLException {
		String sql = "DELETE from order_items WHERE fkorid = " + id;
        
		PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            
            System.out.println("Remove Order (order_items) - Sucessful");
        } catch (SQLException e) {
			e.printStackTrace();
			
			System.out.println("Remove Order (order_items) - Failed");
		}
        
        sql = "DELETE from orders WHERE id = " + id;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.executeQuery();

            return "Remove Order (orders) - Sucessful";
 
        } catch (SQLException e) {
			e.printStackTrace();
			
			return "Remove Order (orders) - Failed";
		}
	}

	@Override
	public String update(int id, Listable item) throws SQLException {
		List<OrderItemModel> items = ((OrderModel) item).getItems(); 

        String sql="UPDATE orders SET "
                + "address = " + "'" + ((OrderModel) item).getAddress() + "', "
                + "payment = " + "'" + ((OrderModel) item).getPayment() + "' "
                + "WHERE id = "+ id + ";";
        
            PreparedStatement preparedStat = null; 

        try {
            System.out.println(sql);
            preparedStat = conn.prepareStatement(sql); 
            preparedStat.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            return "Update Order (orders) - Failed";
        }

        try{
            for(OrderItemModel i : items) {

                sql="UPDATE order_items SET "
                        + "quantity = " + i.getQuantity() + " "
                        + "WHERE fkorid = " + ((OrderModel) item).getId() +";";
            }

            preparedStat = conn.prepareStatement(sql); 
            preparedStat.executeQuery();
            
            return "Update Order (order_items) - Sucessful";

        }catch (Exception e) {
            e.printStackTrace();
            return "Update Order (order_items) - Failed";
        }
	}
	
}
