package kigali.labtest.rw.repository;

import kigali.labtest.rw.domain.Workstation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

// iyi i mbwira spring this interface i managing databse operations ya workstation ikoresheje UUID keys
public interface WorkstationRepository extends JpaRepository<Workstation, UUID> {
}