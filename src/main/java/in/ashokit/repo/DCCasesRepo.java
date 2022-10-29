package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.DataCollectionCases;

public interface DCCasesRepo extends JpaRepository<DataCollectionCases, Integer> {
	@Query(" from DataCollectionCases where caseNum=:caseNum")
	public DataCollectionCases getCaseNumber(Long caseNum);

}
