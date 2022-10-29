package in.ashokit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "DC_CASES")
public class DataCollectionCases {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CASE_NUM")
	private Long caseNum;

	@Column(name = "APP_ID")
	private Integer appId;

	@Column(name = "PLAN_ID")
	private Integer planId;

}
