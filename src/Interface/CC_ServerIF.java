package Interface;
import java.rmi.Remote;
import java.rmi.RemoteException;

import CompensationClaim.CarAccidentList;
import CompensationClaim.CarAccidentListImpl;
import CompensationClaim.CompensationClaimList;
import CompensationClaim.CompensationClaimListImpl;
import CompensationClaim.SurveyList;
import CompensationClaim.SurveyListImpl;

public interface CC_ServerIF extends Remote{
	public CarAccidentList getCarAccidentList() throws RemoteException;
	public SurveyList getSurveyList() throws RemoteException;
	public CompensationClaimList getCompensationClaimList() throws RemoteException;
}
