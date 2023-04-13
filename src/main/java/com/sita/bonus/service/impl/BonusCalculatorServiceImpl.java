package com.sita.bonus.service.impl;

import com.sita.bonus.dtos.request.Bonus;
import com.sita.bonus.dtos.response.Employee;
import com.sita.bonus.ropo.BonusRepo;
import com.sita.bonus.service.BonusCalculatorService;
import com.sita.bonus.service.EmployeeMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class BonusCalculatorServiceImpl implements BonusCalculatorService {

    @Autowired
    private EmployeeMapperService service;

    @Autowired
    private BonusRepo repo;

    @Override
    public Map<String, List<Employee>> calculate(List<Bonus> requests) {

        persistTheRequest(requests);

        List<Bonus> activeEmployee = requests.stream().filter(e -> isActive(e.getJoiningDate(), e.getExitDate())).toList();

        return service.map(activeEmployee);
    }

    private void persistTheRequest(List<Bonus> requests) {
        List<com.sita.bonus.entity.Bonus> bonusList = requests.stream()
                .map(e -> com.sita.bonus.entity.Bonus.builder()
                        .empName(e.getEmpName())
                        .amount(e.getAmount())
                        .currency(e.getCurrency())
                        .exitDate(e.getExitDate())
                        .joiningDate(e.getJoiningDate())
                        .build())
                .toList();

        this.repo.saveAll(bonusList);
    }

    private boolean isActive(LocalDate joinDate, LocalDate exitDate) {

        LocalDate todayDate = LocalDate.now();

        return exitDate.isAfter(todayDate) && joinDate.isBefore(todayDate);
    }
}
