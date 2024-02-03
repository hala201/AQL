package ast.handler;

import controller.APIService;

public class GetReqHandler extends BaseReqHandler {

    public GetReqHandler(APIService apiService) {
        super(apiService);
    }

    @Override
    protected Object makeApiCall(String uri) throws Exception {
        return this.apiService.makeGetRequest(uri);
    }
}