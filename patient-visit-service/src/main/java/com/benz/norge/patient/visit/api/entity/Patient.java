package com.benz.norge.patient.visit.api.entity;

import com.benz.norge.patient.visit.api.db.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PATIENT",schema = Schema.PATIENT_DB)
@Getter
@Setter
public class Patient {

    @Id
    @Column(name = "PATIENT_ID")
    private String patientId;
    @Column(name = "PATIENT_NAME",nullable = false)
    private String patientName;
    @Column(name = "GENDER",nullable = false)
    private String gender;
    @Column(name = "AGE",nullable = false)
    private int age;
    @Column(name = "CREATED_BY",nullable = false)
    private String createdBy;
    @Column(name = "CREATED_DATE_TIME")
    private LocalDateTime createdDateTime;
    @Column(name = "MODIFIED_DATE_TIME")
    private LocalDateTime modifiedDateTime;

   /* bi-directional
   @OneToMany(cascade = CascadeType.ALL,mappedBy = "patient")
    private List<Visit> visits;*/
}
