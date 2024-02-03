package ast.handler;

import controller.APIService;

public class DelReqHandler extends BaseReqHandler {

    public DelReqHandler(APIService apiService) {
        super(apiService);
    }

    @Override
    protected Object makeApiCall(String uri) throws Exception {
        return this.apiService.makeDeleteRequest(uri);
    }
}