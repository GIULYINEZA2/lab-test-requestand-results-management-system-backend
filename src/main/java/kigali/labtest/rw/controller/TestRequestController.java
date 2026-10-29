package kigali.labtest.rw.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import kigali.labtest.rw.domain.TestRequest;
import kigali.labtest.rw.service.TestRequestService;

@RestController // Iyi mArks this class nka Rest api controller
@RequestMapping("/api/test-requests") // iyi nayo ni base url for all test request endpoints
public class TestRequestController {
    @Autowired // injects the TestRequestService bean
    private TestRequestService testRequestService;

    // Http post nawe i creates and saves a new test request record
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody TestRequest testRequest) {
        String message = testRequestService.save(testRequest);
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

    // Http delete niyo gusiga or removes a test request from the databse by Id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        if (!testRequestService.delete(id)) {
            return new ResponseEntity<>("The test request with thta id is not there", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok("Test request deleted successfully");
    }

}
