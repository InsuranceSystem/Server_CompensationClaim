package Dao;

import Interface.Survey;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SurveyDao extends Dao implements Serializable {
    private static final long serialVersionUID = 1L;
	public SurveyDao() {
		try {
			super.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void create(Survey survey) throws Exception {
		String query = "INSERT INTO Survey (CCID, managerName, reportFilePath, surveyFee, decisionMoney, responsibility, responsibilityReason) VALUES ('" +
				survey.getCCID() + "','" +
				survey.getManagerName() + "','" +
				survey.getReportFilePath() + "','" +
				survey.getSurveyFee() + "','" +
				survey.getDecisionMoney() + "','" +
				survey.isResponsibility() + "','" +
				survey.getResponsibilityReason() + "');";
		super.create(query);
	}
	public void createAll(ArrayList<Survey> surveyList) throws Exception {
		Survey survey;
		for(int i=0;i<=surveyList.size();i++) {
			survey = surveyList.get(i);
			String query = "INSERT INTO Survey (CCID, managerName, reportFilePath, surveyFee, decisionMoney, responsibility, responsibilityReason) VALUES ('" +
					survey.getCCID() + "','" +
					survey.getManagerName() + "','" +
					survey.getReportFilePath() + "','" +
					survey.getSurveyFee() + "','" +
					survey.getDecisionMoney() + "','" +
					survey.isResponsibility() + "','" +
					survey.getResponsibilityReason() + "');";
			super.create(query);
		}
	}
	public ArrayList<Survey> retrieveAll() throws Exception {
		String query = "select * from Survey;";
		ResultSet results = super.retrieve(query);
		ArrayList<Survey> surveyList = new ArrayList<Survey>();
		Survey survey;
		while (results.next()){
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
	}
	public void update(Survey survey) throws Exception {
		String query = "UPDATE Survey SET CCID = '" + survey.getCCID() + "', managerName = '" + survey.getManagerName() + "', reportFilePath = '"
				+ survey.getReportFilePath() + "', surveyFee = '" + survey.getSurveyFee() + "', decisionMoney = '" + survey.getDecisionMoney() + "', "
				+ "responsibility = '" + survey.isResponsibility() + "', responsibilityReason = '" + survey.getResponsibilityReason() + "' WHERE CCID = '" + survey.getCCID() + "';";
		super.update(query);
	}
	public void updateById(String CCID, String column, String content) throws Exception {
		String query = "UPDATE Survey SET " + column + "='" + content + "' WHERE CCID='" + CCID + "';";
		super.update(query);
	}
	public void deleteById(String CCID) throws Exception {
		String query = "DELETE FROM Survey WHERE CCID='" + CCID + "';";
		super.delete(query);
	}
	public void deleteAll() throws Exception {
		String query = "DELETE FROM Survey;";
		super.delete(query);
	}
}
