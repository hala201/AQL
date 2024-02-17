# AQL Development Reports

- [Check-in 1 Report](#check-in-1-report)
- [Check-in 2 Report](#check-in-2-report)
- [Check-in 3 Report](#check-in-3-report)
- [Check-in 4 Report](#check-in-4-report)
- [Check-in 5 Report](#check-in-5-report)

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
  - URI can be dynamic. It’s not possible to determine in advance the validity of a user or the success of a request. It is also not guaranteed whether the request will succeed.
  - We will do dynamic checks for loops.
- We still need to decide how an incorrect program will be reported to the user.
- We will likely allow assigning list of JSON objects got from API responses to variables, but there will not be support to manipulate those variables.
  - Users may only use SET to initialize variables and may not make any changes unless SET is used to assign a new value.
- We will likely allow for in-line comments for convenience, if time permits.

## Other

- The timeline will stay the same, with more detailed tasks assigned (for clarity), however the deadline stays the same.
- We have only written regression tests for what we expect from antlr, as well as 2 small functionality unit tests for PUT and POST.
- Future test plans:
  - After finalizing the AST and visitor patterns, we will be able to write more unit and integration tests.
  - We will add tests for incorrect programs that are not supposed to work.
  - We will add end-to-end tests and start creating the mock api to simulate integration test points.

# Check-in 4 Report

We will use antlr for lexing and parsing. Then, we will design our own custom nodes, visitor, and evaluator. Some factories and interfaces are also used to refactor codes.

## New/Changes to designs:

- Allowing users to set a JSON Object to a variable to imitate a List data structure as inspired by the user study. eg. `SET {‘1’: “ben”, ‘2’: 10} AS List`

## Status of implementation.

### Component-wise progress

- GET/DEL/WITH: Finished the component and is working with `WITH`. `WITH` is more thoroughly handled in terms of error/bad cases. However, I still need to handle the error/bad cases such as below:
  - wrong format (non JSON)
  - trying to access non existent variable
  - static checks for syntax of different requests
  - API doesn't exist (404) Response errors
  - 500 Response errors
  - 400 Response errors
- PUT/POST: Finished API functionality and integrated with the design structure of GET/DELETE. Created the corresponding testsets including functionality test and integration test.
- Loop: Modified the grammar and finished implementation, currently debugging the following:
  - Local scope
  - failing get request handling
  - parsing a non iterable error
  - using an undeclared iterable
- ON/ELSE: Added ON/ELSE & Condition functionality, more refinement needed. Created visitor tests (currently not passing need changes to statement). Slight change in Parser rules.
- SET/LOG: Modified the grammar to prevent the potential infinite recursion for LOG keyword, finished log's implementation, currently working on implementing the logic of chained set with request. I still need to work on the following:
  - debugging the logic of chained set with request
  - variable assignment for json object or json string
  - response error handling for set

### Tests

- GET/DEL/WITH: Functionality unit tests are passing. No failing testcases so far. More tests for error cases will be added.
- PUT/POST: Functionality unit and simple integration (end-to-end) tests are passing. No failing testcases so far
- Loop: Some unit tests are passing, parsing tests are failing.
- ON/ELSE: Created visitor tests (currently not passing need changes to statement).
- SET/LOG: Created unit tests, unit tests for SET are not passing.

We still need to write/add more:

- Integration test
- Complex Mock API test

## Final user study plan

We will give a more comprehensive instructions and a bit more complicated task that would involve using various commands together, similar to a coding interview question. In other words, there will be step-by-step instructions, what to expect of input/output at each stage, and mock web APIs that would actually return the things the users will need. We are also thinking of having the user interact with syntax tests and modify their code as per the tests passing. We will continue to find out target users (backend developers).

## Timeline

The general plan is to have implementation ready by Friday, Feb 9th. We also plan to have debugging/refining phase from Feb 9th - 16th. Ideally, we want to have something that fully works by Feb 16th. We plan to have the second User Study + More testing Feb 16th - 19th.

Plans for integration/end-to-end testing (Feb 9th-16th)
Integration tests will be gradually added from this week (Feb 9th-16th)

# Check-in 5 Report

## Final User Study:

### Their Introduction to the DSL:

AQL ((web) API Query Language) aims to help with common frustrations encountered when interacting with web APIs. AQL streamlines the process of making API requests, handling responses, and implementing logic based on those responses. We aim to make this process better for our target users, backend developers (of all levels of experience).
Before AQL, interacting with web APIs involved several steps that could be tedious and error-prone, especially for those not deeply familiar with programming:

1.  **Complexity in Making Requests**: Each programming language has its own way of making HTTP requests, requiring developers to learn and manage various libraries or modules.
2.  **Handling Responses**: Parsing and extracting useful information from API responses often involves dealing with different formats, which can get complicated quickly.
3.  **Implementing Logic**: Writing logic based on API responses (e.g., conditional statements, loops) necessitates a good grasp of the programming language used, adding another layer of complexity.
4.  **State Management**: Keeping track of variables and states across different API calls can be challenging without a clear and organized approach.

AQL addresses these challenges by providing a straightforward, readable syntax for making API requests, handling data, and implementing control flow logic. This is done through the features below:

1.  **Simplified Requests**: AQL abstracts the complexity of making HTTP requests. Whether it’s a GET, PUT, POST, or DELETE request, you simply specify the command and the URL. Optionally, you can include a body, headers, authorizations, etc. with the request. By default, the { key, value } will be counted as body unless the key is specified with a special keyword “AQL@”.

```
GET https://api.com
PUT https://api.com WITH { “name”: "Bob", “AQL@Authorization”: “...”}
```

2. **Variable Management**: AQL allows you to set data (like strings, integers, or API responses) to variables for later use, simplifying state management and data manipulation. You can only use SET statically as it cannot be modified afterward, only re-SET. We also allow users to set JSON to a variable to mimic a list.

```
SET 2 AS x
SET { “name1”: "Bob", “name2” : “Ben” } AS y
LOG y.name1
```

3. **Control Flow Logic**: Implementing loops and conditional logic is straightforward with AQL, making it easier to handle complex API interaction patterns.

```
FOR EACH name IN studentList {
  LOG name
}
ON x == 2 {
  LOG x
} ELSE {
  LOG “failed”
}
```

There are other features for more convenience:
You can access the first key and value from a JSON variable like below

```
SET {"name" : "ted", “name2” : “name” } AS var
LOG var.AQLKEY // prints “name”
LOG var.AQLVALUE //prints “ted”
```

**Example Scenario**

Imagine you’re managing a list of tasks via an API. Without AQL, you’d need to write boilerplate code for each request, handle the parsing of JSON responses manually, and then write additional code for any logic based on the responses. With AQL, you can:

```
SET GET https://api.com/tasks AS list
FOR EACH task IN list {
  PUT https://api.com/tasks/{task.id} WITH { “status”: “completed” }
  LOG task.id // will error if task has no .id
}
```

OR

```
FOR EACH task IN GET https://api.com/tasks {
  PUT https://api.com/tasks/{task.id} WITH { “status”: “completed” }
  LOG task.id // will error if task has no .id
}
```

### Their Assigned Tasks & Their Usage of AQL:

#### **Tasks 1:**

Imagine that you have developed various secured endpoints for a trip generation application. You have users and trips. You are testing out the response of those endpoints.

The endpoint to login is POST to: https://travelerstea-906d.onrender.com/api/users/login This endpoint will return a JSON that contains an accessToken. A valid login info is the following:
{ "email": "testnlm@nlm", "password": "123" }

The endpoint to create a trip is POST to: https://travelerstea-906d.onrender.com/api/trips This endpoint is protected, you need to send Authorization with <accessToken> from logging in. You will also need to create and send a body to create a trip in the following format:{
"tripLocation": "NAME", "stagesPerDay": NUMBER < 2, "budget": NUMBER, "numberOfDays": NUMBER < 2, "AQL@Authorization": accessToken
}

Your job is to print the response you get from creating the trip.

**User 1's** code:
(A developer with coding experience with prior API knowledge)

```
SET POST https://travelertea-906d.onrender.com/api/users/login WITH { "email": "testnlm@nlm", "password": "123" } AS list
SET POST https://travelerstea-906d.onrender.com/api/trips WITH ｛
"tripLocation": "London", "stagesPerDay": 1, "budget": 100, "numberODays": 1, "AQL@Authorization": list.accesToken
} AS RESULT
LOG RESULT
```

**User 2's** code:
(A student with coding experience with little prior API knowledge)

```
SET POST https://travelertea-906d.onrender.com/api/users/login WITH { "email": "testnlm@nlm", "password": "123" } AS authToken
SET POST https://travelerstea-906d.onrender.com/api/trips WITH｛
   "tripLocation": "Cambodia",
   "stagesPerDay": 1,
   "budget": 100,
   "numberODays": 1,
   "AQL@Authorization": authToken.accessToken
} AS response
LOG response
```

#### **Tasks 2:**

Imagine you are trying to find all the users of your application.

The backend of your application is working with the endpoint: https://aqlserver.onrender.com/users

Each user has an associated property called \_id that is unique for each of them (user.\_id). Using this property’s value, you can get even more information about them. You may assume you can use https://aqlserver.onrender.com/users/{users._id}/tasks to get a list of all of THAT ONE USER’s tasks.

As you will notice, the response is not too nice and each user has several other key, value pairs that you need to skip over because you only care about the user.\_id

Your job is to print out all information tasks’ information of all users.

**User 1's** code:
(A developer with coding experience with prior API knowledge)

```
SET GET https://aqlserver.onrender.com/users AS studentlist
FOR EACH info IN studentlist {
   ON info.AQLKEY = "_id" {
      SET GET https://aqlserver.onrender.com/users/{info._id}/tasks AS tasks
      FOR EACH task IN tasks {
         LOG task
      ｝
   } ELSE {
      LOG ""
   }
}
```

**User 2's** code:
(A student with coding experience with little prior API knowledge)

```
SET GET https://aqlserver.onrender.com/users AS userlist
FOR EACH key IN userlist {
   ON key.AQLKEY = "_id" {
      SET GET https://aqlserver.onrender.com/users/{key._id}/tasks AS userTasks
      LOG userTasks
   } ELSE {
      LOG ""
   }
}
```

### Results & Feedbacks:

- Users were able to write working codes for task 1 perfectly and without hints, after about 5 minutes verbal introduction to the language.

- User 1 was able to write task 2 perfectly with 1 hint regarding ON/ELSE. User 2 was able to write task 2 perfectly with 2 hints regarding ON/ELSE and FOR EACH. Mainly, they were both not sure about the response of the API (what the key-value pair is), user 2 was able to figure it out themselves by debugging and logging.

- Both users were able to interact with and use the interface easily.

- Both users found it confusing how "FOR EACH" loops over the key-value pair instead of individual JSONObjects. Mainly, they weren't sure what the object was in the list at each iteration.

- User 2 suggested we allow not having ELSE (using ON without ELSE).

- User 1 suggested we support quote mark inside the string (""quote"" or "\"quote\"").

- User 2 was able to debug from the error logs by themselves.

## Changes

- Inspired by the first user study, we allow users to set a JSON Object to a variable to imitate a List data structure as .
  eg.
```
SET {‘1’: “ben”, ‘2’: 10} AS List
```  

- Realizing the importance of headers, authorizations, etc., we allow users to send not only body, but headers and more with the requests as well.
  eg.
```
PUT https://api.com WITH { "AQL@Authorization": authToken.accessToken }
```

- Realizing that it might be hard for users to know exactly what the key/value is from the response, we allow users to access the first key and value from a JSON variable.
  eg.
```
SET {"name" : "ted", “name2” : “name” } AS var
LOG var.AQLKEY // prints “name”
LOG var.AQLVALUE //prints “ted”
```

- Removed (ERROR | SUCCESS) from conditional grammar, since it require too much changes to how we deal with responses.

### BUG FIXES

- ON/ELSE: we now allow any type of Number (beyond just Integer).
- ON/ELSE: we now allow using ON without ELSE, as inspired by the final user study.

## Final Video Plan:

- Zoom meeting where we present and share screen. Ideally, each one of us write a paragraph of 150 words to explain what our features did.
- We will try to create a story/presentation.
  - Show what the DSL is trying to solve (show how something can be frustrating and how our DSL helps)
  - Target users
  - The features (mention and very briefly explain, not too detailed eg. don't go through grammar)

## Timeline

- Friday Feb 16th - Monday Feb 19th to polish everything else and add E2E & Integration tests.
- Monday Feb 19th film the video
- Hopefully we will be all done by Feb 20th.
