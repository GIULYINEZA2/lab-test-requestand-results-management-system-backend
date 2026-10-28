package kigali.labtest.rw.repository;

import kigali.labtest.rw.domain.Examinee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

// iyi ni interface ya database operatrions on the Examinee table ukoresheje UUID keys
public interface ExamineeRepository extends JpaRepository<Examinee, UUID> {

}
