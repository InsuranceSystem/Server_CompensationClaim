package server;
import CompensationClaim.CarAccidentList;
import CompensationClaim.CarAccidentListImpl;
import CompensationClaim.CompensationClaimList;
import CompensationClaim.CompensationClaimListImpl;
import CompensationClaim.SurveyList;
import CompensationClaim.SurveyListImpl;

public interface CC_ServerIF {
	public CarAccidentList getCarAccidentList();
	public SurveyList getSurveyList();
	public CompensationClaimList getCompensationClaimList();
}
