package kigali.labtest.rw.repository;

import kigali.labtest.rw.domain.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

// iyi ni interface ya databse operations on the Technician Table using UUID keys
public interface TechnicianRepository extends JpaRepository<Technician, UUID> {

}
