package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.DataCollectionEducationInfo;

public interface DCEducationRepo extends JpaRepository<DataCollectionEducationInfo, Integer> {

	@Query(" from DataCollectionEducationInfo where caseNum=:caseNum")
	public DataCollectionEducationInfo  getEducationData(Long caseNum);

}