package in.ashokit.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EligDtlsReponse {

	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Double benifitAmt;
	private String denialReason;
}
