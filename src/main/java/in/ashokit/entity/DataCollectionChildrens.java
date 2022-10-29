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
@Table(name = "DC_CHILDRENS")
public class DataCollectionChildrens {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CHILDERN_ID")
	private Integer childernId;

	@Column(name = "CHILDERN_NAME")
	private String childernName;

	@Column(name = "CASE_NUM")
	private Long caseNum;

	@Column(name = "CHILDERN_AGE")
	private Integer childernAge;

	@Column(name = "CHILDERN_SSN")
	private Long ssn;
}
