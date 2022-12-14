package com.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dao.AuthorDAO;
import com.interfaces.Listable;
import com.models.AuthorModel;

@Service
public class AuthorService {
	AuthorDAO DAO = new AuthorDAO();
	
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return ResponseEntity.ok(DAO.getAll());
	}
	
	public ResponseEntity<Listable> findById(int id) throws SQLException{
		return ResponseEntity.ok(DAO.get(id));
	}
	
	public ResponseEntity<String> remove(int id) throws SQLException{
		return ResponseEntity.ok(DAO.remove(id));
	}
	
	public ResponseEntity<String> insert(AuthorModel author) throws SQLException{
		return ResponseEntity.ok(DAO.insert(author));
	}
	
	public ResponseEntity<String> update(int id, AuthorModel author) throws SQLException{
		return ResponseEntity.ok(DAO.update(id, author));
	}
}
