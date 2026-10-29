package kigali.labtest.rw.repository;

import kigali.labtest.rw.domain.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TestResultRepository extends JpaRepository<TestResult, UUID> {
}