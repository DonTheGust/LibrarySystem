package com.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dao.PublisherDAO;
import com.models.PublisherModel;
import com.interfaces.Listable;

@Service
public class PublisherService {
	PublisherDAO DAO = new PublisherDAO();
	
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return ResponseEntity.ok(DAO.getAll());
	}
	
	public ResponseEntity<Listable> findById(int id) throws SQLException{
		return ResponseEntity.ok(DAO.get(id));
	}
	
	public ResponseEntity<String> remove(int id) throws SQLException{
		return ResponseEntity.ok(DAO.remove(id));
	}
	
	public ResponseEntity<String> insert(PublisherModel publisher) throws SQLException{
		return ResponseEntity.ok(DAO.insert(publisher));
	}
	
	public ResponseEntity<String> update(int id, PublisherModel publisher) throws SQLException{
		return ResponseEntity.ok(DAO.update(id, publisher));
	}
}
