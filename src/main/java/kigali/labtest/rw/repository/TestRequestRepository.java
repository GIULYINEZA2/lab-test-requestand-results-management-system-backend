package kigali.labtest.rw.repository;

import kigali.labtest.rw.domain.RequestStatus;
import kigali.labtest.rw.domain.TestRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.time.LocalDate;
import java.util.List;
import kigali.labtest.rw.domain.Technician;

public interface TestRequestRepository extends JpaRepository<TestRequest, UUID> {

    // B2 Deriverd Test requests by status
    List<TestRequest> findByStatusOrderByRequestDateAsc(RequestStatus status);

    // B3 DERIVED Test requests in a date range

    List<TestRequest> findByRequestDateBetweenOrderByRequestDateAsc(LocalDate start, LocalDate end);

    // D1 Safe saving

    boolean existsByTechnicianAndRequestDateAndStatusNot(Technician technician, LocalDate requestDate,
            RequestStatus status);
}
