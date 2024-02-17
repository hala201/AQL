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

    public static String e2e_trip_success_1_input = "SET POST https://travelerstea-906d.onrender.com/api/users/login WITH { \"email\": \"testnlm@nlm\", \"password\": \"123\" } AS list\n" +
            "SET POST https://travelerstea-906d.onrender.com/api/trips WITH {\n" +
            "\t\"tripLocation\": \"LONDON\",\n" +
            "\t\"stagesPerDay\": 1,\n" +
            "\t\"budget\": 100,\n" +
            "\t\"numberOfDays\": 1,\n" +
            "\t\"AQL@Authorization\": {list.accessToken}\n" +
            "} AS RESULT\n" +
            "LOG RESULT";
    public static String e2e_trip_success_1_output = "{\"stagesPerDay\":1,\"tripLongitude\":-0.12695659999999998,\"__v\":0,\"isPublic\":false,\"tripLocation\":\"LONDON\",\"_id\":\"65d109a8f270343a277d3a43\",\"numberOfDays\":1,\"tripLatitude\":51.51941329999999,\"budget\":100}\n";

    public static String e2e_aqlserver_success_1_input = "SET GET https://aqlserver.onrender.com/users AS stuList\n" +
            "LOG stuList\n" +
            "FOR EACH stu IN stuList {\n" +
            "\tON stu.AQLKEY == \"_id\" {\n" +
            "\t\tSET GET https://aqlserver.onrender.com/users/{stu._id}/tasks AS tasks\n" +
            "\t\tFOR EACH task IN tasks {\n" +
            "\t\t\tLOG task\n" +
            "\t\t}\n" +
            "\t}\n" +
            "}";
    public static String e2e_aqlserver_success_1_output = "[{\"__v\":0,\"name\":\"John Doe\",\"_id\":\"65cd34f301b10b181cec335b\",\"email\":\"john.doe@example.com\"}]\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"this is task 1\"}\n" +
            "{\"_id\":\"65cd35ee01b10b181cec3360\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Task 1\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"this is task 2\"}\n" +
            "{\"_id\":\"65cd382b8e32036d2c4a3c39\"}\n" +
            "{\"completed\":true}\n" +
            "{\"title\":\"Task 2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d00dcb6d4699470705ce3a\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d00dcf6d4699470705ce3d\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d00dfa6d4699470705ce40\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d00e2b6d4699470705ce43\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d00e756d4699470705ce46\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d00e7d6d4699470705ce4a\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d00ed36d4699470705ce4d\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d00ee86d4699470705ce50\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d00eef6d4699470705ce53\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast2\"}\n" +
            "{\"_id\":\"65d00fe36d4699470705ce57\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d010316d4699470705ce5a\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d0107a6d4699470705ce5d\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d010cc6d4699470705ce60\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d011466d4699470705ce64\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d011466d4699470705ce68\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d011796d4699470705ce6c\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d0117a6d4699470705ce70\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d0118e6d4699470705ce74\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d011b86d4699470705ce80\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d011f36d4699470705ce85\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d011f36d4699470705ce89\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d013006d4699470705ce8e\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d013006d4699470705ce92\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d013e36d4699470705ce97\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d013e36d4699470705ce9b\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d0145a6d4699470705cea0\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d0145b6d4699470705cea4\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d014aa6d4699470705cea9\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d014ab6d4699470705cead\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d014e26d4699470705ceb2\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d014e36d4699470705ceb6\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d015186d4699470705cebb\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d015196d4699470705cebf\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d015456d4699470705cec4\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d015466d4699470705cec8\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d0156d6d4699470705cecd\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d0156d6d4699470705ced1\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d015946d4699470705ced6\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d015956d4699470705ceda\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d015c56d4699470705cedf\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d015c56d4699470705cee3\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d0177a6d4699470705cee8\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d0177a6d4699470705ceec\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d0f37fc8ec763ab142ba25\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d0f380c8ec763ab142ba29\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d0facbc8ec763ab142ba2e\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d0faccc8ec763ab142ba32\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d1017bc8ec763ab142ba37\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n" +
            "{\"__v\":0}\n" +
            "{\"description\":\"Eat breakfast\"}\n" +
            "{\"_id\":\"65d1017cc8ec763ab142ba3b\"}\n" +
            "{\"completed\":false}\n" +
            "{\"title\":\"Breakfast2\"}\n" +
            "{\"user\":\"65cd34f301b10b181cec335b\"}\n";

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
}