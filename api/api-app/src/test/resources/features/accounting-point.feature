Feature: Работа с точками учета

  @success
  Scenario: Получение пустого списка точек учета
    When Пользователь отправляет запрос получения списка точек учета
    Then ответ не содержит ошибок
    And полученный список точек учета пуст


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
