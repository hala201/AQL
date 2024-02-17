package helper;
public class testData {
    public static String simpleLog_input = "LOG \"Hello World\"";
    public static String simpleLog_parse_output = "(program (statement (log LOG (value (string \"Hello World\")))))";

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
    public static String simpleGet_input = "GET https://api.example.com/data";
    public static String simpleGet_parse_output = "(program (statement (request (getReq GET (dynamicURI https://api.example.com/data)))))";

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

}