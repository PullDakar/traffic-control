При запуске

- проверить, что файл с БД создался (в домашней директории должен появиться файл monitoring.mv.db)
- проверить доступ к консоли h2 (localhost:8080/h2)
- Создать таблицу:
```h2
CREATE TABLE tutorials_tbl ( 
   id INT NOT NULL, 
   title VARCHAR(50) NOT NULL, 
   author VARCHAR(20) NOT NULL, 
   submission_date DATE 
);
```