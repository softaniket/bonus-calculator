package com.sita.bonus.dtos.request;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BonusRequest {
    private List<@NonNull @Valid Bonus> bonus;
}
