package com.best.billing.servicebuilder.repository.historychange;

import com.best.billing.common.model.Building;
import com.best.billing.common.model.DirectionOfUse;
import com.best.billing.common.model.Service;
import com.best.billing.servicebuilder.models.catalog.AccountingPoint;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoom;
import com.best.billing.servicebuilder.models.entity.AccountingPointKeyRoomServiceEntity;
import com.best.billing.servicebuilder.models.entity.KeyRoom;
import com.best.billing.servicebuilder.models.historychange.AccountingPointServiceState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class AccountingPointServiceStateRepositoryTest {

    @Autowired
    private AccountingPointServiceStateRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    void When_Row_Exist_And_Is_Active_Then_Iterator_Has_Next_Should_Be_True() {
        Service service = Service.builder().description("Service").build();
        Building building = Building.builder().description("Building").build();
        KeyRoom keyRoom = KeyRoom.builder().building(building).room(null).privateSector(true).build();
        AccountingPoint accountingPoint = AccountingPoint.builder().description("AccountingPoint").build();
        AccountingPointKeyRoom accountingPointKeyRoom = AccountingPointKeyRoom.builder().keyRoom(keyRoom).accountingPoint(accountingPoint).build();
        DirectionOfUse directionOfUse = DirectionOfUse.builder().description("directionOfUse").build();
        AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity = AccountingPointKeyRoomServiceEntity.builder()
                .accountingPointKeyRoom(accountingPointKeyRoom)
                .service(service)
                .directionOfUse(directionOfUse)
                .build();

        em.persist(service);
        em.persist(building);
        em.persist(keyRoom);
        em.persist(accountingPoint);
        em.persist(accountingPointKeyRoom);
        em.persist(directionOfUse);
        em.persist(accountingPointKeyRoomServiceEntity);

        em.persist(AccountingPointServiceState.builder()
                .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                .active(true)
                .period(LocalDateTime.now())
                .build());

        Iterable<AccountingPointServiceState> current = repository.findAllActiveByKeyRoomId(keyRoom.getId());
        assertTrue(current.iterator().hasNext());
    }

    @Test
    void When_Last_Row_IS_Not_Active_Then_Iterator_Has_Next_Should_Be_False() {
        Service service = Service.builder().description("Service").build();
        Building building = Building.builder().description("Building").build();
        KeyRoom keyRoom = KeyRoom.builder().building(building).room(null).privateSector(true).build();
        AccountingPoint accountingPoint = AccountingPoint.builder().description("AccountingPoint").build();
        AccountingPointKeyRoom accountingPointKeyRoom = AccountingPointKeyRoom.builder().keyRoom(keyRoom).accountingPoint(accountingPoint).build();
        DirectionOfUse directionOfUse = DirectionOfUse.builder().description("directionOfUse").build();
        AccountingPointKeyRoomServiceEntity accountingPointKeyRoomServiceEntity = AccountingPointKeyRoomServiceEntity.builder()
                .accountingPointKeyRoom(accountingPointKeyRoom)
                .service(service)
                .directionOfUse(directionOfUse)
                .build();

        em.persist(service);
        em.persist(building);
        em.persist(keyRoom);
        em.persist(accountingPoint);
        em.persist(accountingPointKeyRoom);
        em.persist(directionOfUse);
        em.persist(accountingPointKeyRoomServiceEntity);

        em.persist(AccountingPointServiceState.builder()
                .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                .active(true)
                .period(LocalDateTime.now())
                .build());

        em.persist(AccountingPointServiceState.builder()
                .accountingPointKeyRoomServiceEntity(accountingPointKeyRoomServiceEntity)
                .active(false)
                .period(LocalDateTime.now())
                .build());

        Iterable<AccountingPointServiceState> current = repository.findAllActiveByKeyRoomId(keyRoom.getId());
        assertFalse(current.iterator().hasNext());
    }
}
