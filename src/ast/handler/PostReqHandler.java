package ast.handler;

import controller.APIService;
import org.json.JSONObject;

public class PostReqHandler extends BaseReqHandler {

    public PostReqHandler(APIService apiService) {
        super(apiService);
    }

    @Override
    protected Object makeApiCall(String uri, JSONObject params) throws Exception {
        return this.apiService.makePostRequest(uri, params);
    }
}