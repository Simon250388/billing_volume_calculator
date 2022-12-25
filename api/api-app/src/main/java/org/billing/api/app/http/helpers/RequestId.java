package org.billing.api.app.http.helpers;

import java.util.concurrent.ThreadLocalRandom;

public class RequestId {
    public String getBase() {
        return base;
    }

    protected final String base;

    public RequestId(String base) {
        this.base = base;
    }

    public RequestId() {
        this.base = generate();
    }

    private static String generate() {
        final ThreadLocalRandom current = ThreadLocalRandom.current();

        return String.format("%s/%016x%016x", System.currentTimeMillis(), current.nextLong(), current.nextLong());
    }
}
