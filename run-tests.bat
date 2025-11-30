@echo off
REM === Установка переменной окружения для Gmail App Password ===
set ALLURE_MAIL_PASSWORD=ltuvvvjdcaqlhzwf

REM === Запуск автотестов через Maven ===
mvn clean test

REM === Сообщение для пользователя ===
echo =========================================
echo ✅ Тесты завершены. Allure-отчёт должен быть отправлен на почту.
echo =========================================

pause