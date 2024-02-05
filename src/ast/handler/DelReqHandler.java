package ast.handler;

import org.json.JSONObject;

import controller.APIService;

public class DelReqHandler extends BaseReqHandler {

    public DelReqHandler(APIService apiService) {
        super(apiService);
    }

    @Override
    protected Object makeApiCall(String uri, JSONObject params) throws Exception {
        return this.apiService.makeDeleteRequest(uri, params);
    }
}