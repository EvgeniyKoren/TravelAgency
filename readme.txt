Проект (JEE WEB project) для IntelliJ IDEA Ultimate Edition.
TravelAgency

*********************************************************************

1. Запустить сервер MySQL.

2. Зайти в административную консоль и выполнить команду

	source ABSOLUTE_PATH_TO_SCRIPT;

где ABSOLUTE_PATH_TO_SCRIPT - абсолютный путь к файлу:

	sql/dbcreate-mysql.sql

3. Дать пользователю "root" все права на базу данных travel_agency:

	grant all privileges on travel_agency.* to root@'localhost' identified by '';
	
(выполнить в административной консоли MySQL)

4. Открыть проект в IntelliJ IDEA.

5. Сконфигурировать в IDEA Apache Tomcat.
