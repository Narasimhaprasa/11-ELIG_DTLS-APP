package in.ashokit.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.CitizensApp;
import in.ashokit.entity.CoTriggersEntity;
import in.ashokit.entity.DataCollectionCases;
import in.ashokit.entity.DataCollectionChildrens;
import in.ashokit.entity.DataCollectionEducationInfo;
import in.ashokit.entity.DataCollectionIncome;
import in.ashokit.entity.EligDtlsEntity;
import in.ashokit.entity.Plan;
import in.ashokit.repo.CoTriggersRepo;
import in.ashokit.repo.DCCasesRepo;
import in.ashokit.repo.DCChidrensRepo;
import in.ashokit.repo.DCCitizensRepo;
import in.ashokit.repo.DCEducationRepo;
import in.ashokit.repo.DCIncomeRepo;
import in.ashokit.repo.DCPlanRepo;
import in.ashokit.repo.EligDtlsRepo;
import in.ashokit.response.EligDtlsReponse;

@Service
public class EligDtlsServiceImpl implements IEligDtlsService {

	@Autowired
	private DCCasesRepo caseRepo;

	@Autowired
	private DCPlanRepo planRepo;

	@Autowired
	private DCCitizensRepo citizenRepo;

	@Autowired
	private DCIncomeRepo incomeRepo;

	@Autowired
	private DCChidrensRepo childRepo;

	@Autowired
	private DCEducationRepo educRepo;

	@Autowired
	private EligDtlsRepo eligDtlsRepo;

	@Autowired
	private CoTriggersRepo trigRepo;

	@Override
	public EligDtlsReponse eligibilityDetermination(Long caseNum) {
		// response = new EligDtlsReponse();
		DataCollectionCases caseEntity = caseRepo.getCaseNumber(caseNum);
		Long caseNumber = null;
		String planName = null;
		Integer appId = null;
		if (caseEntity != null) {
			caseNumber = caseEntity.getCaseNum();
		}
		Optional<CitizensApp> entity = citizenRepo.findById(caseEntity.getAppId());
		if (entity.isPresent()) {
			CitizensApp citizenData = entity.get();
			appId = citizenData.getApp_Id();

		}
		Plan plan = null;
		Optional<Plan> findById = planRepo.findById(appId);
		if (findById.isPresent()) {
			plan = findById.get();
			planName = plan.getPlanName();
		}
		EligDtlsReponse response = planEligibleCheck(planName, caseNumber, appId);
		response.setPlanStartDate(plan.getPlanStartDate());
		response.setPlanEndDate(plan.getPlanEndDate());

		EligDtlsEntity eligEntity = new EligDtlsEntity();
		BeanUtils.copyProperties(response, eligEntity);
		eligDtlsRepo.save(eligEntity);

		CoTriggersEntity trigEntity = new CoTriggersEntity();
		trigEntity.setCaseNum(caseNumber);
		trigEntity.setTrigStatus("pending");
		trigEntity.setNotice(null);
		trigRepo.save(trigEntity);

		return response;
	}

	private EligDtlsReponse planEligibleCheck(String planName, Long caseNumber, Integer appId) {
		EligDtlsReponse response = new EligDtlsReponse();

		if ("SNAP".equals(planName)) {
			DataCollectionIncome incomeEntity = incomeRepo.getIncomeData(caseNumber);
			if (incomeEntity != null) {
				Double monthSalIncome = incomeEntity.getMonthSalIncome();
				if (monthSalIncome <= 300) {
					response.setPlanName(planName);
					response.setPlanStatus("Approved");
				} else {
					response.setPlanStatus("Denied");
					response.setDenialReason("Income is exceed");
				}
			}
		} else if ("CCAP".equals(planName)) {
			DataCollectionIncome incomeEntity = incomeRepo.getIncomeData(caseNumber);
			Double income = null;
			Integer childAge = null;
			if (incomeEntity != null) {
				income = incomeEntity.getMonthSalIncome();
			}
			List<DataCollectionChildrens> childEntity = childRepo.getChildsData(caseNumber);
			int count = 0;
			for (DataCollectionChildrens childs : childEntity) {
				count++;
				Integer childernAge = childs.getChildernAge();
				if (childernAge > 16) {
					break;
				} else {
					childAge = childs.getChildernAge();
				}
				if (income <= 300 && count > 0 && childAge <= 16) {
					response.setPlanName(planName);
					response.setPlanStatus("Approved");
				} else {
					response.setPlanStatus("Denied");
					response.setDenialReason("Income and childAge is exceed");
				}
			}
		} else if ("Medicad".equals(planName)) {
			DataCollectionIncome incomeData = incomeRepo.getIncomeData(caseNumber);
			Double monthSalIncome = null;
			Double propertyIncome = null;
			if (incomeData != null) {
				monthSalIncome = incomeData.getMonthSalIncome();
				propertyIncome = incomeData.getPropertyIncome();
			}
			if (monthSalIncome <= 300 && propertyIncome == 0) {
				response.setPlanName(planName);
				response.setPlanStatus("Approved");
			} else {
				response.setPlanStatus("Denied");
				response.setDenialReason("Income ,property is exceeded");
			}
		} else if ("Medicare".equals(planName)) {
			Optional<CitizensApp> citizenEntity = citizenRepo.findById(appId);
			if (citizenEntity.isPresent()) {
				CitizensApp citizenData = citizenEntity.get();
				LocalDate dob = citizenData.getDob();
				LocalDate now = LocalDate.now();
				int age = Period.between(dob, now).getYears();
				if (age >= 65) {
					response.setPlanName(planName);
					response.setPlanStatus("Approved");
				} else {
					response.setPlanStatus("Denied");
					response.setDenialReason("Age is not matched");
				}
			}
		} else if ("NJW".equals(planName)) {
			DataCollectionEducationInfo educationEntity = educRepo.getEducationData(caseNumber);

			if (educationEntity != null) {
				String highestDegree = educationEntity.getHighestDegree();
				Integer graduationYear = educationEntity.getGraduationYear();
				LocalDate now = LocalDate.now();
				int currentYear = now.getYear();
				if (graduationYear < currentYear) {
					response.setPlanName(planName);
					response.setPlanStatus("Approved");
				} else {
					response.setPlanStatus("Denied");
					response.setDenialReason("year not matched");
				}
			}
		}
		response.setBenifitAmt(350.0);
		return response;
	}

}
