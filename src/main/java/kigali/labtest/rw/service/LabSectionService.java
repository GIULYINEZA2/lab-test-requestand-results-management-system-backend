package kigali.labtest.rw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.labtest.rw.domain.LabSection;
import kigali.labtest.rw.repository.LabSectionRepository;
import java.util.List;
import java.util.UUID;

@Service // iyi i marking this class nka Sercive component
public class LabSectionService {
    @Autowired // Injectis thr LabSectionRepository bean
    private LabSectionRepository repo;

    // i saving a new lab section muri database
    public String save(LabSection labSection) {
        repo.save(labSection);
        return "Lab section saved successfully";
    }

    // Fetctches all lab sections from the databse
    public List<LabSection> findAll() {
        return repo.findAll();
    }

    // gushaka find a single lab section by its unique UUID
    public Optional<LabSection> findById(UUID id) {
        return repo.findById(id);

    }

    // KWA updating an existing lab section's details
    public Optional<LabSection> update(UUID id, LabSection data) {
        Optional<LabSection> optionalSection = repo.findById(id);
        if (optionalSection.isPresent()) {
            LabSection existing = optionalSection.get();
            existing.setName(data.getName());
            return Optional.of(repo.save(existing));
        }
        return Optional.empty();
    }

    // ku deleting a lab section by its ID if it exists
    public boolean delete(UUID id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    // C3 kureba Lab sections nobody holds
    public List<LabSection> findUnused() {
        return repo.findUnused();
    }
}