package com.sita.bonus.service;

import com.sita.bonus.dtos.request.Bonus;
import com.sita.bonus.dtos.response.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeMapperService {
        Map<String, List<Employee>> map(List<Bonus> bonuses);
}
