package com.benz.norge.patient.visit.api.entity;


import com.benz.norge.patient.visit.api.db.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "HOLIDAY",schema = Schema.PATIENT_DB)
@Getter
@Setter
public class Holiday {

    @Id
    @Column(name = "HOLIDAY_DATE")
    private Date holidayDate;
    @Column(name = "HOLIDAY_NAME",nullable = false)
    private String holidayName;
    @Column(name = "CREATED_BY",nullable = false)
    private String createdBy;
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "CREATED_DATE_TIME",nullable = false)
    private LocalDateTime createdDateTime;
    @Column(name = "MODIFIED_DATE_TIME")
    private LocalDateTime modifiedDateTime;

}
