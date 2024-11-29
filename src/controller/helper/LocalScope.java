package controller.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LocalScope {
    public static void pushScope(Stack<Map<String, Integer>> localEnvironmentStack, Map<String, Integer> environment) {
        Map<String, Integer> newScope = new HashMap<>(environment);
        if (!localEnvironmentStack.isEmpty()) {
            newScope.putAll(localEnvironmentStack.peek());
        }
        localEnvironmentStack.push(newScope);
    }

    public static void popScope(Stack<Map<String, Integer>> localEnvironmentStack) {
        if(!localEnvironmentStack.isEmpty()) localEnvironmentStack.pop();
    }

}
