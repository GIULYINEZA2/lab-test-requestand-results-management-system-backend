package kigali.labtest.rw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

import kigali.labtest.rw.domain.TestRequest;
import kigali.labtest.rw.repository.TestRequestRepository;

@Service
public class TestRequestService {
    @Autowired
    private TestRequestRepository repo;

    public String save(TestRequest testRequest) {
        repo.save(testRequest);
        return "Test request saved successfullly";

    }

    public List<TestRequest> findAll() {
        return repo.findAll();
    }

    public Optional<TestRequest> findById(UUID id) {
        return repo.findById(id);

    }

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

        return Optional.empty();

    }

    public boolean delete(UUID id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }

        return false;
    }
}
