package org.billing.api.model.validator;

public interface ValueExistChecker {
    boolean exist(String value);

    ExistValueType getValueType();
}
