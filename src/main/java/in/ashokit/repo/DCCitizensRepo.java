package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.CitizensApp;

public interface DCCitizensRepo extends JpaRepository<CitizensApp, Integer> {

}
