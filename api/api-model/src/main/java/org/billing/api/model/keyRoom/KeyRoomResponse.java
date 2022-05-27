package org.billing.api.model.keyRoom;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = KeyRoomResponse.KeyRoomResponseBuilder.class)
public class KeyRoomResponse {
    String id;
    String address;
    Long roomTypeId;
    Long countResident;
    Long countSubscribed;
    Long countOwner;
    BigDecimal square;
    @JsonPOJOBuilder(withPrefix = "")
    public static class KeyRoomResponseBuilder {}
}
