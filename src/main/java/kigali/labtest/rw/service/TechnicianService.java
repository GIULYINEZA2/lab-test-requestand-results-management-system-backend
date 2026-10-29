package kigali.labtest.rw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.labtest.rw.controller.TechnicianController;
import kigali.labtest.rw.domain.LabSection;
import kigali.labtest.rw.domain.Technician;
import kigali.labtest.rw.repository.LabSectionRepository;
import kigali.labtest.rw.repository.TechnicianRepository;
import kigali.labtest.rw.repository.WorkstationRepository;
import kigali.labtest.rw.domain.Workstation;
import kigali.labtest.rw.controller.TechnicianController;

import java.util.UUID;
import java.util.Optional;
import java.util.List;

@Service // iyi marks this class as a Service component
public class TechnicianService {

    @Autowired // Injects database repository
    private TechnicianRepository repo;

    // i saving a new technician record
    public String save(Technician technician) {
        repo.save(technician);
        return "Technician saved successfully";

    }

    // iyingiye i fetching all technicians from the database
    public List<Technician> findAll() {
        return repo.findAll();
    }

    // finds one technicians by their hamwe na Unique UUID
    public Optional<Technician> findById(UUID id) {
        return repo.findById(id);

    }

    // iyi below updates details of an existing technician
    public Optional<Technician> update(UUID id, Technician data) {
        Optional<Technician> optionalTechnician = repo.findById(id);
        if (optionalTechnician.isPresent()) {
            Technician existing = optionalTechnician.get();
            existing.setFirstName(data.getFirstName());
            existing.setLastName(data.getLastName());
            existing.setLicenceNo(data.getLicenceNo());
            existing.setWorkstation(data.getWorkstation());
            return Optional.of(repo.save(existing));
        }
        return Optional.empty();

    }

    // Ideletinga umu technician by Id if they exist
    public boolean delete(UUID id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    // C1 HANO kureba Technicians by lab section

    public List<Technician> findByLabSectionName(String name) {
        return repo.findByLabSectionName(name);
    }

    @Autowired
    private WorkstationRepository workstationRepo;

    // D4 Move a technician to another workstation

    public String changeWorkstation(UUID technicianId, int workstationNumber) {
        Optional<Technician> techOpt = repo.findById(technicianId);
        if (!techOpt.isPresent()) {
            return "The technician with that id does not exist";

        }
        Optional<Workstation> wsOpt = workstationRepo.findByWorkstationNumber(workstationNumber);
        if (!wsOpt.isPresent()) {
            return "The workstation with that workstationNumber does not exist";

        }

        Technician technician = techOpt.get();
        Workstation target = wsOpt.get();

        if (repo.existsByWorkstation(target)) {
            Workstation current = technician.getWorkstation();
            if (current == null || !target.getId().equals(current.getId())) {
                return "That workstation is taken by another technician";

            }
        }

        technician.setWorkstation(target);
        repo.save(technician);
        return "Workstation changed successfully";

    }

    // D3 gushyira one lab section to a technician

    @Autowired
    private LabSectionRepository labSectionRepo;

    public String addlabSection(UUID technicianId, UUID labSectionId) {
        Optional<Technician> techOpt = repo.findById(technicianId);
        if (!techOpt.isPresent()) {
            return "The technician with that id does not exist";
        }

        Optional<LabSection> sectionOpt = labSectionRepo.findById(labSectionId);
        if (!sectionOpt.isPresent()) {
            return "That lab section does not exist";

        }

        Technician technician = techOpt.get();
        LabSection labSection = sectionOpt.get();

        for (LabSection existing : technician.getLabSections()) {
            if (existing.getId().equals(labSection.getId())) {
                return "The technician already has that lab section";

            }
        }
        technician.getLabSections().add(labSection);
        repo.save(technician);
        return "Lab section added to the technixian";

    }
}
