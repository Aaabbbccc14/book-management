                                BOOK MANAGEMENT
1) Intellij Idea
2) Postgresql
3) application.properties in -> src.main.resources: change database credentials
4) Ensure that the port 8099 is available, then run ->  src.main.java.com.example.book.management.BookManagementApplication

                               Endpoints:
1) user/add - is for adding user with data:
    {
    "email": "string",
    "firstname": "string",
    "lastname": "string"
    }
2) user/byEmail - alse we can find user by email, the output data:
   {
  "id": 0,
  "email": "string",
  "firstname": "string",
  "lastname": "string",
  "userBooks": [
        {
          "id": 0,
          "name": "string",
          "authorFullName": "string",
          "publisherEmail": "string",
          "addedTime": "2024-03-24T13:53:32.024Z",
          "isbn": "string"
        }
      ]
}
3) user/all - shows all users
4) /book/user/add/{bookId} - the user is "buying" the book
5) /book/add - the admin adding new book
6) /book/{bookId} - showing book by id
7) /book/user/all - all books endpoint
8) /publisher/books - taking all books by publisher (full name)

<img width="1457" alt="Screen Shot 2024-03-24 at 19 51 25" src="https://github.com/Aaabbbccc14/book-management/assets/164651629/19b5e46c-e577-4540-a6df-4a512a98027d">
