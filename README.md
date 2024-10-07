# SP_SpringBoot_AWS_Desafio_01

## Library Checkout System :closed_book:

The **Library Checkout System** is a Java-based application designed 
to manage book checkouts for a library. Built with JPA, the system 
allows the registration of books, authors, and members, as well as 
the checkout and return of books. It also generates reports on 
currently borrowed books and members with overdue books.

## Features

- Book Registration: Add new books to the library's catalog.
- Author Registration: Add new authors to the library's catalog.
- Member Registration: Register library members.
- Book Checkout: Enable members to borrow available books from the library.
- Book Return: Process the return of borrowed books.
- Borrowed Books Report: Generate a report of books that are currently checked out.
- Overdue Members Report: Generate a report of members who have overdue books.

## Technologies Used

- Java 21 LTS
- JPA for database management
- Maven for dependency management
- MySQL server

## Installation and Setup

1. Clone the repository

```bash
  git clone https://github.com/victor-pimentell/SP_SpringBoot_AWS_Desafio_01.git
```

2. Create a schema on your database called "library", here's an example on MySQL WorkBench

![persistence](./docs/schema.png)

3. Configure the persistence.xml with your database credentials.

![persistence](./docs/persistence.png)

4. Run the project so the JPA can create the tables on the database

5. After running the project go to your database manager and the SQL below to insert the test data.

