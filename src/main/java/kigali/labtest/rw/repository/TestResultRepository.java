package kigali.labtest.rw.repository;

import kigali.labtest.rw.domain.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import java.util.UUID;

public interface TestResultRepository extends JpaRepository<TestResult, UUID> {

    // E2: JPQL, path navigation
    @Query("SELECT tr FROM TestResult tr WHERE tr.testRequest.examinee.hospitalNumber = ?1 ORDER BY tr.verifiedDate DESC")
    List<TestResult> findByExamineeHospitalNumber(String hospitalNumber);

}
