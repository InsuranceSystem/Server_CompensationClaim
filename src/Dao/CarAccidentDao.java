package Dao;

import CompensationClaim.CarAccident;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CarAccidentDao extends Dao{
	public CarAccidentDao() {
		try {
			super.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void create(CarAccident carAccident) throws Exception {
		String query = "INSERT INTO CarAccident (CCID, type, dateTime, carNumber, driverName, licenseNumber, accidentDetail) VALUES ('" +
				carAccident.getCCID() + "','" +
				carAccident.getType() + "','" +
				carAccident.getDateTime() + "','" +
				carAccident.getCarNumber() + "','" +
				carAccident.getDriverName() + "','" +
				carAccident.getLicenseNumber() + "','" +
				carAccident.getAccidentDetail() + "');";
		super.create(query);
	}
	public void createAll(ArrayList<CarAccident> carAccidentList) throws Exception {
		CarAccident carAccident;
		for(int i=0;i<=carAccidentList.size();i++) {
			carAccident = carAccidentList.get(i);
			String query = "INSERT INTO CarAccident (CCID, type, dateTime, carNumber, driverName, licenseNumber, accidentDetail) VALUES ('" +
					carAccident.getCCID() + "','" +
					carAccident.getType() + "','" +
					carAccident.getDateTime() + "','" +
					carAccident.getCarNumber() + "','" +
					carAccident.getDriverName() + "','" +
					carAccident.getLicenseNumber() + "','" +
					carAccident.getAccidentDetail() + "');";
			super.create(query);
		}
	}
	public ArrayList<CarAccident> retrieveAll() throws Exception {
		String query = "select * from CarAccident;";
		ResultSet results = super.retrieve(query);
		ArrayList<CarAccident> carAccidentList = new ArrayList<CarAccident>();
		CarAccident carAccident;
		while (results.next()){
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
	}
	public void updateById(String CCID, String column, String content) throws Exception {
		String query = "UPDATE CarAccident SET " + column + "='" + content + "' WHERE CCID='" + CCID + "';";
		super.update(query);
	}
	public void deleteById(String CCID) throws Exception {
		String query = "DELETE FROM CarAccident WHERE CCID="+CCID+";";
		super.delete(query);
	}
	public void deleteAll() throws Exception {
		String query = "DELETE FROM CarAccident;";
		super.delete(query);
	}
}
