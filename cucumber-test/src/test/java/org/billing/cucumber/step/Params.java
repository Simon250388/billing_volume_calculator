package org.billing.cucumber.step;

import io.cucumber.java.ParameterType;
import java.util.UUID;

public class Params {
    @ParameterType(".*")
    public UUID uuid(String uuid) {
        return UUID.fromString(uuid);
    }
}
