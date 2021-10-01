
Feature: Подключение услуги помещения

  @success
  Scenario Template: Подключение прибора учета на точку учета без прибора учета
    Given есть ключ помещения <КлючПомещения>
    And есть точка учета с ключом <КлючТочкиУчета>
    And есть услуга с ключом <КлючУслуги>
    And есть прибор учета с ключом <КлючПрибораУчета>
    And у ключа помещения <КлючПомещения> на точке учета с ключом <КлючТочкиУчета> подключена услуга с ключом <КлючУслуги>
    When я отправляю запрос подключения прибора учета с параметрами <КлючПомещения> <КлючТочкиУчета> <КлючПрибораУчета>
    Then последний ответ не содержит ошибок
    Examples:
      | КлючПомещения | КлючТочкиУчета | КлючУслуги | КлючПрибораУчета |
      | 1             | 1              | 1          | 1                |


  @fail
  Scenario Template: Подключение прибора учета на точку учета с подключенным прибора учета
    Given есть ключ помещения <КлючПомещения>
    And есть точка учета с ключом <КлючТочкиУчета>
    And есть услуга с ключом <КлючУслуги>
    And есть прибор учета с ключом <КлючПрибораУчета>
    And у ключа помещения <КлючПомещения> на точке учета с ключом <КлючТочкиУчета> подключена услуга с ключом <КлючУслуги>
    And у ключа помещения <КлючПомещения> на точке учета с ключом <КлючТочкиУчета> подключен прибор учета с ключом <КлючПрибораУчета>
    When я отправляю запрос подключения прибора учета с параметрами <КлючПомещения> <КлючТочкиУчета> <КлючПрибораУчета>
    Then последний ответ не содержит ошибок
    Examples:
      | КлючПомещения | КлючТочкиУчета | КлючУслуги | КлючПрибораУчета |
      | 1             | 1              | 1          | 1                |

  @success
  Scenario Template: Отключение прибора учета на точке учета с подключенным прибора учета
    Given есть ключ помещения <КлючПомещения>
    And есть точка учета с ключом <КлючТочкиУчета>
    And есть услуга с ключом <КлючУслуги>
    And есть прибор учета с ключом <КлючПрибораУчета>
    And у ключа помещения <КлючПомещения> на точке учета с ключом <КлючТочкиУчета> подключена услуга с ключом <КлючУслуги>
    And у ключа помещения <КлючПомещения> на точке учета с ключом <КлючТочкиУчета> подключен прибор учета с ключом <КлючПрибораУчета>
    When я отправляю запрос вывода прибора учета с параметрами <КлючПомещения> <КлючТочкиУчета> <КлючПрибораУчета>
    Then последний ответ не содержит ошибок
    Examples:
      | КлючПомещения | КлючТочкиУчета | КлючУслуги | КлючПрибораУчета |
      | 1             | 1              | 1          | 1                |

  @success
  Scenario Template: Отключение прибора учета на точке учета с отключенным прибором учета
    Given есть ключ помещения <КлючПомещения>
    And есть точка учета с ключом <КлючТочкиУчета>
    And есть услуга с ключом <КлючУслуги>
    And есть прибор учета с ключом <КлючПрибораУчета>
    And у ключа помещения <КлючПомещения> на точке учета с ключом <КлючТочкиУчета> подключена услуга с ключом <КлючУслуги>
    When я отправляю запрос вывода прибора учета с параметрами <КлючПомещения> <КлючТочкиУчета> <КлючПрибораУчета>
    Then последний ответ не содержит ошибок
    Examples:
      | КлючПомещения | КлючТочкиУчета | КлючУслуги | КлючПрибораУчета |
      | 1             | 1              | 1          | 1                |
