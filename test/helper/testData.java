package helper;
public class testData {
    public static String simpleSuccessNoLog = "\n\n---------\nDone!\n";
    public static String simpleLog_input = "LOG \"Hello World\"";
    public static String simpleLog_parse_output = "(program (statement (log LOG (value (string \"Hello World\")))))";
    public static String simpleLog_e2e_output = "Hello World\n";

    public static String LogWithVariable_input = "LOG variableName";
    public static String LogWithVariable_parse_output = "(program (statement (log LOG (dynamicVar variableName))))";
    public static String numberLog_input = "LOG 123";
    public static String numberLog_parse_output = "(program (statement (log LOG (value 123))))";
    public static String LogWithDynamicVar_inputString = "LOG SimpleClass.a";
    public static String LogWithDynamicVar_parse_outputString = "(program (statement (log LOG (dynamicVar SimpleClass . a))))";
    public static String LogWithDynamicVar_inputNumber = "LOG SimpleClass.b";
    public static String LogWithDynamicVar_parse_outputNumber = "(program (statement (log LOG (dynamicVar SimpleClass . b))))";
    public static String SetWithString_input = "SET \"Hello World\" AS myVar";
    public static String SetWithString_parse_output = "(program (statement (set SET (value (string \"Hello World\")) AS myVar)))";
    public static String SetWithNumber_input = "SET 123 AS myVar";
    public static String SetWithNumber_parse_output = "(program (statement (set SET (value 123) AS myVar)))";
    public static String SetWithGetReq_input = "SET GET https://api.example.com/users AS students";
    public static String SetWithGetReq_parse_output = "(program (statement (set SET (request (getReq GET (dynamicURI https://api.example.com/users))) AS students)))";
    public static class SimpleClass {

        private static String a = "hello";

        private static int b = 1;

        public static String getA() {
            return a;
        }

        public static int getB() {
            return b;
        }
    }
    public static String simpleGet_input = "GET https://8k06e.wiremockapi.cloud/json/1";
    public static String simpleGet_parse_output = "(program (statement (request (getReq GET (dynamicURI https://8k06e.wiremockapi.cloud/json/1)))))";

    public static String simplePut_input = "PUT https://8k06e.wiremockapi.cloud/json/2 WITH { \"id\": 12345, \"value\": \"abc-def-ghi\" }";
    public static String simplePut_parse_output = """
    (program (statement (request (putReq PUT (dynamicURI https://8k06e.wiremockapi.cloud/json/2) (withBlock WITH (params { (param (string "id") : (value 12345)) , (param (string "value") : (value (string "abc-def-ghi"))) }))))))
            """;

    public static String simplePost_input = "POST https://8k06e.wiremockapi.cloud/json WITH { \"id\": 12345, \"value\": \"abc-def-ghi\" }";
    public static String simplePost_parse_output = """
    (program (statement (request (postReq POST (dynamicURI https://8k06e.wiremockapi.cloud/json) (withBlock WITH (params { (param (string "id") : (value 12345)) , (param (string "value") : (value (string "abc-def-ghi"))) }))))))
            """;

    public static String simpleDelete_input = "DELETE https://api.example.com/data";
    public static String simpleDelete_parse_output = "(program (statement (request (delReq DELETE (dynamicURI https://api.example.com/data)))))";

    public static String withBlock_input = """
            GET https://api.example.com/data WITH { "param1": value1, "param2": \"value2\" }
                    """;
    public static String withBlock_parse_output = """
    (program (statement (request (getReq GET (dynamicURI https://api.example.com/data) (withBlock WITH (params { (param (string "param1") : (dynamicVar value1)) , (param (string "param2") : (value (string "value2"))) }))))))
            """;

    public static String onElseCondition_input = "GET https://api.example.com/data ON variable1 == variable2 { LOG variable1 } ELSE { LOG \"Error\" }";
    public static String onElseCondition_parse_output = "(program (statement (request (getReq GET (dynamicURI https://api.example.com/data)))) (statement (onElse ON (condition (dynamicVar variable1) == (dynamicVar variable2)) { (program (statement (log LOG (dynamicVar variable1)))) } ELSE { (program (statement (log LOG (value (string \"Error\"))))) })))";

