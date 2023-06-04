

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import ListImpl.CompensationClaimListImpl;
import ListImpl.SurveyListImpl;
import Interface.CarAccidentList;
import ListImpl.CarAccidentListImpl;
import Interface.CompensationClaimList;
import Interface.SurveyList;




public class CCServer extends UnicastRemoteObject {
	private static final long serialVersionUID = 1L;
	
	protected CCServer() throws RemoteException {
		super();
	}
	
	public static void main(String[] args) throws Exception {
		try {
			System.setProperty("java.security.policy", "policy.txt");
			System.setSecurityManager(null);
			CarAccidentList carAccidentList = new CarAccidentListImpl();
			 CarAccidentList stub = (CarAccidentList) UnicastRemoteObject.exportObject(carAccidentList, 0);
		        Registry registry1 = LocateRegistry.createRegistry(1300);
		        registry1.rebind("CarAccidentList", stub);
		        
		        // SurveyList 객체 등록
		        SurveyList surveyList = new SurveyListImpl();
		        SurveyList stub2 = (SurveyList) UnicastRemoteObject.exportObject(surveyList, 0);
		        Registry registry2 = LocateRegistry.createRegistry(1301);
		        registry2.rebind("SurveyList", stub2);
		        
		        // CompensationClaimList 객체 등록
		        CompensationClaimList compensationClaimList = new CompensationClaimListImpl();
		        CompensationClaimList stub3 = (CompensationClaimList) UnicastRemoteObject.exportObject(compensationClaimList, 0);
		        Registry registry3 = LocateRegistry.createRegistry(1302);
		        registry3.rebind("CompensationClaimList", stub3);
		            				
			System.out.println("CompensationClaim Server is ready !!!");		
	
		} catch (RemoteException e) {
			e.printStackTrace();
		} 
	}

}
