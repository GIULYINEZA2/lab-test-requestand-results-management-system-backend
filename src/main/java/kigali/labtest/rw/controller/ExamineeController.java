package kigali.labtest.rw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kigali.labtest.rw.domain.Examinee;
import kigali.labtest.rw.service.ExamineeService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController // iyi rero makes this class a REST API controller
@RequestMapping("/api/examinees") // iyi nayo i setting the base URL path for examinee endpoints
public class ExamineeController {
    @Autowired // iyi ifasha to inject the service layer logic
    private ExamineeService examineeService;
    // Http post; i creating ikana saving a new examinee record

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Examinee examinee) {
        String message = examineeService.save(examinee);
        return new ResponseEntity<>(message, HttpStatus.CREATED);

    }

    // Http Fet iyi i fetching all examinees
    @GetMapping("/all")
    public ResponseEntity<List<Examinee>> findAll() {
        return ResponseEntity.ok(examineeService.findAll());
    }

    // Http Get iyi i Fetching a single examinee by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<Examinee> optionalExaminee = examineeService.findById(id);
        if (!optionalExaminee.isPresent()) {
            return new ResponseEntity<>("The examinee with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(optionalExaminee.get());

    }

    // Http Delete iyi i removing ikanasiba an examinee by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        if (!examineeService.delete(id)) {
            return new ResponseEntity<>("The examinee with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Examinee deleted successfully");
    }

    // B1 HTTP Get iFinding examinees by matching their last name via a query
    // parameter
    @GetMapping("/by-last-name")
    public ResponseEntity<List<Examinee>> findByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(examineeService.findByLastName(lastName));
    }

}
