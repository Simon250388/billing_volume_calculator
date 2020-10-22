package com.best.billing.volumecalculator.repositories.catalog;

import com.best.billing.volumecalculator.models.catalog.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {
}
