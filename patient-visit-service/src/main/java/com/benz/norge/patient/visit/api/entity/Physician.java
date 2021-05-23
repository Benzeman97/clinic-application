package com.benz.norge.patient.visit.api.entity;

import com.benz.norge.patient.visit.api.db.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PHYSICIAN",schema = Schema.PATIENT_DB)
@Getter
@Setter
public class Physician {

    @Id
    @Column(name = "REG_NO")
    private String regNo;
    @Column(name = "NAME",nullable = false)
    private String name;
    @Column(name = "CREATED_BY",nullable = false)
    private String createdBy;
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "CREATED_DATE_TIME")
    private LocalDateTime createdDateTime;
    @Column(name = "MODIFIED_DATE_TIME")
    private LocalDateTime modifiedDateTime;

   /*  bi-directional
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "physician")
    private List<Visit> visits;*/

}
