package kigali.labtest.rw.repository;

import kigali.labtest.rw.domain.RequestStatus;
import kigali.labtest.rw.domain.TestRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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

    // F1: JPQL
    @Query("SELECT tr.status, COUNT(tr) FROM TestRequest tr GROUP BY tr.status")
    List<Object[]> statsByStatus();

    // F2: JPQL UPDATE
    @Modifying
    @Query("UPDATE TestRequest tr SET tr.status = ?1 " +
            "WHERE tr.technician.id = ?2 AND tr.requestDate = ?3 AND tr.status != ?4")
    int cancelDay(RequestStatus rejectedStatus, UUID technicianId, LocalDate date, RequestStatus processedStatus);

    // Bonus: DERIVED countBy
    long countByStatus(RequestStatus status);

    long countByRequestDate(LocalDate requestDate);
}
