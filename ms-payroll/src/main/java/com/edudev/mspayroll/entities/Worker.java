package com.edudev.mspayroll.entities;

import java.math.BigDecimal;
import java.util.UUID;

public record Worker(
        UUID id,
        String name,
        BigDecimal dailyIncome
) {  }
