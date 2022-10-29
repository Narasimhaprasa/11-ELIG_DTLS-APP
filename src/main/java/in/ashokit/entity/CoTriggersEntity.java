package in.ashokit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "CO_TRIGGERS")
public class CoTriggersEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRIGGER_ID")
	private Integer trigId;

	@Column(name = "CASE_NUM")
	private Long caseNum;

	@Column(name = "TRIGGER_STATUS")
	private String trigStatus;

	@Column(name = "PDF_NOTICE")
	private Byte[] notice;
}
