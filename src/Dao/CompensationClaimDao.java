package Dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

import CompensationClaim.CompensationClaim;

public class CompensationClaimDao extends Dao implements Serializable {
    private static final long serialVersionUID = 1L;
	public CompensationClaimDao() {
		try {
			super.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void create(CompensationClaim compensationClaim) throws Exception {
		String query = "INSERT INTO CompensationClaim (CCID, insuranceID, customerID, receptionistName, receptionistPNumber, relationshipOfContractor, documentFilePath, bank, accountNumber, accountHolderName) VALUES ('" +
				compensationClaim.getCCID() + "','" +
				compensationClaim.getInsuranceID() + "','" +
				compensationClaim.getCustomerID() + "','" +
				compensationClaim.getReceptionistName() + "','" +
				compensationClaim.getReceptionistPNumber() + "','" +
				compensationClaim.getRelationshipOfContractor() + "','" +
				compensationClaim.getDocumentFilePath() + "','" +
				compensationClaim.getBank() + "','" +
				compensationClaim.getAccountNumber() + "','" +
				compensationClaim.getAccountHolderName() + "');";
		super.create(query);
	}
	public void createAll(ArrayList<CompensationClaim> compensationClaimList) throws Exception {
		CompensationClaim compensationClaim;
		for(int i=0;i<=compensationClaimList.size();i++) {
			compensationClaim = compensationClaimList.get(i);
			String query = "INSERT INTO CompensationClaim (CCID, insuranceID, customerID, receptionistName, receptionistPNumber, relationshipOfContractor, documentFilePath, bank, accountNumber, accountHolderName) VALUES ('" +
					compensationClaim.getCCID() + "','" +
					compensationClaim.getInsuranceID() + "','" +
					compensationClaim.getCustomerID() + "','" +
					compensationClaim.getReceptionistName() + "','" +
					compensationClaim.getReceptionistPNumber() + "','" +
					compensationClaim.getRelationshipOfContractor() + "','" +
					compensationClaim.getDocumentFilePath() + "','" +
					compensationClaim.getBank() + "','" +
					compensationClaim.getAccountNumber() + "','" +
					compensationClaim.getAccountHolderName() + "');";
			super.create(query);
		}
	}
	public ArrayList<CompensationClaim> retrieveAll() throws Exception {
		String query = "select * from CompensationClaim;";
		ResultSet results = super.retrieve(query);
		ArrayList<CompensationClaim> compensationClaimList = new ArrayList<CompensationClaim>();
		CompensationClaim compensationClaim;
		while (results.next()){
			compensationClaim = new CompensationClaim();
			compensationClaim.setCCID(results.getString("CCID"));
			compensationClaim.setInsuranceID(results.getString("insuranceID"));
			compensationClaim.setCustomerID(results.getString("customerID"));
			compensationClaim.setReceptionistName(results.getString("receptionistName"));
			compensationClaim.setReceptionistPNumber(results.getString("receptionistPNumber"));
			compensationClaim.setRelationshipOfContractor(results.getString("relationshipOfContractor"));
			compensationClaim.setDocumentFilePath(results.getString("documentFilePath"));
			compensationClaim.setBank(results.getString("bank"));
			compensationClaim.setAccountNumber(results.getString("accountNumber"));
			compensationClaim.setAccountHolderName(results.getString("accountHolderName"));
			compensationClaimList.add(compensationClaim);
		}
		return compensationClaimList;
	}
	public void updateById(String CCID, String column, String content) throws Exception {
		String query = "UPDATE CompensationClaim SET " + column + "='" + content + "' WHERE CCID='" + CCID + "';";
		super.update(query);
	}
	public void deleteById(String CCID) throws Exception {
		String query = "DELETE FROM CompensationClaim WHERE CCID='" + CCID + "';";
		super.delete(query);
	}
	public void deleteAll() throws Exception {
		String query = "DELETE FROM CompensationClaim;";
		super.delete(query);
	}
}
