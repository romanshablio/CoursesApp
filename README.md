В данном проекте используются следующие методики и парадигмы программирования:

1. Объектно-ориентированное программирование (ООП):
   - Инкапсуляция: использование data классов для моделей (Course)
   - Наследование: расширение базовых классов Android (Activity, Fragment)
   - Полиморфизм: переопределение методов (onCreateView, onBindViewHolder)

2. SOLID принципы:
   - Single Responsibility: каждый класс отвечает за одну функциональность
   - Open/Closed: возможность расширения функционала без изменения существующего кода
   - Dependency Inversion: использование интерфейсов и абстракций

3. Паттерны проектирования:
   - Adapter Pattern: для RecyclerView (CoursesAdapter)
   - Observer Pattern: для обработки событий пользовательского интерфейса
   - Builder Pattern: использование GsonBuilder для настройки парсинга JSON

4. Архитектурные подходы:
   - ViewBinding: для безопасного доступа к элементам интерфейса
   - Fragment-based Navigation: для навигации между экранами
   - Resource Management: структурированное хранение ресурсов (layouts, drawables)

5. Функциональное программирование:
   - Лямбда-выражения
   - Функции высшего порядка (map, filter)
   - Неизменяемые коллекции (immutable lists)

6. Принципы чистого кода:
   - Говорящие имена методов и переменных
   - Единая ответственность методов
   - Комментирование сложных участков кода
   - Структурированная организация пакетов

7. Работа с данными:
   - JSON парсинг с использованием Gson
   - Конвертация дат
   - Обработка ошибок через try-catch
   - Логирование важных событий

8. Material Design:
   - Использование современных компонентов пользовательского интерфейса
   - Следование гайдлайнам Material Design
   - Адаптивная верстка

Это базовый Android-проект, который демонстрирует основные принципы современной разработки мобильных приложений с использованием Kotlin.
