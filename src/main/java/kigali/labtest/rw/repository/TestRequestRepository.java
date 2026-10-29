package kigali.labtest.rw.repository;

import kigali.labtest.rw.domain.TestRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TestRequestRepository extends JpaRepository<TestRequest, UUID> {

}
