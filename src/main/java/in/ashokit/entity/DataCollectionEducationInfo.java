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
@Table(name = "DC_EDUCATION_INFO")
public class DataCollectionEducationInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EDUC_ID")
	private Integer educationId;

	@Column(name = "CASE_NUM")
	private Long caseNum;

	@Column(name = "HIGH_DEGREE")
	private String highestDegree;

	@Column(name = "GRADUATION_YEAR")
	private Integer graduationYear;

	@Column(name = "UNIVERSITY_NAME")
	private String universityName;
}
