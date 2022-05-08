package com.edudev.msworker.entities;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "tb_worker")
public final class Worker {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(columnDefinition = "uuid", nullable = false, updatable = false)
    @ColumnDefault("random_uuid()")
    private final UUID id;

    @Column(length = 60, unique = true, nullable = false)
    private String name;

    @Column(scale = 2, precision = 5, nullable = false)
    private BigDecimal dailyIncome;

    private Worker() {
        this.id = null;
    }

    public Worker(final UUID id, final String name, final BigDecimal dailyIncome) {
        this.id = id;
        this.name = name;
        this.dailyIncome = dailyIncome;
    }

    public UUID getId() { return id;}
    public String getName() { return name; }
    public BigDecimal getDailyIncome() { return dailyIncome; }
}
