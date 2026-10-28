package kigali.labtest.rw.service;

import kigali.labtest.rw.domain.Workstation;
import kigali.labtest.rw.repository.WorkstationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service // since turi muri service this is its class
public class WorkstationService {

    @Autowired // iyi ngiye helps to inject the database repository yacu
    private WorkstationRepository repo;

    // now we have to save iyi workstation yacu ici la

    public String save(Workstation workstation) {
        repo.save(workstation);
        return "Workstation saved successfully";
    }

    // save it is not enough twanakora get all workstations :
    public List<Workstation> findAll() {
        return repo.findAll();
    }

    // next we xan find one workstation by ID
    public Optional<Workstation> findById(UUID id) {
        return repo.findById(id);
    }

    // how update updat an existing workstation yacu? we do as follows:
    public Optional<Workstation> update(UUID id, Workstation data) {
        Optional<Workstation> optionalWorkstation = repo.findById(id);
        if (optionalWorkstation.isPresent()) {
            Workstation existing = optionalWorkstation.get();
            existing.setWorkstationNumber(data.getWorkstationNumber());
            existing.setName(data.getName());
            return Optional.of(repo.save(existing));
        }
        return Optional.empty(); // iyi ireturning empty iyo Id iri not found
    }

    // hano ha deletinga a workstation by ID
    public boolean delete(UUID id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}