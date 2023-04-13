package com.sita.bonus.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
public class Bonus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50)
    private String empName;
    @Nonnull
    private Long amount;
    @Nonnull
    private String currency;
    @Nonnull
    private LocalDate joiningDate;
    private LocalDate exitDate;

}
