package kigali.labtest.rw.repository;

import kigali.labtest.rw.domain.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import java.util.UUID;

// iyi ni interface ya databse operations on the Technician Table using UUID keys
public interface TechnicianRepository extends JpaRepository<Technician, UUID> {

    // C1 kureba Technicians by lab section
    @Query("SELECT DISTINCT t FROM Technician t JOIN t.labSections ls WHERE LOWER(ls.name) = LOWER(?1)")
    List<Technician> findByLabSectionName(String name);
}
