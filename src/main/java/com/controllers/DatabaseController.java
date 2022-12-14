package com.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utils.PopulateDB;

@RestController
public class DatabaseController {

	@GetMapping(value="/createTables")
	public ResponseEntity<String> createTables(){
		String msg = PopulateDB.createTables();
		
		return ResponseEntity.ok(msg);
	}
	
	@GetMapping(value="/insertData")
	public ResponseEntity<String> insertData(){
		String msg = PopulateDB.insertData();
		
		return ResponseEntity.ok(msg);
	}
}
