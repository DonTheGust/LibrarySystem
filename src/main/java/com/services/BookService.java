package com.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dao.BookDAO;
import com.interfaces.Listable;
import com.models.BookModel;

@Service
public class BookService {
	BookDAO DAO = new BookDAO();
	
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return ResponseEntity.ok(DAO.getAll());
	}
	
	public ResponseEntity<Listable> findById(int id) throws SQLException{
		return ResponseEntity.ok(DAO.get(id));
	}
	
	public ResponseEntity<String> remove(int id) throws SQLException{
		return ResponseEntity.ok(DAO.remove(id));
	}
	
	public ResponseEntity<String> insert(BookModel book) throws SQLException{
		return ResponseEntity.ok(DAO.insert(book));
	}
	
	public ResponseEntity<String> update(int id, BookModel book) throws SQLException{
		return ResponseEntity.ok(DAO.update(id, book));
	}
}
