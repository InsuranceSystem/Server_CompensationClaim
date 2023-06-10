package ListImpl;

import Dao.SurveyDao;
import Interface.Survey;
import Interface.SurveyList;

import java.io.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class SurveyListImpl implements SurveyList, Remote, Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Survey> surveyList;
    public SurveyDao surveyDao;

    public SurveyListImpl() throws Exception {
        this.surveyList = new ArrayList<>(); // surveyList 초기화
        this.surveyDao = new SurveyDao();
        this.surveyList = surveyDao.retrieveAll();
    }
    public void finalize() throws Throwable {
    }
    @Override
    public boolean createSurvey(Survey survey) throws Exception, RemoteException {
        if(this.surveyList.add(survey)) {
            surveyDao.createSurvey(survey);
            return true;
        } else return false;    }

    @Override
    public ArrayList<Survey> retrieve() throws Exception {
        this.surveyList = surveyDao.retrieveAll();
        return surveyList;
    }

    @Override
    public boolean updateSurvey(Survey updateSurvey) throws Exception, RemoteException {
        for (int i = 0; i < this.surveyList.size(); i++) {
            Survey survey = (Survey) this.surveyList.get(i);
            if (survey.matchID(updateSurvey.getCCID())) {
                this.surveyList.set(i, updateSurvey);
                surveyDao.updateSurvey(updateSurvey);
                return true;
            }
        }
        return false;
    }

    @Override
    public Survey getSurveyByID(String ccid) throws Exception, RemoteException {
        for(int i=0;i<this.surveyList.size();i++) {
            Survey survey = (Survey) this.surveyList.get(i);
            if(survey.matchID(ccid))
                return survey;
        }
        return null;
    }

    @Override
    public void deleteById(String ccid) throws Exception, RemoteException {
        for(int i=0;i<this.surveyList.size();i++) {
            if(this.surveyList.get(i).matchID(ccid)) {
                this.surveyList.remove(i);
            }
        }
        this.surveyDao.deleteById(ccid);
    }

}//end carAccidentListImpl