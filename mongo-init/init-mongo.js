db = db.getSiblingDB('tasktrackerdb');  // Создает или подключается к базе данных tasktrackerdb

// Создание коллекций
db.createCollection('user');
db.createCollection('task');
