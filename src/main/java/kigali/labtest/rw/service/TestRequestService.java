package kigali.labtest.rw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

import kigali.labtest.rw.domain.TestRequest;
import kigali.labtest.rw.repository.TestRequestRepository;

@Service // iyi i marking this class as a Sercive component
public class TestRequestService {
    @Autowired // injects iyi TestRequestRepository bean
    private TestRequestRepository repo;

    // i savinga a new test request record
    public String save(TestRequest testRequest) {
        repo.save(testRequest);
        return "Test request saved successfullly";

    }

    // iyi i fetchinga ama test requests yose from the database
    public List<TestRequest> findAll() {
        return repo.findAll();
    }

    // iyi i findinga one test request by its unique UUID key
    public Optional<TestRequest> findById(UUID id) {
        return repo.findById(id);

    }

    // iyi below ira updating details of an existing test request record
    public Optional<TestRequest> update(UUID id, TestRequest data) {
        Optional<TestRequest> optionalRequest = repo.findById(id);
        if (optionalRequest.isPresent()) {
            TestRequest existing = optionalRequest.get();
            existing.setRequestDate(data.getRequestDate());
            existing.setClinicalNotes(data.getClinicalNotes());
            existing.setExaminee(data.getExaminee());
            existing.setTechnician(data.getTechnician());
            return Optional.of(repo.save(existing));

        }

        return Optional.empty(); // iyi i returining empty container if ID didn't exist

    }

    // iyi nayo ira deleting a tesr request of its ID matches
    public boolean delete(UUID id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }

        return false;
    }
}
