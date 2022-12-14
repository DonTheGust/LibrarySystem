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

import com.services.ClientService;
import com.models.ClientModel;
import com.interfaces.Listable;

@RestController
public class ClientController {
	private final ClientService service;
	
	public ClientController(ClientService service) {
		this.service = service;
	}
	
	@GetMapping(value="/clients")
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return service.findAll();
	}
	
	@GetMapping(value="/clients/{id}")
	public ResponseEntity<Listable> findById(@PathVariable("id") int id) throws SQLException{
		return service.findById(id);	
	}
	
	@DeleteMapping(value="/clients/{id}")
	public ResponseEntity<String> remove(@PathVariable("id") int id) throws SQLException{
		return service.remove(id);	
	}
	
	@PostMapping(value="/clients")
	public ResponseEntity<String> insert(@RequestBody ClientModel client) throws SQLException{
		return service.insert(client);
	}
	
	@PutMapping(value="/clients/{id}")
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody ClientModel client) throws SQLException{
		return service.update(id, client);
	}
}
