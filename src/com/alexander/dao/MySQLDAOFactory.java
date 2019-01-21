package com.alexander.dao;

import com.alexander.interfaces.CellPhoneDAO;

public class MySQLDAOFactory extends DAOFactory {

	@Override
	public CellPhoneDAO getCellPhoneDAO() {
		return new CellPhoneMySQLDAO();
	}

}
