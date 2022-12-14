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

import com.interfaces.Listable;
import com.models.AccountModel;
import com.services.AccountService;

@RestController
public class AccountController {
private final AccountService service;
	
	public AccountController(AccountService service) {
		this.service = service;
	}
	
	@GetMapping(value="/accounts")
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return service.findAll();
	}
	
	@GetMapping(value="/accounts/{id}")
	public ResponseEntity<Listable> findById(@PathVariable("id") int id) throws SQLException{
		return service.findById(id);	
	}
	
	@DeleteMapping(value="/accounts/{id}")
	public ResponseEntity<String> remove(@PathVariable("id") int id) throws SQLException{
		return service.remove(id);	
	}
	
	@PostMapping(value="/accounts")
	public ResponseEntity<String> insert(@RequestBody AccountModel account) throws SQLException{
		return service.insert(account);
	}
	
	@PutMapping(value="/accounts/{id}")
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody AccountModel account) throws SQLException{
		return service.update(id, account);
	}
}
