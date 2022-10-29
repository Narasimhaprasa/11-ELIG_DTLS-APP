package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.DataCollectionChildrens;

public interface DCChidrensRepo extends JpaRepository<DataCollectionChildrens, Integer> {

	@Query(" from DataCollectionChildrens where caseNum=:caseNum")
	public List<DataCollectionChildrens> getChildsData(Long caseNum);
}
