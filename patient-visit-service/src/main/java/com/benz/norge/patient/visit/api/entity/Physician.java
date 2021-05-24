package com.benz.norge.patient.visit.api.entity;

import com.benz.norge.patient.visit.api.db.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PHYSICIAN",schema = Schema.PATIENT_DB)
@Document(collection = "physician")
@Getter
@Setter
public class Physician {

    @Id
    @Column(name = "reg_no")
    private String regNo;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "created_by",nullable = false)
    private String createdBy;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "created_date_time")
    private LocalDateTime createdDateTime;
    @Column(name = "modified_date_time")
    private LocalDateTime modifiedDateTime;

   /*  bi-directional
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "physician")
    private List<Visit> visits;*/

}
