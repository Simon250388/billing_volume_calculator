package org.billing.api.app.controller;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.billing.api.app.useCase.calculation.CalculationRequest;
import org.billing.api.app.useCase.calculation.CalculationResponse;
import org.billing.api.app.useCase.calculation.CalculationUseCaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculation")
@Validated
@RequiredArgsConstructor
public class CalculationController {

    private final CalculationUseCaseService calculationUseCaseService;

    @GetMapping("/{keyRoomId}")
    public ResponseEntity<Collection<CalculationResponse>> getAll(@PathVariable String keyRoomId) {
        return calculationUseCaseService.getAll(keyRoomId);
    }

    @PostMapping
    public ResponseEntity<CalculationResponse> save(@RequestBody CalculationRequest request) {
        return calculationUseCaseService.save(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalculationResponse> update(@PathVariable String id, @RequestBody CalculationRequest request) {
        return calculationUseCaseService.update(id, request);
    }

}
