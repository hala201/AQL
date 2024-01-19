# Check-in 1 Report

API Query Language (AQL)

### Purpose

AQL is a domain-specific language (DSL) intended to simplify, standardize, and streamline web API interactions for developers. It is specifically designed for backend developers and similar roles, aiming to abstract the complexities and annoyance involved in making API calls, handling responses, and chaining requests.

### Target User

The primary users of AQL are backend developers who frequently interact with various web APIs. It is particularly beneficial for teams from different backgrounds, who write APIs in different styles. AQL provides a standardized method for coding these requests. It also simplifies their API interactions and reduces the boilerplate code typically associated with such tasks.

## Key Features

### 1. API Request and Response Handling

- **Description**: AQL allows users to define and execute standard API requests (such as `GET`, `POST`, `PUT`, `DELETE`) and handle responses in a structured way. This feature includes comprehensive error handling capabilities by combining with `ON`.
- **Customization**: Users can tailor request parameters, headers, and define specific steps for processing responses to a certain, limited extent using `WITH`.

### 2. Chaining: Looping & Conditional Logic

- **Looping**: `FOR EACH ... IN` feature enables iterating over a collection of data retrieved from an API (or object within scope using `SET ... AS`), which is particularly useful for batch processing or executing sequential API interactions based on a dataset.
- **Conditional Logic**: AQL supports conditional logic (using statements like `ON <CON>`, `ELSE` where `<CON>` is a certain condition) and allows chaining multiple API requests. The output of one request can be seamlessly fed into subsequent requests.

### 3. SET and literal strings

- **SET**: is used to assign a response from an API call and other values (int, string ...) to a variable for easy access and manipulation. This allows users to store data retrieved from an API and use it in subsequent operations.
- **Literal Strings**: AQL supports the use of literal strings within API calls and operations. These strings can be used to specify fixed values in API requests and conditional logic, enhancing the readability and flexibility of the code.

## Example Snippets

#### This short code example ideally will retrieves user data, fetches their tasks, marks as completed, and sends notifications about task completion.

```aql
SET [GET https://api.com/users/{userId} WITH { userId: 123 }] AS data ON ERROR LOG 'failed' ELSE POST 'https://api.com/send' WITH {
    email: data.email,
    subject: '...',
    message: '...
}

FOR EACH user IN GET https://api.com/users {
    LOG user.name
    SET [GET 'https://api.com/users/{user.id}/tasks'] AS TaskList

    FOR EACH task IN TaskList {
        PUT 'https://api.com/tasks/{task.id}' WITH { status: 'completed' }

        POST 'https://api.com/send' WITH {
            userId: user.id,
            message: 'Task {task.id} completed'
        } ON data.sth==false DELETE 'https://api.com/tasks/{task.id}' ELSE LOG ‘failed
    }

}

LOG 'end'
```

## Feedbacks and Follow-up:

- Concerning complexity:
  - we will assume that we will only handle JSON responses. (As suggested by TA)
  - we will allow more flexible conditions within `ON` instead of just SUCCESS or ERROR. (As suggested by Prof. Alex)
  - we will likely need one or more rich language feature. (As suggested by Prof. Alex)
- We will likely need to come up with a more convincing motivation or use-cases for the DSL.
