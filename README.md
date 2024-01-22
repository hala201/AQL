### Prerequisites

Before you begin, ensure you have the following installed:

- Java JDK 11 or newer
- Gradle 7.0 or newer

### Using Gradle

- Build the project using Gradle.

  ```sh
  gradle build
  ```

- To run the application, execute the following command:

  ```sh
  gradle run
  ```

- To run tests, use the following command:

  ```sh
  gradle test
  ```

## Project Structure

- `src`: Contains the source code for AQL.
  - `api`: API interaction related code.
  - `ast`: Code for abstract syntax tree structures.
  - `chain`: Handling of request chaining logic.
  - `conditional`: Conditional logic implementation.
  - `main`: Entry point of the application.
  - `parser`: Parsing logic for AQL scripts.
- `lib`: External libraries used by the project.
- `test`: Test scripts and test cases.
- `syntax.g4`: ANTLR4 grammar file for AQL.
