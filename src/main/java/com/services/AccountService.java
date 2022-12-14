package com.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dao.AccountDAO;
import com.interfaces.Listable;
import com.models.AccountModel;

@Service
public class AccountService {
	AccountDAO DAO = new AccountDAO();
	
	public ResponseEntity<List<Listable>> findAll() throws SQLException{
		return ResponseEntity.ok(DAO.getAll());
	}
	
	public ResponseEntity<Listable> findById(int id) throws SQLException{
		return ResponseEntity.ok(DAO.get(id));
	}
	
	public ResponseEntity<String> remove(int id) throws SQLException{
		return ResponseEntity.ok(DAO.remove(id));
	}
	
	public ResponseEntity<String> insert(AccountModel account) throws SQLException{
		return ResponseEntity.ok(DAO.insert(account));
	}
	
	public ResponseEntity<String> update(int id, AccountModel account) throws SQLException{
		return ResponseEntity.ok(DAO.update(id, account));
	}
}
