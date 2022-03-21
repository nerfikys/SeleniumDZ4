#language: ru
@all
Функционал: Ипотека на готовые квартиры

  @Test
  Сценарий: Ипотека на готовые квартиры и проверка ошибки
    * Закрываем куки
    * Проверка что это стартовая страница
    * Выбираем "Ипотека" в главном меню
    * Выбираем "Ипотека на готовое жильё" в подменю главного меню
    * Проверка что это вторая страница
    * Заполняем поля формы:
      | Стоимость недвижимости | 5180000 |
      | Первоначальный взнос   | 3058000 |
      | Срок кредита           | 30      |
    * Заполняем чекбоксы формы:
      | Электронная       | false |
      | Страхование жизни | false |
      | Электронная       | true  |
    * Проверяем поля формы:
      | Сумма кредита      | 2122000 |
      | Ежемесячный платеж | 35459   |
      | Необходимый доход  | 49643   |
      | Процентная ставка  | 11      |
