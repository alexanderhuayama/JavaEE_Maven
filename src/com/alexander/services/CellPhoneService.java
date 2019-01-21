package com.alexander.services;

import java.util.ArrayList;

import com.alexander.beans.CellPhoneDTO;
import com.alexander.dao.DAOFactory;
import com.alexander.interfaces.CellPhoneDAO;

public class CellPhoneService {
	DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	CellPhoneDAO dao = factory.getCellPhoneDAO();

	public ArrayList<CellPhoneDTO> cellPhones() {
		return dao.cellPhones();
	}

	public CellPhoneDTO getCellPhoneById(int id) {
		return dao.getCellPhoneById(id);
	}

	public int registerCellPhone(CellPhoneDTO cellPhone) {
		return dao.registerCellPhone(cellPhone);
	}

	public int updateCellPhone(CellPhoneDTO cellPhone) {
		return dao.updateCellPhone(cellPhone);
	}

	public int deleteCellPhone(int id) {
		return dao.deleteCellPhone(id);
	}

}
