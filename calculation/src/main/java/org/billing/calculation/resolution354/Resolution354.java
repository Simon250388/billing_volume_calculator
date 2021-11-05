package org.billing.calculation.resolution354;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.billing.calculation.resolution.CalculationRule;
import org.billing.calculation.resolution.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Resolution354 implements Resolution {

  private final List<CalculationRule> rules;
}