    public static String setStatement_input = "SET GET https://api.example.com/data AS myData";
    public static String setStatement_parse_output = "(program (statement (set SET (request (getReq GET (dynamicURI https://api.example.com/data))) AS myData)))";

    public static String loopStatement_Valid_input = "FOR EACH user IN GET https://api.example.com/users { GET https://api.example.com/data }";
    public static String loopStatement_parse_output = "(program (statement (loop FOR EACH user IN (getReq GET (dynamicURI https://api.example.com/users)) { (statement (request (getReq GET (dynamicURI https://api.example.com/data)))) })))";

    public static String loopStatement_Valid_DeclaredIterable_input = "FOR EACH user IN users { GET https://api.example.com/data }";
    public static String loopStatement_Valid_LongerBody_input = """
            FOR EACH user IN users { 
            GET https://api.example.com/data 
            POST https://api.example.com/data 
            }
            """;

    public static String loopStatement_Invalid_EmptyBody_input = "FOR EACH user IN GET https://api.example.com/users {  }";
    public static String loopStatement_Invalid_NoControlVariable_input = "FOR EACH IN GET https://api.example.com/users { LOG user.name }";
    public static String loopStatement_Invalid_NoIN_input = "FOR EACH user GET https://api.example.com/users { LOG user.name }";
    public static String loopStatement_Invalid_NoEACH_input = "FOR user IN GET https://api.example.com/users { LOG user.name }";
    public static String loopStatement_Invalid_NoIterable_input = "FOR EACH user IN { LOG user.name }";
    public static String loopStatement_Invalid_NoOpeningBracket_input = "FOR EACH user IN GET https://api.example.com/users LOG user.name }";
    public static String loopStatement_Invalid_NoClosingBracket_input = "FOR EACH user IN GET https://api.example.com/users { LOG user.name";
    public static String loopStatement_Invalid_NonIterable_input = "FOR EACH user IN POST https://api.example.com/users { LOG user.name}";

    public static String complex1_input = """
            FOR EACH user IN GET https://api.com/users {
                LOG user.name
                SET GET https://api.com/users/{user.id}/tasks AS TaskList
                SET \"failed\" AS failMsg

                FOR EACH task IN TaskList {
                    PUT https://api.com/tasks/{task.id} WITH { "status": \"completed\" }

                    POST https://api.com/send WITH {
                        "userId": user.id,
                        "message": \"Task {task.id} completed\"
                    }

                    ON task.sth==false {
                        DELETE https://api.com/tasks/{task.id}
                    } ELSE {
                        LOG failMsg
                    }
                }
            }
                    """;
    public static String complex1_parse_output = """
    (program (statement (loop FOR EACH user IN (getReq GET (dynamicURI https://api.com/users)) { (statement (log LOG (dynamicVar user . name))) (statement (set SET (request (getReq GET (dynamicURI https://api.com/users/ (dynamicVar { user . id }) /tasks))) AS TaskList)) (statement (set SET (value (string "failed")) AS failMsg)) (statement (loop FOR EACH task IN (dynamicVar TaskList) { (statement (request (putReq PUT (dynamicURI https://api.com/tasks/ (dynamicVar { task . id })) (withBlock WITH (params { (param (string "status") : (value (string "completed"))) }))))) (statement (request (postReq POST (dynamicURI https://api.com/send) (withBlock WITH (params { (param (string "userId") : (dynamicVar user . id)) , (param (string "message") : (value (string "Task {task.id} completed"))) }))))) (statement (onElse ON (condition (dynamicVar task . sth) == (dynamicVar false)) { (program (statement (request (delReq DELETE (dynamicURI https://api.com/tasks/ (dynamicVar { task . id })))))) } ELSE { (program (statement (log LOG (dynamicVar failMsg)))) })) })) })))
            """;

