package kigali.labtest.rw.repository;

import kigali.labtest.rw.domain.RequestStatus;
import kigali.labtest.rw.domain.TestRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;

public interface TestRequestRepository extends JpaRepository<TestRequest, UUID> {

    // B2 Deriverd
    List<TestRequest> findByStatusOrderByRequestDateAsc(RequestStatus status);
}
