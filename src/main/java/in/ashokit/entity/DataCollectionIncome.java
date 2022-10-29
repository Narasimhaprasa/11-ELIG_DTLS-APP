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
@Table(name = "DC_INCOME")
public class DataCollectionIncome {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INCOME_ID")
	private Integer incomeId;

	@Column(name = "CASE_NUM")
	private Long caseNum;

	@Column(name = "MONTH_SAL_INC")
	private Double monthSalIncome;

	@Column(name = "RENT_INC")
	private Double rentIncome;

	@Column(name = "PROPERTY_INC")
	private Double propertyIncome;
}
