# AQL Development Reports

- [Check-in 1 Report](#check-in-1-report)
- [Check-in 2 Report](#check-in-2-report)
- [Check-in 3 Report](#check-in-3-report)

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
- **Output data**: AST, runtime error checks

### Invariants over the data:

- **Syntax rules & Semantic rules**: check [AQLLexer.g4](https://github.students.cs.ubc.ca/CPSC410-2023W-T2/Group2Project1/blob/main/AQLLexer.g4) & [AQLParser.g4](https://github.students.cs.ubc.ca/CPSC410-2023W-T2/Group2Project1/blob/main/AQLParser.g4)
- **Type safety**: static and dynamic type checks

## Task independence

- We will have clear and independent interfaces/classes.
- We will use mocks if an input relies on an output from another component.
- The parser components and evaluator components interact through AST.

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

# Check-in 3 Report

## Mock up of our Language Design (as explained to participants in user studies):

1. The main 4 commands are the web API - GET PUT POST DELETE. To make a request you can simply specify what type of request you want with the URI, you can also optionally add the body to send with it.

```
eg.
GET https://api.com/
PUT https://api.com/ WITH { name: Bob }
```

2. You can set a data like string, int, or from an api response etc. to a variable and use it later. So, you can do something like print it later using LOG.

```
eg.
SET 2 AS x
LOG x
```

3. You can also assume the left-right and up-down control flow. And, you can also use a loop using the following syntax.

```
eg.
FOR EACH name IN studentList {
   LOG name
}
```

4. You can also do some if-else or conditional logic where you want to do something only in some case using the following syntax.

```
eg.
ON x == 2 {
   LOG x
} ELSE {
   LOG ‘failed’
}
```

Code snippet example (shown to participants):

```
SET GET https://api.com/ AS list
PUT https://api.com/ WITH { status: 'completed' }

SET 2 AS x
ON x == 2 {
   LOG x
} ELSE {
   LOG ‘failed’
}

FOR EACH element IN list {
    LOG element
}
```

## User Study:

### Their Assigned Tasks & Their Usage of AQL:

**Tasks:**

1. Go through a list of users and print all the name of the inactive ones. You can assume that https://api.example.com/users will return a list of users. Each user has a name, email, tasks, status.
2. If that user is active, you should go through their tasks and delete it. You can assume that https://api.example.com/{user.name} will return a list of tasks for that user and you can assume that you can delete a task with that same URI.

**User 1's** code (unfiltered):
(A student with coding experience with a little API knowledge)

```
SET GET https://api.example.com/users AS list

For all the users i
Print i


FOR EACH element IN list {
ON element.status == “active” {
	FOR EACH t IN element.tasks
		DELETE https://api.example.com/
} ELSE {
	LOG ‘failed’
}


}
```

**User 2's** code (unfiltered):
(A student with coding experience with no API knowledge)

```
SET GET https://api.example.com/users AS students
SET [] as list
FOR EACH element IN students {
    ON element.status = “Inactive”{
    list.append(elememt.name)
    }
}
LOG list



SET GET https://api.example.com/users AS students
FOR EACH element IN students{
	ON element.status = “Active” {
		SET GET https://api.example.com/{element.name} AS tasks
			FOR EACH element IN tasks {
				DELETE https://api.example.com/{element.name}
            }
    }
}
```

### Results & Feedbacks:

- Ease/Difficulty & Learnability:
  - Both users found individual commands intuitive as both learned them in about 2-3 minutes.
  - Combining commands wasn't as intuitive (eg. setting an API response to a variable).
  - User 2 found it intuitive to assign the result of a GET request to a list, but they found it a bit unfamiliar not to declare the list before assigning it a value.
  - It wasn’t clear if it was needed to specify ON/ELSE, rather than just ON.
- We learned that we haven't thought about how or if we will deal with lists or similar data structures. (eg. a user tried to set a variable as a list and appends to it)
- We learned that we haven’t thought about whether we want to allow in-line comments.
- We should make it clear that whether the functions/commands are void (return nothing) like LOG, SET or return something like GET, DELETE.

## Future User study:

- We weren’t sure of the extent of the language, so we would like to assign a more interesting task for users. This can be done in the final user study after more tests and a clearer understanding of AQL.
- We would like to refine the task instructions to be structured more clearly, as there were minor issues/misunderstandings with this user study (eg. User was not sure if the task was to delete users or tasks and was not sure if they should return anything).

## Language Design Considerations

- Since checkin 2, we have only made a minor change to onElse rule, to allow for 'SUCCESS' and 'ERROR' for API responses, and we will only allow one onElse to chain with each API requests ((onElse)\* -> (onElse)?).
  - These minor changes do not affect the example snippets we included here.
- We will likely do as much static check as possible (eg. whether a variable exists or not), but most of the checkings will be done during the evaluation stage.
  - URI can be dynamic (we can’t know in advance whether the user is valid). It is also not guaranteed whether the request will succeed.
  - We will do dynamic checks for loops.
- We still need to decide how an incorrect program will be reported to the user.
- We will likely allow assigning list (of JSON) from API requests to variables, but there will not be support to manipulate those variables.
  - Users may only SET and not make any changes unless SET is used to assign a new value.
- We will likely allow for in-line comments for convenience, if time permits.

## Other

- The timeline will stay the same, with more detailed tasks assigned (for clarity), however the deadline stays the same.
- We have only written regression tests for what we expect from antlr, as well as 2 small functionality unit tests for PUT and POST.
- Future test plans:
  - After finalizing the AST and visitor patterns, we will be able to write more unit and integration tests.
  - We will add tests for incorrect programs that are not supposed to work.
  - We will add end-to-end tests and start creating the mock api.
