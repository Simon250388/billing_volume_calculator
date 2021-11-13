package org.billing.bot.service;

public enum Action {
  CREATE_KEY_ROOM("/create_new_key_room", "Добавить помещение"),
  SEND_METER_VALUE("/send_meter_value", "Передать показания"),
  MENU("/menu", "");

  public String getCallbackData() {
    return callbackData;
  }

  private final String callbackData;
  private final String label;

  public String getLabel() {
    return label;
  }

  Action(String callbackData, String label) {
    this.callbackData = callbackData;
    this.label = label;
  }
}