<details>
  <summary>Click to expand SQL code</summary>

  ```sql
  -- Test Data
INSERT INTO author (name, biography, birthDate, nationality) VALUES 
  ('George Orwell', 'English novelist, essayist, journalist, and critic. Known for his works "1984" and "Animal Farm."', '1903-06-25', 'British'),
  ('Jane Austen', 'English novelist known primarily for her six major novels including "Pride and Prejudice" and "Sense and Sensibility."', '1775-12-16', 'British'),
  ('Mark Twain', 'American writer, humorist, entrepreneur, publisher, and lecturer, best known for "The Adventures of Tom Sawyer" and "Adventures of Huckleberry Finn."', '1835-11-30', 'American'),
  ('J.K. Rowling', 'British author, best known for writing the Harry Potter fantasy series.', '1965-07-31', 'British'),
  ('Gabriel García Márquez', 'Colombian novelist, short-story writer, screenwriter, and journalist, known for "One Hundred Years of Solitude."', '1927-03-06', 'Colombian'),
  ('Frank Herbert', 'American science fiction author, best known for the "Dune" series, one of the best-selling science fiction series in history.', '1920-10-08', 'American'),
  ('J.R.R. Tolkien', 'English writer, poet, philologist, and academic, best known for "The Hobbit" and "The Lord of the Rings."', '1892-01-03', 'British'),
  ('Isaac Asimov', 'Russian-born American author and professor of biochemistry, famous for his works of science fiction and popular science, including the "Foundation" and "Robot" series.', '1920-01-02', 'American'),
  ('Ernest Hemingway', 'American novelist, short-story writer, and journalist, known for works like "The Old Man and the Sea" and "A Farewell to Arms."', '1899-07-21', 'American'),
  ('Virginia Woolf', 'English writer, considered one of the most important modernist authors of the 20th century, known for "Mrs. Dalloway" and "To the Lighthouse."', '1882-01-25', 'British');



INSERT INTO book (isbn, publicationDate, quantity, title, author_id) VALUES 
  ('9780451524935', '1949-06-08', 1, '1984', 1),
  ('9780451526342', '1945-08-17', 1, 'Animal Farm', 1),
  ('9780141439518', '1813-01-28', 2, 'Pride and Prejudice', 2),
  ('9780141439662', '1811-10-30', 1, 'Sense and Sensibility', 2),
  ('9780486280615', '1876-06-17', 0, 'The Adventures of Tom Sawyer', 3),
  ('9780486280616', '1884-12-10', 1, 'Adventures of Huckleberry Finn', 3),
  ('9780545582889', '1997-06-26', 2, 'Harry Potter and the Philosopher''s Stone', 4),
  ('9780545582933', '1998-07-02', 1, 'Harry Potter and the Chamber of Secrets', 4),
  ('9780060883287', '1967-05-30', 1, 'One Hundred Years of Solitude', 5),
  ('9781400034710', '1985-09-01', 0, 'Love in the Time of Cholera', 5),
  ('9780441172719', '1965-08-01', 2, 'Dune', 6),
  ('9780441013593', '1976-05-01', 1, 'Children of Dune', 6),
  ('9780618640157', '1954-07-29', 1, 'The Fellowship of the Ring', 7),
  ('9780618640188', '1954-11-11', 3, 'The Two Towers', 7),
  ('9780553293357', '1951-10-01', 2, 'Foundation', 8),
  ('9780553293364', '1952-11-01', 0, 'Foundation and Empire', 8),
  ('9780684801223', '1952-09-01', 2, 'The Old Man and the Sea', 9),
  ('9780684830490', '1929-09-27', 2, 'A Farewell to Arms', 9),
  ('9780156907392', '1925-05-05', 1, 'Mrs. Dalloway', 10),
  ('9780156936026', '1927-05-05', 0, 'To the Lighthouse', 10);

INSERT INTO book_genres (book_id, genres) VALUES
  (1, 'SCIENCE_FICTION'),
  (1, 'DYSTOPIAN'),
  (2, 'ROMANCE'),
  (3, 'FICTION'),
  (3, 'ROMANCE'),
  (3, 'COMICS'),
  (4, 'ROMANCE'),
  (5, 'ADVENTURE'),
  (5, 'CLASSIC'),
  (6, 'ADVENTURE'),
  (6, 'CLASSIC'),
  (7, 'FANTASY'),
  (7, 'YOUNG_ADULT'),
  (8, 'FANTASY'),
  (8, 'YOUNG_ADULT'),
  (9, 'FICTION'),
  (10, 'ROMANCE'),
  (11, 'SCIENCE_FICTION'),
  (11, 'ADVENTURE'),
  (12, 'SCIENCE_FICTION'),
  (12, 'ADVENTURE'),
  (13, 'FANTASY'),
  (13, 'ADVENTURE'),
  (14, 'FANTASY'),
  (14, 'ADVENTURE'),
  (15, 'SCIENCE_FICTION'),
  (15, 'CLASSIC'),
  (16, 'SCIENCE_FICTION'),
  (16, 'CLASSIC'),
  (17, 'LITERARY_FICTION'),
  (17, 'CLASSIC'),
  (18, 'LITERARY_FICTION'),
  (18, 'CLASSIC'),
  (19, 'LITERARY_FICTION'),
  (20, 'LITERARY_FICTION');



INSERT INTO member (name, address, dateAssociation, email, phoneNumber) VALUES
  ('Alice Johnson', '123 Maple St, Springfield', '2022-05-14', 'alice.johnson@example.com', '555-1234'),
  ('Bob Smith', '456 Oak Ave, Greenville', '2021-07-22', 'bob.smith@example.com', '555-5678'),
  ('Catherine Lee', '789 Pine Dr, Hilltown', '2023-01-10', 'catherine.lee@example.com', '555-8765'),
  ('David Garcia', '101 Cedar Blvd, Riverdale', '2020-09-30', 'david.garcia@example.com', '555-4321'),
  ('Emily Davis', '202 Birch Rd, Meadowview', '2022-12-05', 'emily.davis@example.com', '555-6543'),
  ('Frank Harris', '303 Willow Ln, Laketown', '2021-03-19', 'frank.harris@example.com', '555-3456'),
  ('Grace Martinez', '404 Elm St, Seaview', '2020-11-22', 'grace.martinez@example.com', '555-7890'),
  ('Henry Wilson', '505 Maple St, Westport', '2023-07-12', 'henry.wilson@example.com', '555-9876'),
  ('Irene Thompson', '606 Pine Ave, Brookside', '2022-08-08', 'irene.thompson@example.com', '555-5432'),
  ('Jack Clark', '707 Oak Dr, Woodville', '2021-04-25', 'jack.clark@example.com', '555-2109');

INSERT INTO checkout (checkoutDate, dueDate, checkoutState, fine, book_id, member_id) VALUES
  ('2024-09-25', '2024-09-30', 0, 0.00, 1, 1),
  ('2024-10-06', '2024-10-11', 0, 0.00, 2, 1),
  ('2024-10-06', '2024-10-11', 0, 0.00, 3, 1),
  ('2024-10-01', '2024-10-05', 0, 0.00, 4, 2),
  ('2024-10-06', '2024-10-11', 0, 0.00, 5, 2),
  ('2024-10-07', '2024-10-12', 0, 0.00, 6, 2),
  ('2024-10-07', '2024-10-12', 0, 0.00, 7, 2),
  ('2024-10-07', '2024-10-12', 0, 0.00, 8, 2),
  ('2024-09-25', '2024-09-30', 0, 0.00, 9, 3),
  ('2024-10-06', '2024-10-11', 0, 0.00, 10, 3),
  ('2024-10-06', '2024-10-11', 0, 0.00, 11, 3),
  ('2024-10-01', '2024-10-05', 0, 0.00, 12, 4),
  ('2024-10-06', '2024-10-11', 0, 0.00, 13, 4),
  ('2024-10-07', '2024-10-12', 0, 0.00, 14, 4),
  ('2024-10-07', '2024-10-12', 0, 0.00, 15, 4),
  ('2024-09-25', '2024-09-30', 0, 0.00, 16, 5),
  ('2024-10-06', '2024-10-11', 0, 0.00, 17, 5),
  ('2024-10-06', '2024-10-11', 0, 0.00, 18, 5),
  ('2024-10-01', '2024-10-05', 0, 0.00, 19, 6),
  ('2024-09-25', '2024-09-30', 0, 0.00, 20, 6),
  ('2024-10-07', '2024-10-12', 0, 0.00, 2, 6),
  ('2024-09-25', '2024-09-30', 0, 0.00, 3, 3),
  ('2024-10-06', '2024-10-11', 0, 0.00, 4, 3),
  ('2024-10-06', '2024-10-11', 0, 0.00, 7, 4),
  ('2024-10-07', '2024-10-12', 0, 0.00, 8, 4),
  ('2024-10-03', '2024-10-08', 0, 0.00, 5, 3),
  ('2024-10-06', '2024-10-11', 0, 0.00, 7, 4),
  ('2024-09-25', '2024-09-30', 0, 0.00, 9, 5),
  ('2024-10-07', '2024-10-12', 0, 0.00, 11, 6),
  ('2024-09-25', '2024-09-30', 0, 0.00, 2, 7),
  ('2024-09-27', '2024-10-02', 0, 0.00, 4, 8),
  ('2024-09-25', '2024-09-30', 0, 0.00, 6, 9);
  ```
</details>

## Author

- [@victor-pimentell](https://github.com/victor-pimentell)
