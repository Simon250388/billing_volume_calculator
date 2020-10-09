package com.best.billing.volumecalculator.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ResponseDTO<T extends BaseEntityDTO> {


    private T data;
    private Map<String, List<BaseCatalogDTO>> views;
    private List<String> errors;

    public ResponseDTO() {
        this.views = Collections.emptyMap();
        this.errors = new ArrayList<>();
    }

    public ResponseDTO(T data, Map<String, List<BaseCatalogDTO>> views, List<String> errors) {
        this.data = data;
        this.views = views;
        this.errors = errors;
    }

    public ResponseDTO(T data, Map<String, List<BaseCatalogDTO>> views) {
        this.data = data;
        this.views = views;
        this.errors = new ArrayList<>();
    }

    public ResponseDTO(T data) {
        this.data = data;
        this.views = Collections.emptyMap();
        this.errors = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, List<BaseCatalogDTO>> getViews() {
        return views;
    }

    public void setViews(Map<String, List<BaseCatalogDTO>> views) {
        this.views = views;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }


}
