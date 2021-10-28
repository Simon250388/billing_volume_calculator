package org.billing.accountingpoints.validator;

import java.util.UUID;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.billing.accountingpoints.request.AccountingPointRequest;
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
@ContextConfiguration(
    classes = {AccountingPointRequestValidator.class})
@Tag("small")
@Import(AccountingPointRequestValidatorTest.TestConfig.class)
class AccountingPointRequestValidatorTest {

  @TestConfiguration
  public static class TestConfig {
    @Bean
    public javax.validation.Validator validator() {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      return factory.getValidator();
    }
  }

  @Autowired private AccountingPointRequestValidator validator;

  @Test
  void validateWhenIdIsNullAndDescriptionIsBlank() {

    final AccountingPointRequest request = AccountingPointRequest.builder().build();

    final DataBinder dataBinder = new DataBinder(request);

    dataBinder.addValidators(validator);

    dataBinder.validate();

    final BindingResult bindingResult = dataBinder.getBindingResult();

    Assertions.assertAll(
        () -> Assertions.assertTrue(bindingResult.hasErrors()),
        () -> Assertions.assertEquals(1, bindingResult.getErrorCount()),
        () ->
            Assertions.assertTrue(
                "when id is null then name must be not empty"
                    .equalsIgnoreCase(bindingResult.getAllErrors().get(0).getCode())));
  }

  @Test
  void validateWhenIdIsNullAndDescriptionIsNotBlank() {
    final AccountingPointRequest request =
        AccountingPointRequest.builder().name("test").build();
    final DataBinder dataBinder = new DataBinder(request);
    dataBinder.addValidators(validator);
    dataBinder.validate();

    final BindingResult bindingResult = dataBinder.getBindingResult();

    Assertions.assertFalse(bindingResult.hasErrors());
  }

  @Test
  void validateWhenIdIsNotNull() {
    final AccountingPointRequest request =
        AccountingPointRequest.builder().id(UUID.randomUUID()).name("test").build();
    final DataBinder dataBinder = new DataBinder(request);
    dataBinder.addValidators(validator);
    dataBinder.validate();

    final BindingResult bindingResult = dataBinder.getBindingResult();

    Assertions.assertFalse(bindingResult.hasErrors());
  }
}
