package controller.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LocalScope {
    public static void pushScope(Stack<Map<String, Integer>> localEnvironmentStack, Map<String, Integer> environment) {
        localEnvironmentStack.push(new HashMap<>(environment));
    }

    public static void popScope(Stack<Map<String, Integer>> localEnvironmentStack) {
        if(!localEnvironmentStack.isEmpty()) localEnvironmentStack.pop();
    }

}
