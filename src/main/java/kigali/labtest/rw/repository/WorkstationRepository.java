package kigali.labtest.rw.repository;

import kigali.labtest.rw.domain.Workstation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// iyi i mbwira spring this interface i managing databse operations ya workstation ikoresheje UUID keys
public interface WorkstationRepository extends JpaRepository<Workstation, UUID> {

    // C2 JPQL C2 Free workstations

    @Query("SELECT w FROM Workstation w WHERE NOT EXISTS(SELECT t FROM Technician t WHERE t.workstation = w) ORDER BY w.workstationNumber ASC")
    List<Workstation> findFree();

    // D4: KU Moving umu technician to another workstation
    Optional<Workstation> findByWorkstationNumber(int workstationNumber);

}