package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.Plan;

public interface DCPlanRepo extends JpaRepository<Plan, Integer> {
	@Query("select distinct(planName) from Plan")
	public List<String> getPlanNames();

}