    public static String getOnElseCondition_valid_input1 = "ON 2 == 2 { LOG \"SUCCESS\" } ELSE { LOG \"FAILED\" }";
    public static String getOnElseCondition_valid_input2 = "ON 2 == 2 { LOG \"SUCCESS\" }";
    
    public static String getOnElseCondition_Empty_On_number_input = "ON 2 == 2 { } ELSE { LOG \"FAILED\" }";

    public static String getOnElseCondition_Empty_Else_number_input = "ON 2 == 2 { LOG \"SUCCESS\" } ELSE {  }";
    public static String getOnElseCondition_Invalid_rule_number_input = "ON 2 * 2 { LOG \"SUCCESS\" } ELSE { LOG \"FAILED\" }";

    public static String getOnElseCondition_No_condition_number_input = "ON 2 2 { LOG \"SUCCESS\" } ELSE { LOG \"FAILED\" }";

    public static String getCondition_valid_string_input = "\"string\" == \"string\"";
    public static String getCondition_valid_number_input = "2 == 2";
    public static String getCondition_valid1_parse_output = "(program 2 == 2)";
    public static String getCondition_valid_input3_2VAR = "A == B";
    public static String getCondition_valid_input4_2DVAR = "dA.id == dB.name";
    public static String getCondition_valid_input5_VARandDVAR = "A == dB.name";
    public static String getCondition_valid_input6_VARandVAL = "A == \"bob\"";

    public static String e2e_trip_success_1_input = "SET POST https://travelerstea-906d.onrender.com/api/users/login WITH { \"email\": \"testnlm@nlm\", \"password\": \"123\" } AS userInfo\n" +
            "SET GET https://travelerstea-906d.onrender.com/api/trips WITH {\n" +
            "\t\"AQL@Authorization\": {userInfo.accessToken}\n" +
            "} AS RESULT\n" +
            "LOG RESULT";
    public static String e2e_trip_success_1_output = """
    [{
        "_id": "65d12871f270343a277d3a9b",
        "tripLocation": "paris",
        "stagesPerDay": 1,
        "budget": 100,
        "numberOfDays": 1,
        "isPublic": false,
        "__v": 0,
        "tripLatitude": 48.858370099999995,
        "tripLongitude": 2.2944812999999997,
        "tripName": "DONT DELETE THIS PARIS"
    }]
                    """;
                    
    public static String e2e_aqlserver_success_1_input = """
    SET GET https://aqlserver.onrender.com/users AS stuList
    LOG stuList
    FOR EACH stu IN stuList {
        ON stu.AQLKEY == "_id" {
            SET GET https://aqlserver.onrender.com/users/{stu._id}/tasks AS tasks
            FOR EACH task IN tasks {
                LOG task
            }
        }
    }    
                    """;
    public static String e2e_aqlserver_success_1_output = """
    [{"__v":0,"name":"John Doe","_id":"65cd34f301b10b181cec335b","email":"john.doe@example.com"}]
    {"__v":0}
    {"description":"this is task 1"}
    {"_id":"65cd35ee01b10b181cec3360"}
    {"completed":false}
    {"title":"Task 1"}
    {"user":"65cd34f301b10b181cec335b"}
    {"__v":0}
    {"description":"this is task 2"}
    {"_id":"65cd382b8e32036d2c4a3c39"}
    {"completed":true}
    {"title":"Task 2"}
    {"user":"65cd34f301b10b181cec335b"}
                    """;


    public static String e2e_cat_1_success_input = "SET GET https://cat-fact.herokuapp.com/facts/ AS cats\n" +
            "FOR EACH cat IN cats {\n" +
            "\tON cat.AQLKEY == \"text\" {\n" +
            "\t\tLOG cat\n" +
            "\t}\n" +
            "}";
    public static String e2e_cat_1_success_output = "{\"text\":\"Owning a cat can reduce the risk of stroke and heart attack by a third.\"}\n" +
            "{\"text\":\"Most cats are lactose intolerant, and milk can cause painful stomach cramps and diarrhea. It's best to forego the milk and just give your cat the standard: clean, cool drinking water.\"}\n" +
            "{\"text\":\"Domestic cats spend about 70 percent of the day sleeping and 15 percent of the day grooming.\"}\n" +
            "{\"text\":\"The frequency of a domestic cat's purr is the same at which muscles and bones repair themselves.\"}\n" +
            "{\"text\":\"Cats are the most popular pet in the United States: There are 88 million pet cats and 74 million dogs.\"}\n";

