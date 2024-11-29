package ast.handler;

import org.json.JSONObject;

import controller.APIService;

public class GetReqHandler extends BaseReqHandler {

    public GetReqHandler(APIService apiService) {
        super(apiService);
    }

    @Override
    protected Object makeApiCall(String uri, JSONObject params) throws Exception {
        return this.apiService.makeGetRequest(uri, params);
    }
}