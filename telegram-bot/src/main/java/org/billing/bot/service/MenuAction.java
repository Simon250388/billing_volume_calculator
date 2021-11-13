package org.billing.bot.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Component
@Qualifier("menuAction")
public class MenuAction implements BotAction {

  @Override
  public SendMessage getMessage(Long chartId) {

    final List<InlineKeyboardButton> actions =
        Arrays.stream(Action.values())
            .filter(action -> action != Action.MENU)
            .map(
                action ->
                    InlineKeyboardButton.builder()
                        .callbackData(action.getCallbackData())
                        .text(action.getLabel())
                        .build())
            .collect(Collectors.toList());

    return SendMessage.builder()
        .text("Выберете операцию")
        .chatId(chartId.toString())
        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(List.of(actions)).build())
        .build();
  }

  @Override
  public Action getAction() {
    return Action.MENU;
  }
}
