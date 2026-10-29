package kigali.labtest.rw.service;

import kigali.labtest.rw.domain.RequestStatus;
import kigali.labtest.rw.domain.TestRequest;
import kigali.labtest.rw.domain.TestResult;
import kigali.labtest.rw.repository.TestRequestRepository;
import kigali.labtest.rw.repository.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class TestResultService {

    @Autowired
    private TestResultRepository repo;

    @Autowired
    private TestRequestRepository testRequestRepo;

    // E1
    public String save(UUID testRequestId, TestResult result) {
        Optional<TestRequest> optionalRequest = testRequestRepo.findById(testRequestId);
        if (!optionalRequest.isPresent()) {
            return "The test request with that id does not exist";
        }

        TestRequest testRequest = optionalRequest.get();
        if (testRequest.getStatus() != RequestStatus.PROCESSED) {
            return "Test results are only for PROCESSED test requests";
        }

        result.setVerifiedDate(LocalDate.now());
        result.setTestRequest(testRequest);
        repo.save(result);
        return "Test result saved successfully";
    }
}