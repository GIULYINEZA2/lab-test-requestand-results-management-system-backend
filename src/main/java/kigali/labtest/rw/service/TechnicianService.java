package kigali.labtest.rw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.labtest.rw.domain.Technician;
import kigali.labtest.rw.repository.TechnicianRepository;
import java.util.UUID;
import java.util.Optional;
import java.util.List;

@Service // iyi marks this class as a Service component
public class TechnicianService {
    @Autowired // Injects database repository
    private TechnicianRepository repo;

    // i saving a new technician record
    public String save(Technician technician) {
        repo.save(technician);
        return "Technician saved successfully";

    }

    // iyingiye i fetching all technicians from the database
    public List<Technician> findAll() {
        return repo.findAll();
    }

    // finds one technicians by their hamwe na Unique UUID
    public Optional<Technician> findById(UUID id) {
        return repo.findById(id);

    }

    // iyi below updates details of an existing technician
    public Optional<Technician> update(UUID id, Technician data) {
        Optional<Technician> optionalTechnician = repo.findById(id);
        if (optionalTechnician.isPresent()) {
            Technician existing = optionalTechnician.get();
            existing.setFirstName(data.getFirstName());
            existing.setLastName(data.getLastName());
            existing.setLicenceNo(data.getLicenceNo());
            existing.setWorkstation(data.getWorkstation());
            return Optional.of(repo.save(existing));
        }
        return Optional.empty();

    }

    // Ideletinga umu technician by Id if they exist
    public boolean delete(UUID id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
