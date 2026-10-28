package kigali.labtest.rw.repository;

import kigali.labtest.rw.domain.LabSection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

// iyi ni interface ya database operations onkuri LabSection table ukoresheje UUID keys 
public interface LabSectionRepository extends JpaRepository<LabSection, UUID> {

}
