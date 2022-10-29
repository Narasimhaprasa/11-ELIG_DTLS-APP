package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.DataCollectionIncome;

public interface DCIncomeRepo extends JpaRepository<DataCollectionIncome, Integer>{
 
	@Query(" from DataCollectionIncome where caseNum=:caseNum")
	public DataCollectionIncome getIncomeData(Long caseNum);
}
