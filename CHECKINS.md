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
FOR EACH user IN GET https://api.com/users {
    LOG user.name
    SET GET https://api.com/users/{user.id}/tasks AS TaskList
    SET 'failed' AS failMsg

    FOR EACH task IN TaskList {
        PUT https://api.com/tasks/{task.id} WITH { status: 'completed' }

        POST https://api.com/send WITH {
            userId: user.id,
            message: 'Task {task.id} completed'
        }

        ON task.sth==false {
            DELETE https://api.com/tasks/{task.id}
        } ELSE {
            LOG failMsg
        }
    }
}
```

## Feedbacks and Follow-up:

- Concerning complexity:
  - we will assume that we will only handle JSON responses. (As suggested by TA)
  - we will allow more flexible conditions within `ON` instead of just SUCCESS or ERROR. (As suggested by Prof. Alex)
  - we will likely need one or more rich language feature. (As suggested by Prof. Alex)
- We will likely need to come up with a more convincing motivation or use-cases for the DSL.


# Check-in 2 Report
## Division of Responsibilities and Components
1. **Together**: Parser 
   - Input: AQL Script
   - Output: AST
2. **Meng**: API Request Handling and Response for `GET` and `DELETE` requests
    - Input: `GET`/`DELETE` HTTP request
    - Output: HTTP response received from backend logic
3. **Ricky**: API Request Handling and Response for `PUT` and `POST` requests
    - Input: `PUT`/`POST` HTTP request
    - Output: HTTP response retrieved from the backend logic
4. **Kai** : `SET`, Literal Strings, and `LOG` commands
    - Input: `SET`/`LOG` commands
    - Output: NA
5. **Hala**: Chaining: Looping `FOR EACH` 
    - Input: `FOR EACH` variable `IN`request/requestList
    - Output: A list of HTTP responses received from backend logic
6. **Dylan**: Chaining: Conditional `ON ERROR` and `IF/ELSE`
    - Input: `ON ERROR` or `IF/ELSE` logic
    - Output: Boolean
7. **Together**: Mock API for testing
   - Input: Mock AQL script
   - Output: Mock AST

## Data and Invariants
### Data at each interface point: 
- **Input data**: AQL script
- **Intermediate data**: parsing/ validating data, result of ANTLR, compile error checks
- **Output data**:  AST, runtime error checks
### Invariants over the data:
- **Syntax rules & Semantic rules**: check [AQLLexer.g4](https://github.students.cs.ubc.ca/CPSC410-2023W-T2/Group2Project1/blob/main/AQLLexer.g4) & [AQLParser.g4](https://github.students.cs.ubc.ca/CPSC410-2023W-T2/Group2Project1/blob/main/AQLParser.g4)
- **Type safety**: static and dynamic type checks

## Task independence 
- We will have clear and independent interfaces/classes.
- We will use mocks if an input relies on an output from another component. 
- The parser components  and evaluator components interact through AST.

## Testing Plan
- We plan on dividing test writing into the same sections that we label in the component. Each person will write the tests for their assigned task. 
- All team members work on end to end/integrated tests at the end to make sure the program functionalities are neat and coherent. 

## Miscellaneous Tasks Performed Jointly
- Team Management
- Documentation
- Syntax Design
- Brainstorming for use cases

## Timeline
1. Finalize Grammar + Syntax
2. Node (classes/interfaces design) implementation (will be completed together)
- **before check-in 3 (Jan 30) ASAP** 
3. Components implementation + module tests + integrated tests (will be completed individually)
- **before check-in 4 (Feb 6)** 
4. Debug and deliver each component. Continue with integration tests
5. Final Video discussion
6. Possible UI?
- **before check-in 5 (Feb 13)**
7. Finalize the language
8. Video filming
- **Project due (Feb 26)**

## Feedback and Follow-ups:
- Think about paginating over API queries as a feature.
- Make design choices about runtime vs compile errors.
- Plan to work on two user stories.
- Consider a plan for designing the Mock API together