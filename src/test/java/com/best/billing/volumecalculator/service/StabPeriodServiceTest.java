package com.best.billing.volumecalculator.service;

import com.best.billing.servicebuilder.models.catalog.AccountingPoint;
import com.best.billing.servicebuilder.models.catalog.Service;
import com.best.billing.servicebuilder.models.historychange.AccountingPointMeterState;
import com.best.billing.volumecalculator.model.StabPeriod;
import com.best.billing.volumecalculator.repository.StabPeriodRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

class StabPeriodServiceTest {

    @Autowired
    private StabPeriodRepository repository;

    @Test
    void calculateVolume() {
        long dayOfMonth = 31;
        Random random = new Random();
        Stream<StabPeriod> stabPeriodStream = Stream.of(StabPeriod.builder()
                .id(random.nextLong())
                .accountingPoint(
                        AccountingPoint.builder()
                                .id(random.nextLong())
                                .description(randomString(random))
                                .build()
                )
                .service(
                        Service.builder()
                                .id(random.nextLong())
                                .description(randomString(random))
                                .build()
                )
                .accountingPointMeterState(
                        AccountingPointMeterState.builder()
                                .id(random.nextLong())

                                .build()
                )
                .calculationPeriod(new Date(random.nextLong()))
                .build()
        );

        stabPeriodStream
                .forEach(value -> {
                    long coefficientPeriod = dayOfMonth;
                    StabPeriod previous = value.getPrevious();
                    if (previous != null)
                        coefficientPeriod = ((previous.getCalculationPeriod().getTime() - value.getRegistrationPeriod().getTime()) / (86400 * 1000));

                    long volume = 50 * value.getRoomOwner().getOwnerCount() * coefficientPeriod / dayOfMonth;
                });

    }

    private String randomString(Random random) {
        final byte[] array = new byte[50];
        random.nextBytes(array);
        return new String(array, Charset.forName("UTF-8")); // or base32()

    }
}
