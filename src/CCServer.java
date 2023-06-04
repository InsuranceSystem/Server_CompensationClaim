
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import CompensationClaim.CarAccidentList;
import CompensationClaim.CarAccidentListImpl;
import CompensationClaim.CompensationClaimList;
import CompensationClaim.CompensationClaimListImpl;
import CompensationClaim.SurveyList;
import CompensationClaim.SurveyListImpl;
import Exception.CustomClassNotFoundException;
import Exception.CustomConnectException;
import Exception.CustomIllegalAccessException;
import Exception.CustomNotBoundException;
import Exception.CustomSQLException;
import Interface.CC_ServerIF;

public class CCServer extends UnicastRemoteObject implements CC_ServerIF {
	private static final long serialVersionUID = 1L;
	private static CarAccidentList CarAccidentList;
	private static SurveyList SurveyList;
	private static CompensationClaimList CompensationClaimList;

	protected CCServer() throws RemoteException {
		super();
	}

	public static void main(String[] args) throws Exception, CustomNotBoundException, CustomConnectException,
			CustomIllegalAccessException, CustomClassNotFoundException, NoClassDefFoundError {
		try {

			// Naming.rebind("CompensationClaim_Server", server);
			Registry registry = LocateRegistry.createRegistry(1300);
			CCServer server = new CCServer();
			registry.rebind("CompensationClaim_Server", server);
			CarAccidentList = new CarAccidentListImpl();
			SurveyList = new SurveyListImpl();
			CompensationClaimList = new CompensationClaimListImpl();

			System.out.println("CompensationClaim Server is ready !!!");

		} catch (RemoteException e) {
			System.out.println("Remote exception occurred: " + e.getMessage());
		} catch (CustomNotBoundException e) {
			System.out.println("Not bound exception occurred: " + e.getMessage());
		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException occurred: " + e.getMessage());
		} catch (CustomConnectException e) {
			System.out.println("Connection exception occurred:  " + e.getMessage());
		} catch (IllegalAccessException e) {
			System.out.println("Illegal access exception occurred: " + e.getMessage());
		} catch (CustomClassNotFoundException | NoClassDefFoundError e) {
			System.out.println("Class Found Error: " + e.getMessage());
		}
	}

	@Override
	public CarAccidentList getCarAccidentList() throws RemoteException {
		return CarAccidentList;
	}

	@Override
	public SurveyList getSurveyList() throws RemoteException {
		return SurveyList;
	}

	@Override
	public CompensationClaimList getCompensationClaimList() throws RemoteException {
		return CompensationClaimList;
	}
}