    public static String e2e_cat_2_success_input = "SET GET https://cat-fact.herokuapp.com/facts/ AS cats1\n" +
            "SET GET https://cat-fact.herokuapp.com/facts/ AS cats2\n" +
            "\tON cats1 == cats2 {\n" +
            "\t\tLOG \"YES\"\n" +
            "}";

    public static String e2e_nestedOnElse_input = "SET \"{1, 2, 3}\" AS nums1\n" +
            "SET \"{1, 2, 3}\" AS nums2\n" +
            "SET \"{1, 2, 2}\" AS nums3\n" +
            "ON nums1 == nums2 {\n" +
            "\tLOG \"MATCH\"\n" +
            "}\n" +
            "\n" +
            "ON nums2 != nums3 {\n" +
            "\tON nums1 == nums3 {\n" +
            "\t\tLOG \"MISMATCH\"\n" +
            "\t} ELSE {\n" +
            "\t\tLOG \"MISMATCH\"\n" +
            "\t}\n" +
            "}";

    public static String e2e_nestedOnElse_output = "MATCH\n" +
            "MISMATCH\n" +
            "\n" +
            "\n";

    public static String e2e_cat_2_success_output = "YES";

    public static String e2e_cat_EmptyElse_failed_input = "SET GET https://cat-fact.herokuapp.com/facts/ AS cats\n" +
            "FOR EACH cat IN cats {\n" +
            "\tON cat.AQLKEY == \"text\" {\n" +
            "\t\tLOG cat\n" +
            "\t}\n" + " ELSE { \n \t }" +
            "}";

    public static String e2e_cat_EmptyON_failed_input = "SET GET https://cat-fact.herokuapp.com/facts/ AS cats\n" +
            "FOR EACH cat IN cats {\n" +
            "\tON cat.AQLKEY == \"text\" {\n" +
            "\t}\n" +
            "}";

    public static String e2e_cat_EmptyON_failed_ouput = "Empty ON body";

    public static String e2e_cat_EmptyElse_failed_ouput = "Empty ELSE body";


    public static String e2e_cat_EmptyLog_failed_input = "SET GET https://cat-fact.herokuapp.com/facts/ AS cats\n" +
            "FOR EACH cat IN cats {\n" +
            "\tON cat.AQLKEY == \"text\" {\n" +
            "\t\tLOG \n" +
            "\t}\n" +
            "}";

    public static String e2e_cat_EmptyLog_failed_output = "Invalid LOG: missing message to print";

    public static String e2e_cat_dynamicVar_failed_input = "SET GET https://cat-fact.herokuapp.com/facts/ AS cats\n" +
            "FOR EACH cat IN cats {\n" +
            "\tON cat.AQLKEYs == \"text\" {\n" +
            "\t\tLOG cat\n" +
            "\t}\n" +
            "}";

    public static String e2e_cat_dynamicVar_failed_output = "ON/ELSE Error: Property `AQLKEYs` not found for variable `cat`";

    public static String e2e_cat_Variable_failed_input = "SET GET https://cat-fact.herokuapp.com/facts/ AS cats\n" +
            "FOR EACH cat IN cats {\n" +
            "\tON cat.AQLKEY == \"text\" {\n" +
            "\t\tLOG dog\n" +
            "\t}\n" +
            "}";

    public static String e2e_cat_Variable_failed_output = "LOG Error: Variable `dog` not defined in environment.";

