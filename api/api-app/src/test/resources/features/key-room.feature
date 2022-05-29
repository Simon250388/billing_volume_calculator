Feature: Работа с помещениями

  @success
  Scenario: Получение пустого списка помещений
    When Пользователь отправляет запрос получения списка помещений
    Then ответ не содержит ошибок
    And полученный список помещений пуст

#  @fail
#  Scenario Template: Ошибка валидации при создании помещения
#    When Пользователь отправляет запрос создания помещений c параметрами
#      | Address   | roomTypeId   | countResident   | countSubscribed   | countOwner   | square   |
#      | <Address> | <roomTypeId> | <countResident> | <countSubscribed> | <countOwner> | <square> |
#    Then ответ содержит ошибку 404
#    And описание ошибки содержит ошибку валидации "address"
#    And описание ошибки содержит ошибку валидации "countOwner"
#    And описание ошибки содержит ошибку валидации "square"
#    Examples:
#      | Address | roomTypeId | countResident | countSubscribed | countOwner | square |
#      | [blank] | 1          | 1             | 1               | 0          | 0      |

  @success
  Scenario Template: Успешное создание помещения
    When Пользователь отправляет запрос создания помещений c параметрами
      | Address   | roomTypeId   | countResident   | countSubscribed   | countOwner   | square   |
      | <Address> | <roomTypeId> | <countResident> | <countSubscribed> | <countOwner> | <square> |
    Then ответ не содержит ошибок
    And созданное помещение имеет значение полей
      | Address   | roomTypeId   | countResident   | countSubscribed   | countOwner   | square   |
      | <Address> | <roomTypeId> | <countResident> | <countSubscribed> | <countOwner> | <square> |
    Examples:
      | Address | roomTypeId | countResident | countSubscribed | countOwner | square |
      | адрес   | 1          | 1             | 1               | 1          | 50.0   |

  @success
  Scenario Template: Обновление всех полей помещения
    Given Есть помещение с параметрами
      | keyRoomId   | Address             | roomTypeId | countResident | countSubscribed | countOwner | square |
      | <keyRoomId> | Адрес до обновления | 1          | 9             | 10              | 5          | 80.0   |
    When Пользователь отправляет запрос обновление помещения с параметрами
      | keyRoomId   | Address   | roomTypeId   | countResident   | countSubscribed   | countOwner   | square   |
      | <keyRoomId> | <Address> | <roomTypeId> | <countResident> | <countSubscribed> | <countOwner> | <square> |
    Then ответ не содержит ошибок
    And созданное помещение имеет значение полей
      | keyRoomId   | Address | roomTypeId | countResident | countSubscribed | countOwner | square |
      | <keyRoomId> | адрес   | 1          | 1             | 1               | 1          | 50.0   |
    Examples:
      | keyRoomId                            | Address | roomTypeId | countResident | countSubscribed | countOwner | square |
      | 0a53e03a-1574-4e0e-b707-3e4dcd92e539 | адрес   | 1          | 1             | 1               | 1          | 50.0   |


  @success
  Scenario Template: Обновление части полей помещения
    Given Есть помещение с параметрами
      | keyRoomId   | Address             | roomTypeId | countResident | countSubscribed | countOwner | square |
      | <keyRoomId> | Адрес до обновления | 1          | 9             | 10              | 5          | 80.0   |
    When Пользователь отправляет запрос обновление помещения с параметрами
      | keyRoomId   | roomTypeId   | countResident   | countSubscribed   | countOwner   | square   |
      | <keyRoomId> | <roomTypeId> | <countResident> | <countSubscribed> | <countOwner> | <square> |
    Then ответ не содержит ошибок
    And созданное помещение имеет значение полей
      | keyRoomId   | Address             | roomTypeId | countResident | countSubscribed | countOwner | square |
      | <keyRoomId> | Адрес до обновления | 1          | 1             | 1               | 1          | 50.0   |
    Examples:
      | keyRoomId                            | roomTypeId | countResident | countSubscribed | countOwner | square |
      | 0a53e03a-1574-4e0e-b707-3e4dcd92e539 | 1          | 1             | 1               | 1          | 50.0   |

