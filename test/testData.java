public class testData {
    static String simpleLog_input = "LOG 'Hello World'";
    static String simpleLog_parse_output = "(program (statement (log LOG (string 'Hello World'))))";

    static String LogWithVariable_input = "LOG variableName";
    static String LogWithVariable_parse_output = "(program (statement (log LOG (value variableName))))";

    static String simpleGet_input = "GET https://api.example.com/data";
    static String simpleGet_parse_output = "(program (statement (request (getReq GET (dynamicURI https://api.example.com/data)))))";

    static String simplePut_input = "PUT https://8k06e.wiremockapi.cloud/json/2 WITH { \"id\": 12345, \"value\": \"abc-def-ghi\" }";
    static String simplePut_parse_output = "(program (statement (request (putReq PUT (dynamicURI https://8k06e.wiremockapi.cloud/json/2) (withBlock WITH { (params (param (string \"id\") : (value 12345)) , (param (string \"value\") : (string \"abc-def-ghi\"))) })))))";

    static String simplePost_input = "POST https://8k06e.wiremockapi.cloud/json WITH { \"id\": 12345, \"value\": \"abc-def-ghi\" }";
    static String simplePost_parse_output = "(program (statement (request (postReq POST (dynamicURI https://8k06e.wiremockapi.cloud/json) (withBlock WITH { (params (param (string \"id\") : (value 12345)) , (param (string \"value\") : (string \"abc-def-ghi\"))) })))))";

    static String simpleDelete_input = "DELETE https://api.example.com/data";
    static String simpleDelete_parse_output = "(program (statement (request (delReq DELETE (dynamicURI https://api.example.com/data)))))";

    static String withBlock_input = """
        GET https://api.example.com/data WITH { "param1": value1, "param2": 'value2' }
                """;
    static String withBlock_parse_output = "(program (statement (request (getReq GET (dynamicURI https://api.example.com/data) (withBlock WITH { (params (param (string \"param1\") : (value value1)) , (param (string \"param2\") : (string 'value2'))) })))))";

    static String onElseCondition_input = "GET https://api.example.com/data ON variable1 == variable2 { LOG variable1 } ELSE { LOG 'Error' }";
    static String onElseCondition_parse_output = "(program (statement (request (getReq GET (dynamicURI https://api.example.com/data) (onElse ON (condition (value variable1) == (value variable2)) { (statement (log LOG (value variable1))) } ELSE { (statement (log LOG (string 'Error'))) })))))";

    static String setStatement_input = "SET GET https://api.example.com/data AS myData";
    static String setStatement_parse_output = "(program (statement (set SET (request (getReq GET (dynamicURI https://api.example.com/data))) AS myData)))";

    static String loopStatement_input = "FOR EACH user IN GET https://api.example.com/users { LOG user.name }";
    static String loopStatement_parse_output = "(program (statement (loop FOR EACH user IN (request (getReq GET (dynamicURI https://api.example.com/users))) { (statement (log LOG (value (dynamicVar user . name)))) })))";

    static String complex1_input = """
        FOR EACH user IN GET https://api.com/users {
            LOG user.name
            SET GET https://api.com/users/{user.id}/tasks AS TaskList
            SET 'failed' AS failMsg
        
            FOR EACH task IN TaskList {
                PUT https://api.com/tasks/{task.id} WITH { "status": 'completed' }
        
                POST https://api.com/send WITH {
                    "userId": user.id,
                    "message": 'Task {task.id} completed'
                }
        
                ON task.sth==false {
                    DELETE https://api.com/tasks/{task.id}
                } ELSE {
                    LOG failMsg
                }
            }
        }
                """;
    static String complex1_parse_output = "(program (statement (loop FOR EACH user IN (request (getReq GET (dynamicURI https://api.com/users))) { (statement (log LOG (value (dynamicVar user . name)))) (statement (set SET (request (getReq GET (dynamicURI https://api.com/users/ (dynamicVar { user . id }) /tasks))) AS TaskList)) (statement (set SET (string 'failed') AS failMsg)) (statement (loop FOR EACH task IN TaskList { (statement (request (putReq PUT (dynamicURI https://api.com/tasks/ (dynamicVar { task . id })) (withBlock WITH { (params (param (string \"status\") : (string 'completed'))) })))) (statement (request (postReq POST (dynamicURI https://api.com/send) (withBlock WITH { (params (param (string \"userId\") : (value (dynamicVar user . id))) , (param (string \"message\") : (string 'Task {task.id} completed'))) }) (onElse ON (condition (value (dynamicVar task . sth)) == (value false)) { (statement (request (delReq DELETE (dynamicURI https://api.com/tasks/ (dynamicVar { task . id }))))) } ELSE { (statement (log LOG (value failMsg))) })))) })) })))";
}
