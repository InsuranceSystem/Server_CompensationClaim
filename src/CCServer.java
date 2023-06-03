

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import CompensationClaim.CarAccidentList;
import CompensationClaim.CarAccidentListImpl;
import CompensationClaim.CompensationClaimList;
import CompensationClaim.CompensationClaimListImpl;
import CompensationClaim.SurveyList;
import CompensationClaim.SurveyListImpl;
import Interface.CC_ServerIF;




public class CCServer extends UnicastRemoteObject implements CC_ServerIF{
	private static final long serialVersionUID = 1L;
	private static CarAccidentList CarAccidentList;
	private static SurveyList SurveyList;
	private static CompensationClaimList CompensationClaimList;
	
	protected CCServer() throws RemoteException {
		super();
	}
	
	public static void main(String[] args) throws Exception {
		try {
			CCServer server = new CCServer();
			Naming.rebind("CompensationClaim_Server", server);
			
			CarAccidentList = new CarAccidentListImpl();
			SurveyList = new SurveyListImpl();
			CompensationClaimList = new CompensationClaimListImpl();
					
			System.out.println("CompensationClaim Server is ready !!!");		
	
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();	
		}
	}
	@Override
	public CarAccidentList getCarAccidentList() {
		return CarAccidentList;
	}

	@Override
	public SurveyList getSurveyList() {
		return SurveyList;
	}
	
	@Override
	public CompensationClaimList getCompensationClaimList() {
		return CompensationClaimList;
	}
}
