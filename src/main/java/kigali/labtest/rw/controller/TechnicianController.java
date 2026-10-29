package kigali.labtest.rw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kigali.labtest.rw.service.TechnicianService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;
import java.util.UUID;
import java.util.Optional;
import kigali.labtest.rw.domain.Technician;

@RestController // iyi i marking this class nka rest api controller
@RequestMapping("/api/technicians") // iyi i setting the base url oath for all endpoints in this controller
public class TechnicianController {
    @Autowired // iyi injects the sercice layer logic
    private TechnicianService technicianService;

    // Http post ikorea create and save a new technician record
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Technician technician) {
        String message = technicianService.save(technician);
        return new ResponseEntity<>(message, HttpStatus.CREATED);

    }

    // Http get fectches a liste of all technicians
    @GetMapping("/all")
    public ResponseEntity<List<Technician>> findAll() {
        return ResponseEntity.ok(technicianService.findAll());
    }

    // Http get fectches a single technician ukoreshehe Unique Id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<Technician> optionalTechnician = technicianService.findById(id);
        if (!optionalTechnician.isPresent()) {
            return new ResponseEntity<>("The technician withthat id does not exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(optionalTechnician.get());
    }

    // http put ikora update an existing technicians's details by their ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Technician data) {
        Optional<Technician> optionalTechnician = technicianService.update(id, data);
        if (!optionalTechnician.isPresent()) {
            return new ResponseEntity<>("The technician with that id does not exist", HttpStatus.NOT_FOUND);

        }
        return ResponseEntity.ok(optionalTechnician.get());

    }

    // Http delete i removing umu technician from the system by their ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        if (!technicianService.delete(id)) {
            return new ResponseEntity<>("The technician with that id does not exixist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Technician deleted successful");

    }

    // c1 kureba aba Technicians by lab section
    @GetMapping("/by-lab-section")
    public ResponseEntity<List<Technician>> findByLabSection(@RequestParam String name) {
        return ResponseEntity.ok(technicianService.findByLabSectionName(name));
    }

    // D4 Movinga a technician to another workstation
    @PatchMapping("/{technicianId}/workstation")
    public ResponseEntity<String> changeWorkstation(@PathVariable UUID technicianId,
            @RequestParam int workstationNumber) {
        String message = technicianService.changeWorkstation(technicianId, workstationNumber);
        if (message.contains("does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        if (message.contains("taken")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);

        }
        return ResponseEntity.ok(message);
    }

    // D3 kw'Adding one lab section to a technician
    @PutMapping("/{technician}/lab-sections/{labSectionId}")
    public ResponseEntity<String> addLabSection(@PathVariable UUID technicianId, @PathVariable UUID labSectionId) {
        String message = technicianService.addlabSection(technicianId, labSectionId);
        if (message.contains("does not exist")) {

            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

        }

        if (message.contains("already has")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);

        }
        return ResponseEntity.ok(message);

    }
}
