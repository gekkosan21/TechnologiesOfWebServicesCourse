
-- Создание таблицы
CREATE TABLE Comics (
    ID INT PRIMARY KEY,
    Title VARCHAR(100),
    Author VARCHAR(50),
    Genre VARCHAR(50),
    Year INT,
    ISBN VARCHAR(50)
);

-- Вставка данных
INSERT INTO Comics (ID, Title, Author, Genre, Year, ISBN) VALUES
(1, 'Batman: Year One', 'Frank Miller', 'Superhero', 1987, '978-1401207526'),
(2, 'Watchmen', 'Alan Moore', 'Superhero', 1986, '978-0930289232'),
(3, 'V for Vendetta', 'Alan Moore', 'Dystopian', 1988, '978-1401207922'),
(4, 'Saga, Vol. 1', 'Brian K. Vaughan', 'Science Fiction', 2012, '978-1607066019'),
(5, 'Maus', 'Art Spiegelman', 'Historical', 1991, '978-0394747231');