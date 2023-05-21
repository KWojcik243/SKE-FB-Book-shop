INSERT INTO "Authors" (name, surname)
VALUES ('J.R.R.', 'Tolkien');

INSERT INTO "Books" (title, png_path, age_group, rating, isbn, author_id, amount)
VALUES ('Władca Pierścieni: Bractwo Pierścienia','./book_cover/Wladca_Pierscieni_Bractwo Pierscienia','12','8.7','9788328724624','1','255');

INSERT INTO "BookCategories" (book_id, category)
VALUES ('1','Fantasy');
