package db;

import java.sql.Connection;
import java.util.List;

import db.exception.PersistenceException;

public interface Gateway<T> {
	
	public void setConnection(Connection con);
	
	public void save(T dto) throws PersistenceException;
	
	public void delete(T dto) throws PersistenceException;
	
	public List<T> listAll() throws PersistenceException;
	
	public int count() throws PersistenceException;

}
