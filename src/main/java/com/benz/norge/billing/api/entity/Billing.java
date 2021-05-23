package com.benz.norge.billing.api.entity;


import com.benz.norge.billing.api.db.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BILLING",schema = Schema.BILLING_DB)
@Getter
@Setter
public class Billing {

    @Id
    @SequenceGenerator(name = "BILLING_ID_GEN",sequenceName = Schema.BILLING_DB+".BILLING_ID_SEQ",initialValue = 1006,allocationSize = 1)
    @GeneratedValue(generator = "BILLING_ID_GEN",strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private long id;
    @Column(name = "BILLED_DATE_TIME",nullable = false)
    private LocalDateTime billedDateTime;
    @Column(name = "visited_id",nullable = false)
    private String visitedId;

}
