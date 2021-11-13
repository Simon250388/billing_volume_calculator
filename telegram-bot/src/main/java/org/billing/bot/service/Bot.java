package org.billing.bot.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Bot extends TelegramLongPollingBot {

  private final MessageRouter messageRouter;

  @Qualifier("menuAction")
  private final BotAction menuAction;

  @Value("${telegram.botName}")
  private String botName;

  @Value("${telegram.token}")
  private String botToken;

  @Override
  public String getBotUsername() {
    return botName;
  }

  @Override
  public String getBotToken() {
    return botToken;
  }

  @Override
  @SneakyThrows
  public void onUpdateReceived(Update update) {

    if (update.hasCallbackQuery()) {
      Optional<BotAction> optAction = messageRouter.getAction(update.getCallbackQuery().getData());
      SendMessage message =
          optAction
              .orElse(menuAction)
              .getMessage(update.getCallbackQuery().getMessage().getChatId());
      execute(message);
      return;
    }

    execute(menuAction.getMessage(update.getMessage().getChatId()));
  }
}
