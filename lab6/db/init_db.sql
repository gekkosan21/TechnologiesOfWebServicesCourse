-- Создание таблицы
CREATE TABLE Comics (
    ID SERIAL PRIMARY KEY,
    Title VARCHAR(100),
    Author VARCHAR(50),
    Genre VARCHAR(50),
    Year INT,
    ISBN VARCHAR(50)
);

-- Вставка данных
INSERT INTO Comics (Title, Author, Genre, Year, ISBN) VALUES
('Batman: Year One', 'Frank Miller', 'Superhero', 1987, '978-1401207526'),
('Watchmen', 'Alan Moore', 'Superhero', 1986, '978-0930289232'),
('V for Vendetta', 'Alan Moore', 'Dystopian', 1988, '978-1401207922'),
('Saga, Vol. 1', 'Brian K. Vaughan', 'Science Fiction', 2012, '978-1607066019'),
('Maus', 'Art Spiegelman', 'Historical', 1991, '978-0394747231'),
('The Sandman: Preludes & Nocturnes', 'Neil Gaiman', 'Fantasy', 1989, '978-1401225759'),
('Spider-Man: Blue', 'Jeph Loeb', 'Superhero', 2002, '978-0785110713'),
('X-Men: Days of Future Past', 'Chris Claremont', 'Superhero', 1981, '978-0785164532'),
('Batman: The Killing Joke', 'Alan Moore', 'Superhero', 1988, '978-0930289454'),
('Black Hole', 'Charles Burns', 'Horror', 2005, '978-0375727191'),
('Persepolis', 'Marjane Satrapi', 'Autobiography', 2000, '978-0375714573'),
('Y: The Last Man, Vol. 1', 'Brian K. Vaughan', 'Post-Apocalyptic', 2002, '978-1401202323'),
('Bone: Out from Boneville', 'Jeff Smith', 'Fantasy', 2005, '978-0439706230'),
('Hellboy: Seed of Destruction', 'Mike Mignola', 'Supernatural', 1994, '978-1593070941'),
('Locke & Key: Welcome to Lovecraft', 'Joe Hill', 'Horror', 2008, '978-1600102370'),
('Scott Pilgrim’s Precious Little Life', 'Bryan Lee O’Malley', 'Romantic Comedy', 2004, '978-1932664089'),
('Ms. Marvel, Vol. 1: No Normal', 'G. Willow Wilson', 'Superhero', 2014, '978-0785190210'),
('The Walking Dead, Vol. 1: Days Gone Bye', 'Robert Kirkman', 'Zombie', 2004, '978-1582406725');

