package ast.handler;

import controller.APIService;
import org.json.JSONObject;

public class PutReqHandler extends BaseReqHandler {

    public PutReqHandler(APIService apiService) {
        super(apiService);
    }

    @Override
    protected Object makeApiCall(String uri, JSONObject params) throws Exception {
        return this.apiService.makePutRequest(uri, params);
    }
}