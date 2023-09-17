package org.billing.api.app.controller;

import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.billing.api.app.useCase.meterValues.MeterRequest;
import org.billing.api.app.useCase.meterValues.MeterResponse;
import org.billing.api.app.useCase.meterValues.MeterValueRequest;
import org.billing.api.app.useCase.meterValues.MeterValueResponse;
import org.billing.api.app.useCase.meterValues.MeterValueUseCaseService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/meters")
@Validated
@RequiredArgsConstructor
public class MeterValueController {

    private final MeterValueUseCaseService meterValueUseCaseService;

    @GetMapping("/{keyRoomId}")
    public ResponseEntity<Collection<MeterResponse>> getAll(@PathVariable String keyRoomId) {
        return meterValueUseCaseService.getAll(keyRoomId);
    }

    @GetMapping("/{keyRoomId}/{meterId}")
    public ResponseEntity<List<String>> getMeterValueHistory(
            @PathVariable(name = "keyRoomId") String keyRoomId,
            @PathVariable(name = "meterId") String meterId
    ) {
        return meterValueUseCaseService.getMeterValueHistory(keyRoomId, meterId);
    }

    @PutMapping
    public ResponseEntity<MeterResponse> save(@RequestBody MeterRequest request) {
        return meterValueUseCaseService.save(request);
    }

    @PostMapping("/values")
    public ResponseEntity<MeterValueResponse> saveValue(@RequestBody MeterValueRequest request) {
        return meterValueUseCaseService.saveValue(request);
    }
}
