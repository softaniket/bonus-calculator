package com.sita.bonus.dtos.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sita.bonus.utils.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bonus {
    private String empName;
    private Long amount;
    private String currency;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate joiningDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate exitDate;
}
