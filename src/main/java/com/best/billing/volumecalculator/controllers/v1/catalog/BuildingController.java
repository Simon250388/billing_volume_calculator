package com.best.billing.volumecalculator.controllers.v1.catalog;

import com.best.billing.volumecalculator.dto.ResponseListDTO;
import com.best.billing.volumecalculator.dto.catalog.BuildingDTO;
import com.best.billing.volumecalculator.services.catalog.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/building")
public class BuildingController {
    private final BuildingService service;

    @Autowired
    public BuildingController(BuildingService service) {
        this.service = service;
    }

    @GetMapping("/{fndStr}")
    public ResponseEntity<ResponseListDTO<BuildingDTO>> doGetBySubstring(@PathVariable String fndStr) {
        return new ResponseEntity<>(
                new ResponseListDTO<>(service.findByDescriptionContaining(fndStr)),
                HttpStatus.OK);
    }
}
