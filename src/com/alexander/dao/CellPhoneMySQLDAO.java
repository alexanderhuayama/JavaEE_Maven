package com.alexander.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.alexander.beans.CellPhoneDTO;
import com.alexander.interfaces.CellPhoneDAO;
import com.alexander.utils.MySQLConnection;

public class CellPhoneMySQLDAO implements CellPhoneDAO {

	@Override
	public ArrayList<CellPhoneDTO> cellPhones() {
		ArrayList<CellPhoneDTO> cellPhones = new ArrayList<CellPhoneDTO>();
		CellPhoneDTO cellPhone = null;
		Connection cn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		String sql = "CALL sp_get_cellphones()";

		try {
			cn = MySQLConnection.getConnection();
			cs = cn.prepareCall(sql);
			rs = cs.executeQuery();

			while (rs.next()) {
				cellPhone = new CellPhoneDTO();
				cellPhone.setId(rs.getInt("id"));
				cellPhone.setBrand(rs.getString("brand"));
				cellPhone.setModel(rs.getString("model"));
				cellPhone.setPrice(rs.getDouble("price"));
				cellPhone.setRegisterDate(rs.getString("register_date"));
				cellPhone.setUpdateDate(rs.getString("update_date"));

				cellPhones.add(cellPhone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnection.closeConnection(rs, cs, cn);
		}

		return cellPhones;
	}

	@Override
	public CellPhoneDTO getCellPhoneById(int id) {
		CellPhoneDTO cellPhone = null;
		Connection cn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		String sql = "CALL sp_get_cellphone_by_id(?)";

		try {
			cn = MySQLConnection.getConnection();
			cs = cn.prepareCall(sql);
			cs.setInt(1, id);
			rs = cs.executeQuery();

			if (rs.next()) {
				cellPhone = new CellPhoneDTO();
				cellPhone.setId(rs.getInt("id"));
				cellPhone.setBrand(rs.getString("brand"));
				cellPhone.setModel(rs.getString("model"));
				cellPhone.setPrice(rs.getDouble("price"));
				cellPhone.setRegisterDate(rs.getString("register_date"));
				cellPhone.setUpdateDate(rs.getString("update_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnection.closeConnection(rs, cs, cn);
		}

		return cellPhone;
	}

	@Override
	public int registerCellPhone(CellPhoneDTO cellPhone) {
		int affectedRows = -1;
		Connection cn = null;
		CallableStatement cs = null;
		String sql = "CALL sp_register_cellphone(?,?,?)";

		try {
			cn = MySQLConnection.getConnection();
			cs = cn.prepareCall(sql);
			cs.setString(1, cellPhone.getBrand());
			cs.setString(2, cellPhone.getModel());
			cs.setDouble(3, cellPhone.getPrice());

			affectedRows = cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnection.closeConnection(null, cs, cn);
		}

		return affectedRows;
	}

	@Override
	public int updateCellPhone(CellPhoneDTO cellPhone) {
		int affectedRows = -1;
		Connection cn = null;
		CallableStatement cs = null;
		String sql = "CALL sp_update_cellphone(?,?,?,?)";

		try {
			cn = MySQLConnection.getConnection();
			cs = cn.prepareCall(sql);
			cs.setInt(1, cellPhone.getId());
			cs.setString(2, cellPhone.getBrand());
			cs.setString(3, cellPhone.getModel());
			cs.setDouble(4, cellPhone.getPrice());

			affectedRows = cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnection.closeConnection(null, cs, cn);
		}

		return affectedRows;
	}

	@Override
	public int deleteCellPhone(int id) {
		int affectedRows = -1;
		Connection cn = null;
		CallableStatement cs = null;
		String sql = "CALL sp_delete_cellphone(?)";

		try {
			cn = MySQLConnection.getConnection();
			cs = cn.prepareCall(sql);
			cs.setInt(1, id);

			affectedRows = cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnection.closeConnection(null, cs, cn);
		}

		return affectedRows;
	}

}
