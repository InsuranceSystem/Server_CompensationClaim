package Dao;

import Interface.Survey;

import java.sql.ResultSet;
import java.util.ArrayList;

import Exception.DaoException;

public class SurveyDao extends Dao {
	public SurveyDao() throws DaoException {
		try {
			super.connect();
		} catch (Exception e) {
			System.out.println("데이터베이스 연결에 실패했습니다." + e.getMessage());
			System.out.println("DAO Exception 발생한 메서드: " + ((DaoException) e).getDaoMethodName());
		}
	}

	public void create(Survey survey) throws DaoException {
		try {
			String query = "INSERT INTO Survey (CCID, managerName, reportFilePath, surveyFee, decisionMoney, responsibility, responsibilityReason) VALUES ('"
					+ survey.getCCID() + "','" + survey.getManagerName() + "','" + survey.getReportFilePath() + "','"
					+ survey.getSurveyFee() + "','" + survey.getDecisionMoney() + "','" + survey.isResponsibility()
					+ "','" + survey.getResponsibilityReason() + "');";
			super.create(query);
		} catch (Exception e) {
			// 예외를 DaoException으로 래핑하여 다시 던짐
			throw new DaoException("Survey 생성 중에 오류가 발생했습니다.", "create");
		}
	}

	public void createAll(ArrayList<Survey> surveyList) throws DaoException {
		try {
			Survey survey;
			for (int i = 0; i <= surveyList.size(); i++) {
				survey = surveyList.get(i);
				String query = "INSERT INTO Survey (CCID, managerName, reportFilePath, surveyFee, decisionMoney, responsibility, responsibilityReason) VALUES ('"
						+ survey.getCCID() + "','" + survey.getManagerName() + "','" + survey.getReportFilePath()
						+ "','" + survey.getSurveyFee() + "','" + survey.getDecisionMoney() + "','"
						+ survey.isResponsibility() + "','" + survey.getResponsibilityReason() + "');";
				super.create(query);
			}
		} catch (Exception e) {
			throw new DaoException("Survey 생성 중에 오류가 발생했습니다.", "createAll");
		}

	}

	public ArrayList<Survey> retrieveAll() throws DaoException {
		try {
			String query = "select * from Survey;";
			ResultSet results = super.retrieve(query);
			ArrayList<Survey> surveyList = new ArrayList<Survey>();
			Survey survey;
			while (results.next()) {
				survey = new Survey();
				survey.setCCID(results.getString("CCID"));
				survey.setManagerName(results.getString("managerName"));
				survey.setReportFilePath(results.getString("reportFilePath"));
				survey.setSurveyFee(results.getInt("surveyFee"));
				survey.setDecisionMoney(results.getInt("decisionMoney"));
				survey.setResponsibility(results.getBoolean("responsibility"));
				survey.setResponsibilityReason(results.getString("responsibilityReason"));
				surveyList.add(survey);
			}
			return surveyList;
		} catch (Exception e) {
			throw new DaoException("Survey 목록을 가져오는 중에 오류가 발생했습니다.", "retrieveAll");
		}

	}

	public void update(Survey survey) throws DaoException {
		try {
			String query = "UPDATE Survey SET CCID = '" + survey.getCCID() + "', managerName = '"
					+ survey.getManagerName() + "', reportFilePath = '" + survey.getReportFilePath()
					+ "', surveyFee = '" + survey.getSurveyFee() + "', decisionMoney = '" + survey.getDecisionMoney()
					+ "', " + "responsibility = '" + survey.isResponsibility() + "', responsibilityReason = '"
					+ survey.getResponsibilityReason() + "' WHERE CCID = '" + survey.getCCID() + "';";
			super.update(query);
		} catch (Exception e) {
			throw new DaoException("Survey 업데이트 중에 오류가 발생했습니다.", "update");
		}

	}

	public void updateById(String CCID, String column, String content) throws DaoException {
		try {
			String query = "UPDATE Survey SET " + column + "='" + content + "' WHERE CCID='" + CCID + "';";
			super.update(query);
		} catch (Exception e) {
			throw new DaoException("Survey 업데이트 중에 오류가 발생했습니다.", "updateById");
		}

	}

	public void deleteById(String CCID) throws DaoException {
		try {
			String query = "DELETE FROM Survey WHERE CCID='" + CCID + "';";
			super.delete(query);
		} catch (Exception e) {
			throw new DaoException("Survey 삭제 중에 오류가 발생했습니다.", "deleteById");
		}

	}

	public void deleteAll() throws DaoException {
		try {
			String query = "DELETE FROM Survey;";
			super.delete(query);
		} catch (Exception e) {
			throw new DaoException("Survey 삭제 중에 오류가 발생했습니다.", "deleteAll");
		}

	}
}
