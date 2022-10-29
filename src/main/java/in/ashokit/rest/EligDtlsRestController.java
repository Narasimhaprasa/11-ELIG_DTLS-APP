package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.response.EligDtlsReponse;
import in.ashokit.service.IEligDtlsService;

@RestController
public class EligDtlsRestController {
	@Autowired
	private IEligDtlsService service;

	@GetMapping("/eligibleDetermination")
	public ResponseEntity<EligDtlsReponse> eligibilityDetermination(@PathVariable Long caseNum) {
		EligDtlsReponse response = service.eligibilityDetermination(caseNum);
		return new ResponseEntity<EligDtlsReponse>(response, HttpStatus.CREATED);
	}
}
