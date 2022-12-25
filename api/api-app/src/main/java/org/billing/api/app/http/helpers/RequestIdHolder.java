package org.billing.api.app.http.helpers;

import java.util.Optional;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class RequestIdHolder {
    private static final ThreadLocal<RequestId> current = new ThreadLocal<>();

    public static void set(RequestId requestId) {
        current.set(requestId);
    }

    public static RequestId get() {
        return Optional.ofNullable(current.get()).orElse(new RequestId());
    }

    public static void remove() {
        current.remove();
    }
}
