package Dao;

import CompensationClaim.CompensationClaim;
import Exception.DaoException;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CompensationClaimDao extends Dao {
	public CompensationClaimDao() throws DaoException {
		try {
			super.connect();
		} catch (Exception e) {
			System.out.println("데이터베이스 연결에 실패했습니다." + e.getMessage());
			System.out.println("DAO Exception 발생한 메서드: " + ((DaoException) e).getDaoMethodName());
		}
	}

	public void create(CompensationClaim compensationClaim) throws DaoException {
		try {
			String query = "INSERT INTO CompensationClaim (CCID, insuranceID, customerID, receptionistName, receptionistPNumber, relationshipOfContractor, documentFilePath, bank, accountNumber, accountHolderName) VALUES ('"
					+ compensationClaim.getCCID() + "','" + compensationClaim.getInsuranceID() + "','"
					+ compensationClaim.getCustomerID() + "','" + compensationClaim.getReceptionistName() + "','"
					+ compensationClaim.getReceptionistPNumber() + "','"
					+ compensationClaim.getRelationshipOfContractor() + "','" + compensationClaim.getDocumentFilePath()
					+ "','" + compensationClaim.getBank() + "','" + compensationClaim.getAccountNumber() + "','"
					+ compensationClaim.getAccountHolderName() + "');";
			super.create(query);
		} catch (Exception e) {
			throw new DaoException("CompensationClaim 생성 중에 오류가 발생했습니다.", "create");
		}
	}

	public void createAll(ArrayList<CompensationClaim> compensationClaimList) throws DaoException {
		try {
			CompensationClaim compensationClaim;
			for (int i = 0; i < compensationClaimList.size(); i++) {
				compensationClaim = compensationClaimList.get(i);
				String query = "INSERT INTO CompensationClaim (CCID, insuranceID, customerID, receptionistName, receptionistPNumber, relationshipOfContractor, documentFilePath, bank, accountNumber, accountHolderName) VALUES ('"
						+ compensationClaim.getCCID() + "','" + compensationClaim.getInsuranceID() + "','"
						+ compensationClaim.getCustomerID() + "','" + compensationClaim.getReceptionistName() + "','"
						+ compensationClaim.getReceptionistPNumber() + "','"
						+ compensationClaim.getRelationshipOfContractor() + "','"
						+ compensationClaim.getDocumentFilePath() + "','" + compensationClaim.getBank() + "','"
						+ compensationClaim.getAccountNumber() + "','" + compensationClaim.getAccountHolderName()
						+ "');";
				super.create(query);
			}
		} catch (Exception e) {
			throw new DaoException("CompensationClaim 생성 중에 오류가 발생했습니다.", "createAll");
		}

	}

	public ArrayList<CompensationClaim> retrieveAll() throws DaoException {
		try {
			String query = "SELECT * FROM CompensationClaim;";
			ResultSet results = super.retrieve(query);
			ArrayList<CompensationClaim> compensationClaimList = new ArrayList<>();
			CompensationClaim compensationClaim;
			while (results.next()) {
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
		} catch (Exception e) {
			throw new DaoException("CompensationClaim 목록을 가져오는 중에 오류가 발생했습니다.", "retrieveAll");
		}
	}

	public void updateById(String CCID, String column, String content) throws DaoException {
		try {
			String query = "UPDATE CompensationClaim SET " + column + "='" + content + "' WHERE CCID='" + CCID + "';";
			super.update(query);
		} catch (Exception e) {
			throw new DaoException("CompensationClaim 업데이트 중에 오류가 발생했습니다.", "updateById");
		}
	}

	public void deleteById(String CCID) throws DaoException {
		try {
			String query = "DELETE FROM CompensationClaim WHERE CCID='" + CCID + "';";
			super.delete(query);
		} catch (Exception e) {
			throw new DaoException("CompensationClaim 삭제 중에 오류가 발생했습니다.", "deleteById");
		}
	}

	public void deleteAll() throws DaoException {
        try {
            String query = "DELETE FROM CompensationClaim;";
            super.delete(query);
        } catch (Exception e) {
            throw new DaoException("CompensationClaim 삭제 중에 오류가 발생했습니다.", "deleteAll");
        }
    }
