package kigali.labtest.rw.service;

import kigali.labtest.rw.domain.RequestStatus;
import kigali.labtest.rw.repository.ExamineeRepository;
import kigali.labtest.rw.repository.TechnicianRepository;
import kigali.labtest.rw.repository.TestRequestRepository;
import kigali.labtest.rw.repository.TestResultRepository;
import kigali.labtest.rw.repository.WorkstationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private ExamineeRepository examineeRepo;

    @Autowired
    private TechnicianRepository technicianRepo;

    @Autowired
    private WorkstationRepository workstationRepo;

    @Autowired
    private TestRequestRepository testRequestRepo;

    @Autowired
    private TestResultRepository testResultRepo;

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("examinees", examineeRepo.count());
        summary.put("technicians", technicianRepo.count());
        summary.put("freeWorkstations", (long) workstationRepo.findFree().size());
        summary.put("requestsToday", testRequestRepo.countByRequestDate(LocalDate.now()));
        summary.put("requested", testRequestRepo.countByStatus(RequestStatus.REQUESTED));
        summary.put("results", testResultRepo.count());
        return summary;
    }
}