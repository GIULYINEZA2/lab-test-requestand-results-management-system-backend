package kigali.labtest.rw.repository;

import kigali.labtest.rw.domain.LabSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import java.util.UUID;

// iyi ni interface ya database operations onkuri LabSection table ukoresheje UUID keys 
public interface LabSectionRepository extends JpaRepository<LabSection, UUID> {
    // C3 JPQL is Empty kureba Lab sections nobody holds
    @Query("SELECT ls FROM LabSection ls WHERE ls.technicians IS EMPTY")
    List<LabSection> findUnused();
}
