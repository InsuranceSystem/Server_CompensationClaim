package ListImpl;

import Dao.CompensationClaimDao;
import Dao.SurveyDao;
import Interface.Survey;
import Interface.SurveyList;

import java.io.*;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SurveyListImpl implements SurveyList, Remote, Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Survey> surveyList;
    public SurveyDao surveyDao;

    public SurveyListImpl() throws Exception {
        this.surveyDao = new SurveyDao();
        this.surveyList = surveyDao.retrieveAll();
    }
    public void finalize() throws Throwable {
    }

    public ArrayList<Survey> retrieve(){
        return surveyList;
    }
    public boolean update(Survey updateSurvey) throws Exception {
        for (int i = 0; i < this.surveyList.size(); i++) {
            Survey survey = (Survey) this.surveyList.get(i);
            if (survey.matchId(updateSurvey.getCCID())) {
                this.surveyList.set(i, updateSurvey);
                surveyDao.update(updateSurvey);
                return true;
            }
        }
        return false;    }
    public boolean createSurvey(Survey survey) throws Exception {
        if(this.surveyList.add(survey)) {
            surveyDao.create(survey);
            return true;
        } else return false;
    }
    public boolean create(Survey survey) throws Exception {
        if(this.surveyList.add(survey)) {
            surveyDao.create(survey);
            return true;
        }
        else return false;
    }
}//end carAccidentListImpl