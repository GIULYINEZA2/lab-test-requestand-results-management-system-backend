package kigali.labtest.rw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import kigali.labtest.rw.domain.RequestStatus;
import kigali.labtest.rw.domain.TestRequest;
import kigali.labtest.rw.service.TestRequestService;
import kigali.labtest.rw.service.TestResultService;

@RestController // Iyi mArks this class nka Rest api controller
@RequestMapping("/api/test-requests") // iyi nayo ni base url for all test request endpoints
public class TestRequestController {
    @Autowired // injects the TestRequestService bean
    private TestRequestService testRequestService;

    // Http post nawe i creates and saves a new test request record
    // A2 + D1
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody TestRequest testRequest) {
        String message = testRequestService.save(testRequest);
        if (message.contains("past")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        if (message.contains("already has")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    // Http get i fetchinga a list ya all test requests
    @GetMapping("/all")
    public ResponseEntity<List<TestRequest>> findAll() {
        return ResponseEntity.ok(testRequestService.findAll());

    }

    // Http get here i fetchinga a spcific test request by its ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<TestRequest> optionalRequest = testRequestService.findById(id);
        if (!optionalRequest.isPresent()) {
            return new ResponseEntity<>("The test request with that id does not exist", HttpStatus.NOT_FOUND);

        }
        return ResponseEntity.ok(optionalRequest.get());

    }

    // Http put nayo updates an existing test request's data by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody TestRequest data) {
        Optional<TestRequest> optionalRequest = testRequestService.update(id, data);
        if (!optionalRequest.isPresent()) {
            return new ResponseEntity<>("The test request wth that id doesn't exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(optionalRequest.get());

    }

    @Autowired
    private TestResultService testResultService;

    // Http delete niyo gusiga or removes a test request from the databse by Id
    // A2 + E3
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        Optional<TestRequest> optionalRequest = testRequestService.findById(id);
        if (!optionalRequest.isPresent()) {
            return new ResponseEntity<>("The test request with that id does not exist", HttpStatus.NOT_FOUND);
        }

        if (testResultService.existsByTestRequest(optionalRequest.get())) {
            return new ResponseEntity<>("That test request has test results, delete them first", HttpStatus.CONFLICT);
        }

        testRequestService.delete(id);
        return ResponseEntity.ok("Test request deleted successfully");
    }

    // B2 Test requests by status
    @GetMapping("/by-status")
    public ResponseEntity<List<TestRequest>> findByStatus(@RequestParam RequestStatus status) {
        return ResponseEntity.ok(testRequestService.findByStatus(status));
    }

    // B3 Test requests in a date range

    @GetMapping("/between")
    public ResponseEntity<List<TestRequest>> findBetween(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return ResponseEntity.ok(testRequestService.findBetween(startDate, endDate));
    }

    // D2 kureba Status flow

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable UUID id, @RequestParam RequestStatus status) {
        String message = testRequestService.updateStatus(id, status);
        if (message.contains("does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

        }

        if (message.contains("Cannot change")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

        }

        return ResponseEntity.ok(message);

    }

    // F1
    @GetMapping("/stats/by-status")
    public ResponseEntity<List<Object[]>> statsByStatus() {
        return ResponseEntity.ok(testRequestService.countByStatus());
    }

    // F2
    @PatchMapping("/cancel-day")
    public ResponseEntity<String> cancelDay(@RequestParam UUID technicianId, @RequestParam String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return ResponseEntity.ok(testRequestService.cancelDay(technicianId, parsedDate));
    }

    // Bonus: pagination, e.g. ?page=0&size=5&sort=requestDate,desc
    @GetMapping("/page")
    public ResponseEntity<Page<TestRequest>> page(@RequestParam int page, @RequestParam int size,
            @RequestParam String sort) {
        String[] parts = sort.split(",");
        String propertyName = parts[0];

        Sort.Direction direction = Sort.Direction.ASC;
        if (parts.length > 1 && parts[1].equalsIgnoreCase("desc")) {
            direction = Sort.Direction.DESC;
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, propertyName));
        return ResponseEntity.ok(testRequestService.findPage(pageable));
    }
}
