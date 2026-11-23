package com.example.backend3nareplica.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ConsumoResponse(
        LocalDate data,
        BigDecimal calorias,
        BigDecimal proteinas,
        BigDecimal carbos,
        BigDecimal gorduras
) {}
