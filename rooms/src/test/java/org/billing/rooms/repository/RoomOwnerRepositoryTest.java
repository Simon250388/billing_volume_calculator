package org.billing.rooms.repository;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.liquibase.enabled=false",
        "spring.jpa.show-sql=true",
        "spring.jpa.properties.hibernate.format_sql=true",
})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
@DatabaseSetup("/db/common.xml")
class RoomOwnerRepositoryTest {
    @Autowired
    private RoomOwnerRepository repository;


//    @Test
//    void when_Table_Is_Empty_Then_FindOneLastByKeyRoomId_Should_Be_Empty() {
//        throw new UnsupportedOperationException("RoomOwnerRepositoryTest::when_Table_Is_Empty_Then_FindOneLastByKeyRoomId_Should_Be_Empty");
//    }
//
//    @Test
//    void when_Table_Contains_One_Row_Then_FindOneLastByKeyRoomId_Be_Return_Existing_Row() {
//        throw new UnsupportedOperationException("RoomOwnerRepositoryTest::when_Table_Contains_One_Row_Then_FindOneLastByKeyRoomId_Be_Return_Existing_Row");
//
//    }
//
//    @Test
//    void when_Table_Contains_Many_Rows_Then_FindOneLastByKeyRoomId_Should_Be_Return_Row_Which_Have_Max_Period() {
//        throw new UnsupportedOperationException("RoomOwnerRepositoryTest::when_Table_Contains_Many_Rows_Then_FindOneLastByKeyRoomId_Should_Be_Return_Row_Which_Have_Max_Period");
//    }

//    @Test
//    void when_Table_Contains_Many_Rows_Then_FindAllByKeyRoom_Id_Should_Be_Return_All_Rows() {
//        throw new UnsupportedOperationException("RoomOwnerRepositoryTest::when_Table_Contains_Many_Rows_Then_FindAllByKeyRoom_Id_Should_Be_Return_All_Rows");
//    }
}
