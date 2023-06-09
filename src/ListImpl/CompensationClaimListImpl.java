package ListImpl;

import Dao.CompensationClaimDao;
import Interface.CompensationClaim;
import Interface.CompensationClaimList;
import Exception.DaoException;

import java.io.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class CompensationClaimListImpl implements CompensationClaimList, Remote, Serializable {
    private static final long serialVersionUID = 1L;

	private ArrayList<CompensationClaim> compensationClaimList;
	private CompensationClaimDao compensationClaimDao;
	public SurveyListImpl surveyList;

	public CompensationClaimListImpl() throws Exception {
		this.compensationClaimDao = new CompensationClaimDao();
		this.compensationClaimList = compensationClaimDao.retrieveAll();
		surveyList = new SurveyListImpl();
	}
	public void finalize() throws Throwable {
	}
	public ArrayList<CompensationClaim> retrieve(){
		return compensationClaimList;
	}
	public boolean update(){
		return false;
	}

	@Override
	public boolean delete(String CCID) throws Exception, RemoteException {
		for (int i = 0; i < this.compensationClaimList.size(); i++) {
			CompensationClaim compensationClaim = (CompensationClaim) this.compensationClaimList.get(i);
			if (compensationClaim.matchId(CCID))
				if (this.compensationClaimList.remove(compensationClaim)) {
                    try {
                        surveyList.deleteById(CCID);
                    } catch (DaoException e) {
                        throw new RuntimeException(e);
                    }
                    compensationClaimDao.deleteById(CCID);
					return true;}
				else return false;}
		return false;
	}

	public boolean createCompensationClaim(CompensationClaim compensationClaim) throws Exception {
		if (this.compensationClaimList.add(compensationClaim)) {
			compensationClaimDao.create(compensationClaim);
			return true;
		}
		else return false;
	}

    public CompensationClaim getCompensationClaimbyID(String inputCCID) {
		for(int i=0;i<this.compensationClaimList.size();i++) {
			CompensationClaim compensationClaim = (CompensationClaim) this.compensationClaimList.get(i);
			if(compensationClaim.matchId(inputCCID))
				return compensationClaim;
		}
		return null;
    }
}
