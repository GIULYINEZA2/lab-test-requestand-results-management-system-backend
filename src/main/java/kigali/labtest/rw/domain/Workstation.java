package kigali.labtest.rw.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "workstation")
public class Workstation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int workstationNumber;
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getWorkstationNumber() {
        return workstationNumber;
    }

    public void setWorkstationNumber(int workstationNumber) {
        this.workstationNumber = workstationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}