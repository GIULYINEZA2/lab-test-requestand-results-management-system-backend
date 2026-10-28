package kigali.labtest.rw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kigali.labtest.rw.domain.Workstation;
import kigali.labtest.rw.service.WorkstationService;

import kigali.labtest.rw.service.WorkstationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController // hano rero makes this class a REST API controller
@RequestMapping("/api/workstations") // settinga the base URL path
public class WorkstationController {
    @Autowired // iyi injects the service layer logic
    private WorkstationService workstationService;

    // HTTP POST: creating ika saving a new workstation
    @PostMapping("save")
    public ResponseEntity<String> save(@RequestBody Workstation workstation) {
        String message = workstationService.save(workstation);
        return new ResponseEntity<>(message, HttpStatus.CREATED);

    }

    // http get : i fetchinga all workstations
    @GetMapping("all")
    public ResponseEntity<List<Workstation>> findAll() {
        return ResponseEntity.ok(workstationService.findAll());

    }

    // hhtp get : i fetching a single workstation by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<Workstation> optionalWorkstation = workstationService.findById(id);
        if (!optionalWorkstation.isPresent()) {
            return new ResponseEntity<>("The worksation with that id does not exist", HttpStatus.NOT_FOUND);

        }
        return ResponseEntity.ok(optionalWorkstation.get());

    }

    // hhtp put ira updating an existinf workstation by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Workstation data) {
        Optional<Workstation> optionalWorkstation = workstationService.update(id, data);
        if (!optionalWorkstation.isPresent()) {
            return new ResponseEntity<>("The workstation with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(optionalWorkstation.get());

    }

    // http delete ; i removing a workstation by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        if (!workstationService.delete(id)) {
            return new ResponseEntity<>("The workstation with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(" workstation deleted successfully");
    }

}
