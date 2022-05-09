package com.edudev.msworker.entities;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tb_worker")
public final class Worker {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(nullable = false, updatable = false)
    private final Long id;

    @Column(length = 60, unique = true, nullable = false)
    private String name;

    @Column(scale = 2, precision = 5, nullable = false)
    private BigDecimal dailyIncome;

    private Worker() {
        this.id = null;
    }

    public Worker(final Long id, final String name, final BigDecimal dailyIncome) {
        this.id = id;
        this.name = name;
        this.dailyIncome = dailyIncome;
    }

    public Long getId() { return id;}
    public String getName() { return name; }
    public BigDecimal getDailyIncome() { return dailyIncome; }
}
