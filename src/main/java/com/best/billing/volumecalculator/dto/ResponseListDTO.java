package com.best.billing.volumecalculator.dto;

import com.best.billing.volumecalculator.basemodels.BaseCatalog;
import com.best.billing.volumecalculator.basemodels.BaseEntity;

import java.util.List;
import java.util.Map;

public class ResponseListDTO<T extends BaseEntity> {
    private List<T> data;
    private Map<String, List<BaseCatalogDTO>> views;
    private List<String> errors;

    public ResponseListDTO(List<T> data, Map<String, List<BaseCatalogDTO>> views, List<String> errors) {
        this.data = data;
        this.views = views;
        this.errors = errors;
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
