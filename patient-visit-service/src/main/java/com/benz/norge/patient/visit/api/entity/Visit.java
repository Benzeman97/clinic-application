package com.benz.norge.patient.visit.api.entity;

import com.benz.norge.patient.visit.api.db.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "visit",schema = Schema.PATIENT_DB)
@Document(collection = "visit")
@Getter
@Setter
public class Visit {

    @Id
    @Column(name = "visited_id")
    private String visitedId;
    @Column(name = "reason",nullable = false)
    private String reason;
    @Column(name = "visit_date_time",nullable = false)
    private String visitDateTime;
    @Column(name = "created_by",nullable = false)
    private String createdBy;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "created_date_time",nullable = false)
    private LocalDateTime createdDateTime;
    @Column(name = "modified_date_time")
    private LocalDateTime modifiedDateTime;

    @ManyToOne(targetEntity = Physician.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "reg_no",referencedColumnName = "reg_no")
    private Physician physician;

    @ManyToOne(targetEntity = Patient.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id",referencedColumnName = "patient_id")
    private Patient patient;

}
