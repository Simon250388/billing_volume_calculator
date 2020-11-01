package com.best.billing.common.controllers.v1;

import com.best.billing.base.dto.ResponseListDTO;
import com.best.billing.common.dto.BuildingDTO;
import com.best.billing.common.services.catalog.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/building")
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
