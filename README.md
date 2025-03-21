Querity - DEMO
==============

This application is the simplest demonstration of the [Querity](https://github.com/queritylib/querity) library.

It is a Spring Boot application that exposes a REST API to query a list of orders.

The application uses an in-memory H2 database to store the orders and the `querity-spring-data-jpa` library to query the database. 

You can view the ER diagram of the database [here](/assets/er-diagram.png).

The in-memory database is pre-populated with a test dataset of 1000 orders. The source data can be found in the [orders.json](/src/main/resources/data/orders.json) file.

The parsing of the query language is enabled by the `querity-parser` library.

## Running

Run the application with the following command:

```bash
./mvnw -Pwith-frontend spring-boot:run
```

Then open your browser to `http://localhost:8080`, you should see a simple web page with a form to query the orders.

There's also a Swagger-UI interface available at `http://localhost:8080/swagger-ui/index.html` to test the API.

Now try out the query language by replacing the query string with any valid query (see [Documentation](https://queritylib.github.io/querity/#query-language-syntax)).

Some examples:

`shippingCustomer.address.country="France" sort by placementDate desc page 1,20`

> This query filters by shipping address country, sorts the results by placement date in descending order and paginates
> the results, returning the first page with 20 elements.

`distinct and(rows.totalPrice>1000, currency="EUR") sort by totalPrice desc page 1,10`

> Notice how the above query string filters by a field nested in a collection (`rows.totalPrice`).
>
> In this case the `distinct` keyword is used to avoid duplicate rows and pagination issues.

## Other info

Test dataset generated with Mockaroo: https://mockaroo.com/85a9ebf0
