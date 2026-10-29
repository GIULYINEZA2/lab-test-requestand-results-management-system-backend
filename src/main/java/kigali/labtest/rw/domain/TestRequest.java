package kigali.labtest.rw.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "test_request")
public class TestRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate requestDate;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    private String clinicalNotes;
    @ManyToOne
    @JoinColumn(name = "examinee_id")
    private Examinee examinee;
    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getClinicalNotes() {
        return clinicalNotes;
    }

    public void setClinicalNotes(String clinicalNotes) {
        this.clinicalNotes = clinicalNotes;
    }

    public Examinee getExaminee() {
        return examinee;
    }

    public void setExaminee(Examinee examinee) {
        this.examinee = examinee;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

}
