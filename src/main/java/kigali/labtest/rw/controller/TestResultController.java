package kigali.labtest.rw.controller;

import kigali.labtest.rw.domain.TestResult;
import kigali.labtest.rw.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test-results")
public class TestResultController {

    @Autowired
    private TestResultService testResultService;

    // E1
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID testRequestId, @RequestBody TestResult result) {
        String message = testResultService.save(testRequestId, result);
        if (message.contains("does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        if (message.contains("only for PROCESSED")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    // E2
    @GetMapping("/examinee/{hospitalNumber}")
    public ResponseEntity<List<TestResult>> findByExaminee(@PathVariable String hospitalNumber) {
        return ResponseEntity.ok(testResultService.findByExamineeHospitalNumber(hospitalNumber));
    }

    // E3
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        Optional<TestResult> optionalResult = testResultService.findById(id);
        if (!optionalResult.isPresent()) {
            return new ResponseEntity<>("The test result with that id does not exist", HttpStatus.NOT_FOUND);
        }
        testResultService.delete(id);
        return ResponseEntity.ok("Test result deleted successfully");
    }
}