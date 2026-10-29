package kigali.labtest.rw.repository;

import kigali.labtest.rw.domain.Examinee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;
import java.util.List;

// iyi ni interface ya database operatrions on the Examinee table ukoresheje UUID keys
public interface ExamineeRepository extends JpaRepository<Examinee, UUID> {
    // B1
    List<Examinee> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    // C4 KUREBA Examinees of a technician
    @Query("SELECT DISTINCT tr.examinee FROM TestRequest tr WHERE tr.technician.id = ?1")
    List<Examinee> findByTechnicianId(UUID technicianId);

}
