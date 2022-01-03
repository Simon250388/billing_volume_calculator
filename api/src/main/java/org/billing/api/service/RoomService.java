package org.billing.api.service;

import org.billing.api.dto.BillingServerResponse;
import org.billing.api.dto.RoomPeopleCountRequest;
import reactor.core.publisher.Mono;

public interface RoomService {
    Mono<BillingServerResponse> savePrescribedCount(
            RoomPeopleCountRequest roomPeopleCountRequest);

    Mono<BillingServerResponse> saveOwnerCount(RoomPeopleCountRequest roomPeopleCountRequest);

    Mono<BillingServerResponse> saveRoomPeopleCountRequest(
            RoomPeopleCountRequest roomPeopleCountRequest);
}
