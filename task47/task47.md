# Задание 47

## Отчёт

### Обозревайте не более 200-400 строк кода за раз

Чаще всего этот показатель у нас меньше 200. Стараемся делать изменения маленькими порциями.
При необходимости сделать ревью, к примеру, нового сервиса, делаем это за несколько подходов.

### Придерживайтесь темпа не более 300-500 строк кода/час

Да, так по сути у нас и есть. В день проводим до 3 ревью. Как правило - это 1-2 ревью.

### Отведите достаточно времени для вдумчивого и неспешного просмотра, однако не более 60-90 минут

Не всегда получается. Но в целом стараемся не спешить и проверять основные возможные "грабли".

### Авторов кода следует принуждать к комментированию/документированию кода до начала code review

Комментарии в любом виде не приветствуются, они загрязняют код и являются причиной конфузов и дефектов. Это не относится
к документированию кода (JavaCode).

Перед ревью автор смотрит финально изменения уже в Bitbucket, в самом Pull Request. По сути это собственное code review.
И да, это часто помогает выявлять косяки.

### Устанавливайте количественно измеряемые цели в обзорах кода и фиксируйте метрики, чтобы улучшать этот процесс

Это требование для идеального мира. Ни в одной организации за свою практику я не видел подобного анализа и метрик.
Хотелось бы это иметь, но действующий уровень культуры программирования пока не позволяет этой идеи воплотиться в жизнь.

### Чек-листы существенно улучшают результаты как для программистов, так и для инспекторов

Использую личные чек-листы. Пришёл к этому итерационно. Сильно облегчают жизнь и ощутимо повышают качество
изготовляемого продукта. Другим рекомендую, но мало кто пользуется.

### Убедитесь, что баги действительно исправлены

Да, используем Bitbucket. Пока достаточно, чтобы отследить что было найдено и пофикшено.

### Формированию правильной культуры обзоров кода

Наши менеджеры проектов далеки от кухни программирования. Мы самоорганизовались и между командами наладили практику
совместного code review, описали правила его проведения.

### Остерегайтесь эффекта "Большого брата"

Тут приходится искать некий компромисс между процессами, принятыми на проекте, эффективностью работы и отчетностью.

### Эффект эго

Да, этот механизм хорошо работает в моём случае.

### Облегчённые обзоры кода всё равно остаются эффективны

Да, с некоторого момента всегда использую SonarQube для выявления стандартных косяков.

Система bug-трекинга, конечно, тоже сильно помогает оперативно проводить code review и фиксировать нужные моменты.