    public static String e2e_cat_Condition_failed_input = "SET GET https://cat-fact.herokuapp.com/facts/ AS cats\n" +
            "FOR EACH cat IN cats {\n" +
            "\tON cat.AQLKEY += \"text\" {\n" +
            "\t\tLOG cat\n" +
            "\t}\n" +
            "}";

    public static String e2e_cat_Condition_failed_output = "Invalid condition format. cat.AQLKEY\"text\"";

    public static String e2e_cat_Comparator_failed_input = "SET GET https://cat-fact.herokuapp.com/facts/ AS cats1\n" +
            "SET GET https://cat-fact.herokuapp.com/facts/ AS cats2\n" +
            "\tON cats1 > cats2 {\n" +
            "\t\tLOG \"YES\"\n" +
            "}";

    public static String e2e_cat_Comparator_failed_output = "ON/ELSE Error: Unsupported operator for JSONArray comparison: >";

    public static String e2e_cat_URL1_failed_input = "SET GET https:/cat-fact.herokuapp.com/facts/ AS cats\n" +
            "FOR EACH cat IN cats {\n" +
            "\tON cat.AQLKEY == \"text\" {\n" +
            "\t\tLOG cat\n" +
            "\t}\n" +
            "}";

    public static String e2e_cat_URL1_failed_output = "Syntax errors found.\n" +
            "Syntax error at line 1:8 - missing URI at 'https'Syntax error at line 1:13 " +
            "- mismatched input ':' expecting {'AS', 'WITH', '{', '.', URI_TAIL, VARIABLE}";

    public static String e2e_cat_URL2_failed_input = "SET GET https//cat-fact.herokuapp.com/facts/ AS cats\n" +
            "FOR EACH cat IN cats {\n" +
            "\tON cat.AQLKEY == \"text\" {\n" +
            "\t\tLOG cat\n" +
            "\t}\n" +
            "}";

    public static String e2e_cat_URL2_failed_output = "Syntax errors found.\n" +
            "Syntax error at line 1:8 - missing URI at 'https'";

    public static String e2e_local_scope_failed_input = "SET GET https://65y4r.wiremockapi.cloud/users/tasks2 AS users\n" +
            "FOR EACH user IN users {\n" +
            "\t\tSET 3 AS x\n" +
            "\t}\n" +
            "LOG x";
    public static String e2e_local_scope_failed_output = "LOG Error: Variable `x` not defined in environment.";

    public static String e2e_cat_URL_local_scope_failed_input = "SET GET https://cat-fact.herokuapp.com/facts/ AS users\n" +
            "FOR EACH user IN users {\n" +
            "\t\tSET 3 AS x\n" +
            "\t}\n" +
            "LOG x";

    public static String e2e_cat_URL_dupe_var_name_input = "SET GET https://cat-fact.herokuapp.com/facts/ AS users\n" +
            "FOR EACH user IN users { \n" +
            "\t\tSET 3 AS x\n" +
            "\t}\n" +
            "SET 4 AS x\n" +
            "LOG x";
    public static String e2e_cat_URL_dupe_var_name_output = "4";

    public static String e2e_nested_scopes_loop_fail_early_input = "SET GET https://cat-fact.herokuapp.com/facts/ AS users\n" +
            "FOR EACH user IN users { \n" +
            "\t\tSET 3 AS x\n" +
            "\t\tFOR EACH joke IN GET https://65y4r.wiremockapi.cloud/users/tasks2 {\n" +
            "\t\t\t\tSET 5 AS y\n" +
            "\t\t\t\tLOG y\n" +
            "\t\t\t}\n" +
            "\t\tLOG y\n" +
            "\t}";
    public static String e2e_nested_scopes_loop_fail_early_output = "5\n" +
            "5\n" +
            "LOG Error: Variable `y` not defined in environment.\n";

