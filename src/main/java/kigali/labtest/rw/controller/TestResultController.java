package kigali.labtest.rw.controller;

import kigali.labtest.rw.domain.TestResult;
import kigali.labtest.rw.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

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
}