package com.sita.bonus.service.impl;

import com.sita.bonus.dtos.request.Bonus;
import com.sita.bonus.dtos.response.Employee;
import com.sita.bonus.ropo.BonusRepo;
import com.sita.bonus.service.EmployeeMapperService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BonusCalculatorServiceImplTest {

    @Mock
    private BonusRepo repo;

    @Mock
    private EmployeeMapperService employeeMapperService;

    @InjectMocks
    private BonusCalculatorServiceImpl service;

    @Test
    public void testCalculate() {
        // given
        List<Bonus> requests = new ArrayList<>();
        requests.add(new Bonus("Aniket Kumar", 1000L, "INR", LocalDate.of(2021, 1, 1), LocalDate.of(2022, 1, 1)));
        requests.add(new Bonus("Raushan Singh", 2000L, "USD", LocalDate.of(2021, 1, 1), LocalDate.of(2024, 1, 1)));
        requests.add(new Bonus("Deepak Rathod", 3000L, "USD", LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1)));

        Map<String, List<Employee>> expected = new HashMap<>();
        expected.put("USD", List.of(new Employee("Raushan Singh", 2000L)));

        when(repo.saveAll(anyList())).thenReturn(null);
        when(employeeMapperService.map(anyList())).thenReturn(expected);

        // when
        Map<String, List<Employee>> result = service.calculate(requests);

        // then
        assertEquals(1, result.size());
        assertEquals(1, result.get("USD").size());


        verify(repo, times(1)).saveAll(anyList());
    }

    @Test
    public void testCalculate_withEmptyList() {
        // given
        List<Bonus> requests = new ArrayList<>();

        // when
        Map<String, List<Employee>> result = service.calculate(requests);

        // then
        assertEquals(0, result.size());
    }

}