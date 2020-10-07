package com.best.billing.volumecalculator.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ResponseListDTO<T extends BaseEntityDTO> {
    private List<T> data;
    private Map<String, List<BaseCatalogDTO>> views;
    private List<String> errors;

    public ResponseListDTO(List<T> data, Map<String, List<BaseCatalogDTO>> views, List<String> errors) {
        this.data = data;
        this.views = views;
        this.errors = errors;
    }

    public ResponseListDTO(List<T> data, Map<String, List<BaseCatalogDTO>> views) {
        this.data = data;
        this.views = views;
        this.errors = new ArrayList<>();
    }

    public ResponseListDTO(List<T> data) {
        this.data = data;
        this.views = Collections.emptyMap();
        this.errors = new ArrayList<>();
    }



    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
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
