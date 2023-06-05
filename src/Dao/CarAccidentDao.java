package Dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

import Exception.DaoException;
import Interface.CarAccident;

public class CarAccidentDao extends Dao implements Serializable {
	private static final long serialVersionUID = 1L;

	public CarAccidentDao() throws DaoException {
		try {
			super.connect();
		} catch (Exception e) {
			System.out.println("데이터베이스 연결에 실패했습니다." + e.getMessage());
			System.out.println("DAO Exception 발생한 메서드: " + ((DaoException) e).getDaoMethodName());
		}
	}

	public void create(CarAccident carAccident) throws DaoException {
		try {
			String query = "INSERT INTO CarAccident (CCID, type, dateTime, carNumber, driverName, licenseNumber, accidentDetail) VALUES ('"
					+ carAccident.getCCID() + "','" + carAccident.getType() + "','" + carAccident.getDateTime() + "','"
					+ carAccident.getCarNumber() + "','" + carAccident.getDriverName() + "','"
					+ carAccident.getLicenseNumber() + "','" + carAccident.getAccidentDetail() + "');";
			super.create(query);
		} catch (Exception e) {
			throw new DaoException("CarAccident 생성 중에 오류가 발생했습니다.", "create");
		}
	}

	public void createAll(ArrayList<CarAccident> carAccidentList) throws DaoException {
		try {
			CarAccident carAccident;
			for (int i = 0; i < carAccidentList.size(); i++) {
				carAccident = carAccidentList.get(i);
				String query = "INSERT INTO CarAccident (CCID, type, dateTime, carNumber, driverName, licenseNumber, accidentDetail) VALUES ('"
						+ carAccident.getCCID() + "','" + carAccident.getType() + "','" + carAccident.getDateTime()
						+ "','" + carAccident.getCarNumber() + "','" + carAccident.getDriverName() + "','"
						+ carAccident.getLicenseNumber() + "','" + carAccident.getAccidentDetail() + "');";
				super.create(query);
			}
		} catch (Exception e) {
			throw new DaoException("CarAccident 생성 중에 오류가 발생했습니다.", "createAll");
		}
	}

	public ArrayList<CarAccident> retrieveAll() throws DaoException {
		try {
			String query = "SELECT * FROM CarAccident;";
			ResultSet results = super.retrieve(query);
			ArrayList<CarAccident> carAccidentList = new ArrayList<>();
			CarAccident carAccident;
			while (results.next()) {
				carAccident = new CarAccident();
				carAccident.setCCID(results.getString("CCID"));
				carAccident.setType(results.getString("type"));
				carAccident.setDateTime(results.getTimestamp("dateTime").toLocalDateTime());
				carAccident.setCarNumber(results.getString("carNumber"));
				carAccident.setDriverName(results.getString("driverName"));
				carAccident.setLicenseNumber(results.getString("licenseNumber"));
				carAccident.setAccidentDetail(results.getString("accidentDetail"));
				carAccidentList.add(carAccident);
			}
			return carAccidentList;
		} catch (Exception e) {
			throw new DaoException("CarAccident 목록을 가져오는 중에 오류가 발생했습니다.", "retrieveAll");
		}
	}

	public void updateById(String CCID, String column, String content) throws DaoException {
		try {
			String query = "UPDATE CarAccident SET " + column + "='" + content + "' WHERE CCID='" + CCID + "';";
			super.update(query);
		} catch (Exception e) {
			throw new DaoException("CarAccident 업데이트 중에 오류가 발생했습니다.", "updateById");
		}
	}

	public void deleteById(String CCID) throws DaoException {
		try {
			String query = "DELETE FROM CarAccident WHERE CCID='" + CCID + "';";
			super.delete(query);
		} catch (Exception e) {
			throw new DaoException("CarAccident 삭제 중에 오류가 발생했습니다.", "deleteById");
		}
	}

	public void deleteAll() throws DaoException {
		try {
			String query = "DELETE FROM CarAccident;";
			super.delete(query);
		} catch (Exception e) {
			throw new DaoException("CarAccident 삭제 중에 오류가 발생했습니다.", "deleteAll");
		}
	}
}
