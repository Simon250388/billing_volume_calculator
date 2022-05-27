package org.billing.api.model.keyRoom;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = KeyRoomRequest.KeyRoomRequestBuilder.class)
public class KeyRoomRequest {
    @NotEmpty(groups = KeyRoomCreateRequestValidationGroup.class)
    String address;
    @NotNull(groups = KeyRoomCreateRequestValidationGroup.class)
    Long roomTypeId;
    @NotNull(groups = KeyRoomCreateRequestValidationGroup.class)
    Long countResident;
    @NotNull(groups = KeyRoomCreateRequestValidationGroup.class)
    Long countSubscribed;
    @NotNull(groups = KeyRoomCreateRequestValidationGroup.class)
    @Min(value = 1, message = "Количество собственников не может быть меньше 1", groups = KeyRoomCreateRequestValidationGroup.class)
    @Min(value = 1, message = "Количество собственников не может быть меньше 1")
    Long countOwner;
    @NotNull(groups = KeyRoomCreateRequestValidationGroup.class)
    @DecimalMin(value = "1", message = "Площадь не может быть меньше 1", groups = KeyRoomCreateRequestValidationGroup.class)
    @DecimalMin(value = "1", message = "Площадь не может быть меньше 1")
    BigDecimal square;

    @JsonPOJOBuilder(withPrefix = "")
    public static class KeyRoomRequestBuilder {
    }

    public interface KeyRoomCreateRequestValidationGroup {
    }
}


