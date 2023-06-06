INSERT INTO "Authors" (name, surname)
VALUES ('J.R.R.', 'Tolkien');

INSERT INTO "Books" (title, png_path, age_group, rating, isbn, author_id, amount)
VALUES ('Władca Pierścieni: Bractwo Pierścienia','db_init\img\wladca-pierscieni-bractwo-pierscienia-tom-1-wersja-ilustrowana.jpg','12','8.7','9788328724624','1','255');

INSERT INTO "BookCategories" (book_id, category)
VALUES ('1','Fantasy');

INSERT INTO "Books" (title, png_path, age_group, rating, isbn, author_id, amount)
VALUES ('Władca pierścieni. Dwie wieże. Tom 2','db_init\img\wladca-pierscieni-dwie-wieze-tom-2-wersja-ilustrowana.jpg','12','9.0','	22655660','1','0');

INSERT INTO "BookCategories" (book_id, category)
VALUES ('2','Fantasy');

INSERT INTO "Authors" (name, surname)
VALUES ('James', 'Clear');

INSERT INTO "Books" (title, png_path, age_group, rating, isbn, author_id, amount)
VALUES ('Atomic Habits','db_init\img\AtomicHabits.jpg','3','9.6','30639720','2','12');

INSERT INTO "BookCategories" (book_id, category)
VALUES ('3','Personal development');

INSERT INTO "BookCategories" (book_id, category)
VALUES ('3','Psychology');

INSERT INTO "Authors" (name, surname)
VALUES ('Andrzej', 'Sapkowski');

INSERT INTO "Books" (title, png_path, age_group, rating, isbn, author_id, amount)
VALUES ('Miecz przeznaczenia. Wiedźmin. Tom 2','db_init\img\miecz-przeznaczenia-wiedzmin-tom-2.jpg','12','7.0','20646967','3','2');

INSERT INTO "BookCategories" (book_id, category)
VALUES ('4','Fantasy');

INSERT INTO "BookCategories" (book_id, category)
VALUES ('4','Action');

INSERT INTO "Authors" (name, surname)
VALUES ('Malcolm', 'Folley');

INSERT INTO "Books" (title, png_path, age_group, rating, isbn, author_id, amount)
VALUES ('Grand Prix Monaco. Kulisy najwspanialszego wyścigu F1 na świecie','db_init\img\grand-prix-monaco-kulisy-najwspanialszego-wyscigu-f1-na-swiecie.jpg','3','4.6','56210651','4','50');

INSERT INTO "BookCategories" (book_id, category)
VALUES ('5','History');


INSERT INTO "Authors" (name, surname)
VALUES ('Marc', 'Webber');

INSERT INTO "Books" (title, png_path, age_group, rating, isbn, author_id, amount)
VALUES ('Mark Webber. Moja Formuła 1','db_init\img\MarkWebberMojaFormuła1.jpg','3','9.0','20245382','5','500');

INSERT INTO "BookCategories" (book_id, category)
VALUES ('6','Biography');
