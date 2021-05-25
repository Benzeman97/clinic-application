package com.benz.norge.billing.api.entity;


import com.benz.norge.billing.api.db.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Table(name = "billing",schema = Schema.BILLING_DB)
@Document(collection = "billing")
@Getter
@Setter
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "billed_date_time",nullable = false)
    private LocalDateTime billedDateTime;
    @Column(name = "visited_id",nullable = false)
    private String visitedId;

}
