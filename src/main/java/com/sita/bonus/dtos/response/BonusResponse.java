package com.sita.bonus.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class BonusResponse {
    private String currency;
    private List<Employee> employees;
}