    public static String e2e_nested_scopes_loop_input = "SET GET https://cat-fact.herokuapp.com/facts/ AS users\n" +
            "FOR EACH user IN users { \n" +
            "\t\tSET 3 AS x\n" +
            "\t\tFOR EACH joke IN GET https://65y4r.wiremockapi.cloud/users/tasks2 {\n" +
            "\t\t\t\tSET 5 AS y\n" +
            "\t\t\t\tLOG y\n" +
            "\t\t\t}\n" +
            "\t}\n" +
            "LOG y";
    public static String e2e_nested_scopes_loop_output = "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "5\n" +
            "LOG Error: Variable `y` not defined in environment.\n";

    public static String e2e_nested_scope_success_input = "SET GET https://65y4r.wiremockapi.cloud/users/tasks2 AS users\n" +
            "FOR EACH user IN users { \n" +
            "\t\tSET 3 AS x\n" +
            "\t\tFOR EACH joke IN GET https://65y4r.wiremockapi.cloud/users/tasks2 {\n" +
            "\t\t\t\tSET 5 AS y\n" +
            "\t\t\t\tLOG x\n" +
            "\t\t\t\tLOG user\n" +
            "\t\t\t\tLOG joke\n" +
            "\t\t\t}\n" +
            "\t\tLOG x\n" +
            "\t}";
    public static String e2e_nested_scope_success_ouput = "3\n" +
            "{\"task1\":\"haha\"}\n" +
            "{\"task1\":\"haha\"}\n" +
            "3\n" +
            "{\"task1\":\"haha\"}\n" +
            "{\"task2\":\"haha\"}\n" +
            "3\n" +
            "3\n" +
            "{\"task2\":\"haha\"}\n" +
            "{\"task1\":\"haha\"}\n" +
            "3\n" +
            "{\"task2\":\"haha\"}\n" +
            "{\"task2\":\"haha\"}\n" +
            "3";

    public static String e2e_local_scope_failed_on_else_input = "ON 2 == 3 { \n" +
            "\tSET 3 AS x\n" +
            "\tLOG x\n" +
            "\tLOG \"SUCCESS\" \n" +
            "\t} ELSE { \n" +
            "\t\tLOG x\n" +
            "\t\tLOG \"FAILED\" \n" +
            "\t\t}";

    public static String e2e_local_scope_failed_on_else_output = "LOG Error: Variable `x` not defined in environment.\n";

    public static String e2e_local_scope_success_on_else_input = "SET 4 AS x\n" +
            "ON 2 == 2 { \n" +
            "\tSET 3 AS x\n" +
            "\tLOG x\n" +
            "\tLOG \"SUCCESS\" \n" +
            "\t} ELSE { \n" +
            "\t\tLOG \"FAILED\" \n" +
            "\t\t}";

    public static String e2e_local_scope_success_on_else_output = "3\n" +
            "SUCCESS\n";

    public static String e2e_local_scope_success_on_else_nested_input = "ON 2 == 2 { \n" +
            "\tSET 3 AS x\n" +
            "\tLOG x\n" +
            "\tON 1 == 1 {\n" +
            "\t\t\tSET 4 AS y\n" +
            "\t\t\tLOG y\n" +
            "\t\t} ELSE {\n" +
            "\t\t\t\tLOG \"FAILED nest\"\n" +
            "\t\t\t}\n" +
            "\tLOG \"SUCCESS\" \n" +
            "\t} ELSE { \n" +
            "\t\tLOG \"FAILED\" \n" +
            "\t\t}";
    public static String e2e_local_scope_success_on_else_nested_ouput = "3\n" +
            "4\n" +
            "SUCCESS\n";
    public static String e2e_local_scope_fail_on_else_nested_input = "ON 2 == 2 { \n" +
            "\tSET 3 AS x\n" +
            "\tON 1 == 1 {\n" +
            "\t\tSET 4 AS y\n" +
            "\t} ELSE {\n" +
            "\t\tLOG \"FAILED nest\"\n" +
            "\t}\n" +
            "\tLOG y\n" +
            "\tLOG \"SUCCESS\" \n" +
            "} ELSE { \n" +
            "\tLOG \"FAILED\" \n" +
            "}";
    public static String e2e_local_scope_fail_on_else_nested_ouput = "LOG Error: Variable `y` not defined in environment.\n";

}