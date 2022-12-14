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

import com.models.AuthorModel;
import com.interfaces.Listable;

@RestController
public class AuthorController {
private final com.services.AuthorService service;
	
	public AuthorController(com.services.AuthorService service) {
		this.service = service;
	}
	
	@GetMapping(value="/authors")
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return service.findAll();
	}
	
	@GetMapping(value="/authors/{id}")
	public ResponseEntity<Listable> findById(@PathVariable("id") int id) throws SQLException{
		return service.findById(id);	
	}
	
	@DeleteMapping(value="/authors/{id}")
	public ResponseEntity<String> remove(@PathVariable("id") int id) throws SQLException{
		return service.remove(id);	
	}
	
	@PostMapping(value="/authors")
	public ResponseEntity<String> insert(@RequestBody AuthorModel author) throws SQLException{
		return service.insert(author);
	}
	
	@PutMapping(value="/authors/{id}")
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody AuthorModel author) throws SQLException{
		return service.update(id, author);
	}
}
