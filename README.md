Querity - Demo Application
==========================

Welcome to **Querity Demo** – a minimal yet complete example showcasing the power and simplicity of the
[**Querity**](https://github.com/queritylib/querity) query language for full-stack filtering, sorting, and pagination.

This demo illustrates how to integrate Querity across both backend and frontend:

- **Backend**: A Spring Boot REST API using
[`querity-spring-data-jpa`](https://github.com/queritylib/querity/tree/main/querity-spring-data-jpa) to query an in-memory **H2** database of orders.
- **Frontend**: A React-based UI built with
[`@queritylib/react`](https://github.com/queritylib/querity-react) to construct dynamic queries and display results in a grid.

Querity enables expressive, consistent, and secure querying capabilities across your stack – from API consumers to the database layer – with minimal effort.

> 💡 Whether you're building internal tools or public APIs, Querity helps you deliver powerful query functionality without reinventing the wheel.

See the live demo at ➡️ [Querity Demo](https://querity-demo.onrender.com/) ⬅️.

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

You can view the ER diagram of the database [here](/assets/er-diagram.png).

Test dataset generated with Mockaroo: https://mockaroo.com/85a9ebf0
