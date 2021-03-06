package com.alexander.dao;

import com.alexander.interfaces.CellPhoneDAO;

abstract public class DAOFactory {
	public static final int MYSQL = 1;
	
	public static DAOFactory getDAOFactory(int database) {
		switch(database) {
			case MYSQL: return new MySQLDAOFactory();
			default: return null;
		}
	}
	
	public abstract CellPhoneDAO getCellPhoneDAO();
}
