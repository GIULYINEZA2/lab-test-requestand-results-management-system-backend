package kigali.labtest.rw.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

import kigali.labtest.rw.domain.LabSection;
import kigali.labtest.rw.service.LabSectionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController // iyi i marking this class as a Rest Api controller
@RequestMapping("/api/lab-sections") // IYi ni BASE URL path ya all endpoints muri iyi class
public class LabSectionController {
    @Autowired // ceci injects the service layer logic
    private LabSectionService labSectionService;

    // Http Post ifasha gu creating no gu saving a new lab section
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody LabSection labSection) {
        String message = labSectionService.save(labSection);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    // Http Get idufasha ku fetching a list of all lab sections
    @GetMapping("/all")
    public ResponseEntity<List<LabSection>> findAll() {
        return ResponseEntity.ok(labSectionService.findAll());

    }

    // Http get fetches a specific lab section by its ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<LabSection> optionalSection = labSectionService.findById(id);
        if (!optionalSection.isPresent()) {
            return new ResponseEntity<>("The lab section with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(optionalSection.get());
    }

    // Iyi HTTP put iri for updating an existing lab section's data by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody LabSection data) {
        Optional<LabSection> optionalSection = labSectionService.update(id, data);
        if (!optionalSection.isPresent()) {
            return new ResponseEntity<>("The lab section with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(optionalSection.get());

    }

    // Http delete iri for removing a lab section from the database by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        if (!labSectionService.delete(id)) {
            return new ResponseEntity<>("The lab section with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Lab section deleted successful");
    }

}
