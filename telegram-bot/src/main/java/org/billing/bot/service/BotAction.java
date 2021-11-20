package org.billing.bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface BotAction {
    SendMessage getMessage(Long chartId);
    Action getAction();
}
