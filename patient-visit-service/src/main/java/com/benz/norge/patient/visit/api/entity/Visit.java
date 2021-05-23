package com.benz.norge.patient.visit.api.entity;

import com.benz.norge.patient.visit.api.db.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "VISIT",schema = Schema.PATIENT_DB)
@Getter
@Setter
public class Visit {

    @Id
    @Column(name = "VISITED_ID")
    private String visitedId;
    @Column(name = "REASON",nullable = false)
    private String reason;
    @Column(name = "VISITED_DATE_TIME",nullable = false)
    private String visitedDateTime;
    @Column(name = "CREATED_BY",nullable = false)
    private String createdBy;
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "CREATED_DATE_TIME",nullable = false)
    private LocalDateTime createdDateTime;
    @Column(name = "MODIFIED_DATE_TIME")
    private LocalDateTime modifiedDateTime;

    @ManyToOne(targetEntity = Physician.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "REG_NO",referencedColumnName = "REG_NO")
    private Physician physician;

    @ManyToOne(targetEntity = Patient.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "PATIENT_ID",referencedColumnName = "PATIENT_ID")
    private Patient patient;

}
