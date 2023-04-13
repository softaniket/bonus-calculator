package com.sita.bonus.service.impl;

import com.sita.bonus.dtos.request.Bonus;
import com.sita.bonus.dtos.response.Employee;
import com.sita.bonus.service.EmployeeMapperService;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class EmployeeMapperServiceImpl implements EmployeeMapperService {
    @Override
    public Map<String, List<Employee>> map(List<Bonus> bonuses) {

        return bonuses.stream()
                .collect(Collectors.groupingBy(Bonus::getCurrency,
                                Collectors.mapping(bonus -> new Employee(bonus.getEmpName(), bonus.getAmount()),
                                        Collectors.collectingAndThen(Collectors.toList(),
                                                employees -> {
                                                    employees.sort(Comparator.comparing(Employee::getEmpName));
                                                    return employees;
                                                }
                                        )
                                )
                        )
                );
    }
}
