package in.ashokit.service;

import in.ashokit.response.EligDtlsReponse;

public interface IEligDtlsService {

	public EligDtlsReponse eligibilityDetermination(Long caseNum);
}
