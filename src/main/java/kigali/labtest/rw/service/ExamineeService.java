package kigali.labtest.rw.service;

import java.util.Optional;

import org.springframework.expression.spel.ast.OperatorInstanceof;
import org.springframework.stereotype.Service;

import kigali.labtest.rw.domain.Examinee;
import kigali.labtest.rw.repository.ExamineeRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

@Service // iyi i marking this class as a service component
public class ExamineeService {
    @Autowired // Injects the examineeRepositoy bean
    private ExamineeRepository repo;

    // iyi i savinga a new examinee to the database
    public String save(Examinee examinee) {
        repo.save(examinee);
        return "Examinee saved successfully";

    }
    // I fetching a list of all examinees

    public List<Examinee> findAll() {
        return repo.findAll();

    }

    // ifasha to find one examinee by their unique UUID
    public Optional<Examinee> findById(UUID id) {
        return repo.findById(id);

    }

    // ikora ku updating details of an existing examinee
    public Optional<Examinee> update(UUID id, Examinee data) {
        Optional<Examinee> optionalExaminee = repo.findById(id);
        if (optionalExaminee.isPresent()) {
            Examinee existing = optionalExaminee.get();
            existing.setFirstName(data.getFirstName());
            existing.setLastName(data.getLastName());
            existing.setHospitalNumber(data.getHospitalNumber());
            existing.setPhone(data.getPhone());
            return Optional.of(repo.save(existing));
        }
        return Optional.empty();

    }

    // ikore delete of an examinee by ID niba iri exist
    public boolean delete(UUID id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false; // it returning false iyo record itari found
    }
}
