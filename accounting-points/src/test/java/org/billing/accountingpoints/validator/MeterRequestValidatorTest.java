package org.billing.accountingpoints.validator;

import java.util.UUID;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.billing.accountingpoints.request.MeterRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MeterRequestValidator.class})
@Tag("small")
@Import(MeterRequestValidatorTest.TestConfig.class)
class MeterRequestValidatorTest {

    @Autowired
    private MeterRequestValidator validator;

  @TestConfiguration
  public static class TestConfig {
    @Bean
    public javax.validation.Validator validator() {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      return factory.getValidator();
    }
  }

  @Test
  void validateWhenIdIsNullAndFactoryNumberIsBlank() {

        final MeterRequest request = MeterRequest.builder().build();

        final DataBinder dataBinder = new DataBinder(request);

        dataBinder.addValidators(validator);

        dataBinder.validate();

        final BindingResult bindingResult = dataBinder.getBindingResult();

        Assertions.assertAll(
                () -> Assertions.assertTrue(bindingResult.hasErrors()),
                () -> Assertions.assertEquals(1, bindingResult.getErrorCount()),
                () ->
                        Assertions.assertTrue(
                                "when id is null then factory_number must be not empty"
                                        .equalsIgnoreCase(bindingResult.getAllErrors().get(0).getCode())));
    }

  @Test
  void validateWhenIdIsNullAndFactoryNumberIsNotPattern() {

        final MeterRequest request = MeterRequest.builder().factoryNumber("test").build();

        final DataBinder dataBinder = new DataBinder(request);

        dataBinder.addValidators(validator);

        dataBinder.validate();

        final BindingResult bindingResult = dataBinder.getBindingResult();

        Assertions.assertAll(
                () -> Assertions.assertTrue(bindingResult.hasErrors()),
                () -> Assertions.assertEquals(1, bindingResult.getErrorCount()),
                () ->
                        Assertions.assertTrue(
                                "must be patter [0-9]{1,5}"
                                        .equalsIgnoreCase(bindingResult.getAllErrors().get(0).getCode())));
    }

  @Test
  void validateWhenIdIsNullAndFactoryNumberIsNotBlank() {
        final MeterRequest request =
                MeterRequest.builder().factoryNumber("12345").build();
        final DataBinder dataBinder = new DataBinder(request);
        dataBinder.addValidators(validator);
        dataBinder.validate();

        final BindingResult bindingResult = dataBinder.getBindingResult();

        Assertions.assertFalse(bindingResult.hasErrors());
    }

  @Test
  void validateWhenIdIsNotNull() {
        final MeterRequest request =
                MeterRequest.builder().id(UUID.randomUUID()).factoryNumber("12345").build();
        final DataBinder dataBinder = new DataBinder(request);
        dataBinder.addValidators(validator);
        dataBinder.validate();

        final BindingResult bindingResult = dataBinder.getBindingResult();

        Assertions.assertFalse(bindingResult.hasErrors());
    }
}
