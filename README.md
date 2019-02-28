# Checklist with NaB

## server
Сервер сделан с использованием Nuts and Bolts

## client
Клиент написан с использованием Angular 7

## Запуск
Проверить настройки подключения к postgres в src/etc/nab-todo/service.properties
```
cd ./client
npm install -g @angular/cli
ng build --prod

cd ../server
mvn clean compile exec:java
```

