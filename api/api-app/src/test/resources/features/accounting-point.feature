Feature: Работа с точками учета

  @success
  Scenario Template: Получение пустого списка точек учета
    Given Есть помещение с ключом <keyRoomId>
    When Пользователь отправляет запрос получения списка точек учета <keyRoomId>
    Then ответ не содержит ошибок
    And полученный список точек учета пуст
    Examples:
      | keyRoomId                              |
      | "dc6a54cc-9708-43e7-a7b0-1b172d3e5c71" |

  @fail
  Scenario Template: Ошибка валидации при создании точки учета без прибора учета
    When Пользователь отправляет запрос создания точки учета c параметрами
      | keyRoomId   | serviceId   | providerId   | active | meterIsActive |
      | <keyRoomId> | <serviceId> | <providerId> | true   | false         |
    Then ответ содержит ошибку 404
    And описание ошибки содержит ошибку валидации "Поставщик не найден"
    And описание ошибки содержит ошибку валидации "Услуга не найдена"
    And описание ошибки содержит ошибку валидации "Помещение не найдено"
    Examples:
      | keyRoomId                              | serviceId                              | providerId                             |
      | "dc6a54cc-9708-43e7-a7b0-1b172d3e5c71" | "7fba434f-7542-4201-a7c4-e22a06ae307c" | "fa8fcd9d-997b-479f-bd11-a7e60f8ca076" |

  @success
  Scenario Template: Создание точки учета без прибора учета
    Given Есть помещение с ключом <keyRoomId>
    And Есть услуга с ключом <serviceId>
    And Есть поставщик с ключом <providerId>
    When Пользователь отправляет запрос создания точки учета c параметрами
      | keyRoomId   | serviceId   | providerId   | active   | meterIsActive   |
      | <keyRoomId> | <serviceId> | <providerId> | <active> | <meterIsActive> |
    Then ответ не содержит ошибок
    And Созданная точка учета имеет значение полей
      | keyRoomId   | serviceId   | providerId   | active   | meterIsActive   |
      | <keyRoomId> | <serviceId> | <providerId> | <active> | <meterIsActive> |
    And У созданной точки учета заполненно свойство id
    Examples:
      | keyRoomId                              | serviceId                              | providerId                             | active | meterIsActive |
      | "dc6a54cc-9708-43e7-a7b0-1b172d3e5c71" | "7fba434f-7542-4201-a7c4-e22a06ae307c" | "fa8fcd9d-997b-479f-bd11-a7e60f8ca076" | true   | false         |

  @fail
  Scenario Template: Ошибка обновлении не существующей точки учета
    Given Есть помещение с ключом <keyRoomId>
    And Есть услуга с ключом <serviceId>
    When Пользователь отправляет запрос обновления точки учета c параметрами
      | AccountingPointId   | keyRoomId   | serviceId   |
      | <AccountingPointId> | <keyRoomId> | <serviceId> |
    Then ответ содержит ошибку 404
    And описание ошибки содержит ошибку "Точка учета не найдена"
    Examples:
      | AccountingPointId                      | keyRoomId                              | serviceId                              |
      | "dc6a54cc-9708-43e7-a7b0-1b172d3e5c71" | "dc6a54cc-9708-43e7-a7b0-1b172d3e5c71" | "7fba434f-7542-4201-a7c4-e22a06ae307c" |

  @success
  Scenario Template: Обновление всех полей точки учета
    Given Есть помещение с ключом <keyRoomId>
    And Есть точка учета с ключом <AccountingPointId> в помещении <keyRoomId>
    And Есть услуга с ключом <serviceId>
    And Есть поставщик с ключом <providerId>
    When Пользователь отправляет запрос обновления точки учета c параметрами
      | AccountingPointId   | keyRoomId   | serviceId   | providerId   | active   | meterIsActive   |
      | <AccountingPointId> | <keyRoomId> | <serviceId> | <providerId> | <active> | <meterIsActive> |
    Then ответ не содержит ошибок
    And точка учета имеет значение полей
      | keyRoomId   | serviceId   | providerId   | active   | meterIsActive   |
      | <keyRoomId> | <serviceId> | <providerId> | <active> | <meterIsActive> |
    Examples:
      | AccountingPointId                      | keyRoomId                              | serviceId                              | providerId                             | active | meterIsActive |
      | "6b6700a2-6826-4361-9e28-1be9cadcc84c" | "6b6700a2-6826-4361-9e28-1be9cadcc84c" | "7fba434f-7542-4201-a7c4-e22a06ae307c" | "fa8fcd9d-997b-479f-bd11-a7e60f8ca076" | true   | false         |

  @success
  Scenario Template: Обновление части полей точки учета
    Given Есть помещение с ключом <keyRoomId>
    And Есть точка учета с ключом <AccountingPointId> в помещении <keyRoomId>
    And Есть услуга с ключом <serviceId>
    And Есть поставщик с ключом <providerId>
    When Пользователь отправляет запрос обновления точки учета c параметрами
      | AccountingPointId   | keyRoomId   | serviceId   |
      | <AccountingPointId> | <keyRoomId> | <serviceId> |
    When Пользователь отправляет запрос обновления точки учета c параметрами
      | AccountingPointId   | keyRoomId   | providerId   |
      | <AccountingPointId> | <keyRoomId> | <providerId> |
    When Пользователь отправляет запрос обновления точки учета c параметрами
      | AccountingPointId   | keyRoomId   | active   |
      | <AccountingPointId> | <keyRoomId> | <active> |
    When Пользователь отправляет запрос обновления точки учета c параметрами
      | AccountingPointId   | keyRoomId   | meterIsActive   |
      | <AccountingPointId> | <keyRoomId> | <meterIsActive> |
    Then ответ не содержит ошибок
    And точка учета имеет значение полей
      | keyRoomId   | serviceId   | providerId   | active   | meterIsActive   |
      | <keyRoomId> | <serviceId> | <providerId> | <active> | <meterIsActive> |
    Examples:
      | AccountingPointId                      | keyRoomId                              | serviceId                              | providerId                             | active | meterIsActive |
      | "6b6700a2-6826-4361-9e28-1be9cadcc84c" | "6b6700a2-6826-4361-9e28-1be9cadcc84c" | "7fba434f-7542-4201-a7c4-e22a06ae307c" | "fa8fcd9d-997b-479f-bd11-a7e60f8ca076" | true   | false         |
