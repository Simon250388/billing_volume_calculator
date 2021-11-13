package org.billing.bot.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageRouter {

  private final List<BotAction> actions;

  public Optional<BotAction> getAction(String message) {
    return actions.stream()
        .filter(action -> action.getAction().getCallbackData().equalsIgnoreCase(message))
        .findFirst();
  }
}
