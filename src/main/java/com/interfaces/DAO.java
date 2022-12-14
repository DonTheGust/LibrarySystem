package com.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.conn.DBConn;

public interface DAO {
	public final static DBConn conn = new DBConn();
			
	public abstract String insert(Listable item) throws SQLException;
	
	public abstract Listable get(int id) throws SQLException;
	
	public abstract List<Listable> getAll() throws SQLException;
	
	public abstract String remove(int id) throws SQLException;
	
	public abstract String update(int id, Listable item) throws SQLException;
}
