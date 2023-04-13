package com.sita.bonus.service.impl;

import com.sita.bonus.dtos.request.Bonus;
import com.sita.bonus.dtos.response.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EmployeeMapperServiceImplTest {

    @InjectMocks
    private EmployeeMapperServiceImpl employeeMapperService;

    @Test
    void mapTest() {
        // Create a mock Bonus object
        Bonus bonus = Mockito.mock(Bonus.class);
        Mockito.when(bonus.getEmpName()).thenReturn("John");
        Mockito.when(bonus.getAmount()).thenReturn(1000L);
        Mockito.when(bonus.getCurrency()).thenReturn("USD");

        // Create a list of bonuses
        List<Bonus> bonuses = List.of(bonus);

        // Create the expected result
        List<Employee> employees = List.of(new Employee("John", 1000L));
        Map<String, List<Employee>> expectedResult = Map.of("USD", employees);

        // Create the object to be tested and invoke the method
        Map<String, List<Employee>> actualResult = employeeMapperService.map(bonuses);

        // Verify the results
        assertEquals(expectedResult.size(), actualResult.size());
    }
}