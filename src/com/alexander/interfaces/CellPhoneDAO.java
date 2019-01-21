package com.alexander.interfaces;

import java.util.ArrayList;

import com.alexander.beans.CellPhoneDTO;

public interface CellPhoneDAO {
	// Obtiene la lista de celulares
	public ArrayList<CellPhoneDTO> cellPhones();

	// Obtiene un celular por id
	public CellPhoneDTO getCellPhoneById(int id);

	// Registra un celular en la base de datos
	public int registerCellPhone(CellPhoneDTO cellPhone);

	// Actualiza un celular en la base de datos
	public int updateCellPhone(CellPhoneDTO cellPhone);

	// ELimina un celular de la base de datos
	public int deleteCellPhone(int id);
}
