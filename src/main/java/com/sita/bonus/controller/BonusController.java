package com.sita.bonus.controller;

import com.sita.bonus.dtos.request.BonusRequest;
import com.sita.bonus.dtos.response.BonusResponse;
import com.sita.bonus.dtos.response.Employee;
import com.sita.bonus.dtos.response.Response;
import com.sita.bonus.service.BonusCalculatorService;
import com.sita.bonus.validation.BonusRequestValidator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tci/bonus")
@Slf4j
public class BonusController {

    @Autowired
    private BonusCalculatorService service;
    @Autowired
    private BonusRequestValidator validator;

    @PostMapping("/eligibility")
    public ResponseEntity<Response> postBonus(@Valid @RequestBody BonusRequest requests) {


        log.info("POST REQUEST: CALCULATE BONUS");

        validator.postBonusValidation(requests);

        Map<String, List<Employee>> resultMap = service.calculate(requests.getBonus());

        return ResponseEntity.status(HttpStatus.CREATED).body(prepareBonusResponse(resultMap));
    }

    // We can have separate mapper package in real project to keep code manageable.
    private Response prepareBonusResponse(Map<String, List<Employee>> result) {
        Response response = new Response();

        List<BonusResponse> data = result.entrySet()
                .stream()
                .map(each -> BonusResponse.builder()
                        .currency(each.getKey())
                        .employees(each.getValue())
                        .build()
                ).collect(Collectors.toList());

        response.setErrorMessage("");
        response.setData(data);
        return response;
    }
}
