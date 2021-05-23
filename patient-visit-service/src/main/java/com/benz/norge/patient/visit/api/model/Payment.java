package com.benz.norge.patient.visit.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Payment {

    private int id;
    private String date;
    private String visitedId;
    private String patientName;
    private String physicianName;
}
