package com.benz.norge.billing.api.model;

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

    private long id;
    private String date;
    private String visitedId;
    private String patientName;
    private String physicianName;
}
