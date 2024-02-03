package controller;

import java.util.List;

public interface IRequest {
    String getHead();
    List<String> getBody();
    List<String> getTail();
}
