package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import CompensationClaim.CompensationClaim;



public interface CompensationClaimList extends Remote{

	boolean createCompensationClaim(CompensationClaim compensationClaim) throws Exception, RemoteException;

	ArrayList<CompensationClaim> retrieve() throws RemoteException;

	boolean update() throws RemoteException;

	CompensationClaim getCompensationClaimbyID(String ccid) throws RemoteException;

}