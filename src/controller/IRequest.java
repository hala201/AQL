package controller;

import java.util.List;

import org.json.JSONObject;

public interface IRequest {
    String getHead();
    List<String> getBody();
    List<String> getTail();
    JSONObject getParams();
}
