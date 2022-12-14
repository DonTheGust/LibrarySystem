package com.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dao.ClientDAO;
import com.interfaces.Listable;
import com.models.ClientModel;

@Service
public class ClientService {
	ClientDAO DAO = new ClientDAO();
	
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return ResponseEntity.ok(DAO.getAll());
	}
	
	public ResponseEntity<Listable> findById(int id) throws SQLException{
		return ResponseEntity.ok(DAO.get(id));
	}
	
	public ResponseEntity<String> remove(int id) throws SQLException{
		return ResponseEntity.ok(DAO.remove(id));
	}
	
	public ResponseEntity<String> insert(ClientModel client) throws SQLException{
		return ResponseEntity.ok(DAO.insert(client));
	}
	
	public ResponseEntity<String> update(int id, ClientModel client) throws SQLException{
		return ResponseEntity.ok(DAO.update(id, client));
	}
}
