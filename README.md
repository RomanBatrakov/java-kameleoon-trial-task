# Kameleoon trial task project
Backend (rest api) of the application, in which users can:
- create account;
- addition, view, modification, and deletion of quotes;
- voting on quotes (either upvote or downvote);
- view of the top 10 quotes, the details of each quote.
## Tech stack:
- Java 17;
- Spring Boot 3.0.2;
- Spring Web, Spring Data;
- H2, SQL;
- Junit
- Maven 4.0.0;
- Lombok
- Docker.
## Quick start:
The application uses ports: 8080.
While in the directory on the command line, type:

`mvn package`  
`docker-compose up`  
## Rest service:
<details>
    <summary><h3>Examples of methods and endpoints available for the API:</h3></summary>

- [(POST) create quote](http://localhost:8080/quotes/{userId})
- [(GET) get quote](http://localhost:8080/quotes/{quoteId})
- [(GET) get all quotes](http://localhost:8080/quotes)
- [(GET) get top quotes](http://localhost:8080/quotes/top)
- [(PATCH) update quote](http://localhost:8080/quotes/{itemId}/{quoteId})
- [(DELETE) delete quote](http://localhost:8080/quotes/{itemId}/{quoteId})
- [(POST) create vote](http://localhost:8080/quotes/{itemId}/{quoteId}/{reaction})
- [(GET) get quote votes](http://localhost:8080/quotes/{quoteId}/votes)
- [(POST) create user](http://localhost:8080/users)

</details>
