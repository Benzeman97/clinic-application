package com.benz.norge.patient.visit.api.entity;


import com.benz.norge.patient.visit.api.db.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "holiday",schema = Schema.PATIENT_DB)
@Document(collection = "holiday")
@Getter
@Setter
public class Holiday {

    @Id
    @Column(name = "holiday_date")
    private String holidayDate;
    @Column(name = "holiday_name",nullable = false)
    private String holidayName;
    @Column(name = "created_by",nullable = false)
    private String createdBy;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "created_date_time",nullable = false)
    private LocalDateTime createdDateTime;
    @Column(name = "modified_date_time")
    private LocalDateTime modifiedDateTime;

}
