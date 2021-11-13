package org.billing.bot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class NewKeyRoomAction implements BotAction {
  @Override
  public SendMessage getMessage(Long chartId) {
    return SendMessage.builder().chatId(chartId.toString()).text("метод пока не реализован").build();
  }

  @Override
  public Action getAction() {
    return Action.CREATE_KEY_ROOM;
  }
}
