package ListImpl;

import Dao.CarAccidentDao;
import Interface.CarAccident;
import Interface.CarAccidentList;
import Interface.CompensationClaim;
import Exception.DaoException;
import java.io.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CarAccidentListImpl implements CarAccidentList, Remote, Serializable {
    private static final long serialVersionUID = 1L;

	private ArrayList<CarAccident> carAccidentList;
	public CarAccidentDao carAccidentDao;

	public CarAccidentListImpl() throws Exception {
		this.carAccidentDao = new CarAccidentDao();
		this.carAccidentList = carAccidentDao.retrieveAll();
	}
	public void finalize() throws Throwable {
	}

	@Override
	public CarAccident getCarAccidentByID(String ccid) {
		for(int i=0;i<this.carAccidentList.size();i++) {
			CarAccident carAccident = (CarAccident) this.carAccidentList.get(i);
			if(carAccident.matchId(ccid))
				return carAccident;
		}
		return null;
	}

	public boolean add(){
		return false;
	}

	@Override
	public boolean delete(String ccid) throws Exception, RemoteException {
		for (int i = 0; i < this.carAccidentList.size(); i++) {
			CarAccident carAccident = (CarAccident) this.carAccidentList.get(i);
			if (carAccident.matchId(ccid))
				if (this.carAccidentList.remove(carAccident)) {
					carAccidentDao.deleteById(ccid);
					return true;}
				else return false;}
		return false;
	}

	public ArrayList<CarAccident> retrieve(){
		return carAccidentList;
	}
	public boolean update(){
		return false;
	}
	public boolean createCarAccident(CarAccident carAccident) throws Exception {
		if(this.carAccidentList.add(carAccident)) {
			carAccidentDao.create(carAccident);
			return true;
		} else return false;
	}
}//end carAccidentListImpl