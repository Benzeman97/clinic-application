package com.benz.norge.patient.visit.api.entity;

import com.benz.norge.patient.visit.api.db.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "patient",schema = Schema.PATIENT_DB)
@Document(collection = "patient")
@Getter
@Setter
public class Patient {

    @Id
    @Column(name = "patient_id")
    private String patientId;
    @Column(name = "patient_name",nullable = false)
    private String patientName;
    @Column(name = "gender",nullable = false)
    private String gender;
    @Column(name = "age",nullable = false)
    private int age;
    @Column(name = "created_by",nullable = false)
    private String createdBy;
    @Column(name = "created_date_time")
    private LocalDateTime createdDateTime;
    @Column(name = "modified_date_time")
    private LocalDateTime modifiedDateTime;

   /* bi-directional
   @OneToMany(cascade = CascadeType.ALL,mappedBy = "patient")
    private List<Visit> visits;*/
}
