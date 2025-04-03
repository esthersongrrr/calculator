#  Java Extensible Calculator

This project implements a RESTful calculator API using **Spring Boot**, following **object-oriented principles** like the **Open-Closed Principle** and the **Strategy Pattern** for clean extensibility.

---

## Features
- Basic arithmetic operations (ADD, SUBTRACT, MULTIPLY, DIVIDE)
- Allow operation chaining with initial value
- Extensible design following Open-Closed Principle
- Proper error handling
- Unit tests

## Setup & Run

```bash
mvn clean install
mvn spring-boot:run
```

## API Endpoints
### Basic Operation
- **POST** `localhost:8080/v1/calculator/calculate?operation=ADD&num1=10&num2=5`
    - Response:
      ```json
        15.0
      ```
### Chain Operations
- **POST** `localhost:8080/v1/calculator/chain?initialValue=5`
    - Request Body:
      ```json
      [
          { "operation": "ADD", "value": 3 },
          { "operation": "MULTIPLY", "value": 2 },
          { "operation": "DIVIDE", "value": 4 }
      ]
      ```
    - Response:
      ```json
        4.0
      ```
## How to Extend a New Operation
1. Create a new class that implements OperationStrategy

2. Implement supports() and apply() methods

3. Annotate with @Component

4. Done! Spring will inject it automatically at runtime

## Testing
```bash
    mvn test
```
Covers functional, boundary, and failure cases.
Includes tests for:

1. Division by zero

2. NaN, Infinity, ±0, Double max/min

3. Chains with extreme or empty values

4. Single-operation and no-op chains

5. API input validation


## Future Improvement
- Connect to local memory to store history results.
- Expand the calculator to support more operations.
