package com.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.services.BookService;
import com.models.BookModel;
import com.interfaces.Listable;

@RestController
public class BookController {
	private final BookService service;
	
	public BookController(BookService service) {
		this.service = service;
	}
	
	@GetMapping(value="/books")
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return service.findAll();
	}
	
	@GetMapping(value="/books/{id}")
	public ResponseEntity<Listable> findById(@PathVariable("id") int id) throws SQLException{
		return service.findById(id);	
	}
	
	@DeleteMapping(value="/books/{id}")
	public ResponseEntity<String> remove(@PathVariable("id") int id) throws SQLException{
		return service.remove(id);	
	}
	
	@PostMapping(value="/books")
	public ResponseEntity<String> insert(@RequestBody BookModel book) throws SQLException{
		return service.insert(book);
	}
	
	@PutMapping(value="/books/{id}")
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody BookModel book) throws SQLException{
		return service.update(id, book);
	}
}
