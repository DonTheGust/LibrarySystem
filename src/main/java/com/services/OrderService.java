package com.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dao.OrderDAO;
import com.models.OrderModel;
import com.interfaces.Listable;

@Service
public class OrderService {
	OrderDAO DAO = new OrderDAO();
	
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return ResponseEntity.ok(DAO.getAll());
	}
	
	public ResponseEntity<Listable> findById(int id) throws SQLException{
		return ResponseEntity.ok(DAO.get(id));
	}
	
	public ResponseEntity<String> remove(int id) throws SQLException{
		return ResponseEntity.ok(DAO.remove(id));
	}
	
	public ResponseEntity<String> insert(OrderModel order) throws SQLException{
		return ResponseEntity.ok(DAO.insert(order));
	}
	
	public ResponseEntity<String> update(int id, OrderModel order) throws SQLException{
		return ResponseEntity.ok(DAO.update(id, order));
	}
}
