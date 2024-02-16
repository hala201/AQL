package controller.helper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.json.JSONArray;
import org.json.JSONObject;

import ast.loop.Variable;
import controller.AQLVisitor;
import gen.AQLParser;

public class ConditionUtils {

    public static boolean isValidOperator(String operator) {
        Set<String> validOperators = new HashSet<>(Arrays.asList("==", "!=", ">", "<", ">=", "<="));
        return validOperators.contains(operator);
    }

    public static Object evaluateOperand(ParseTree node, AQLVisitor visitor) {
        if (node instanceof TerminalNode) {
            return getVar((TerminalNode) node);
        } else if (node instanceof AQLParser.DynamicVarContext) {
            return getDynamicVar((AQLParser.DynamicVarContext) node);
        } else if (node instanceof AQLParser.ValueContext) {
            return getValue((AQLParser.ValueContext) node);
        }
        return null;
    }

    public static Object getValue(AQLParser.ValueContext ctx) {
        if (ctx.NUMBER() != null) {
            return Double.parseDouble(ctx.NUMBER().getText());
        } else if (ctx.string() != null) {
            return ctx.string().getText().replaceAll("^\"|\"$", "");
        }
        throw new IllegalArgumentException("Unsupported value type in condition.");
    }

    public static Variable getVar(TerminalNode node) {
        return new Variable(node.getText(), null);
    }

    public static String getDynamicVar(AQLParser.DynamicVarContext ctx) {
        StringBuilder fullDynamicVar = new StringBuilder();
        if (ctx.VARIABLE().size() == 2) {
            String objectName = ctx.VARIABLE(0).getText();
            String propertyName = ctx.VARIABLE(1).getText();

            fullDynamicVar.append(objectName).append(".").append(propertyName);
        }
        if (ctx.getText().startsWith("{") && ctx.getText().endsWith("}")) {
            return "{" + fullDynamicVar.toString() + "}";
        } else {
            return fullDynamicVar.toString();
        }
    }

    // evaluates a string operand that could be a variable name, dynamic variable, or literal.
    public static Object evaluateOperand(Object operand, Map<String, Integer> environment, Map<Integer, Object> memory) {
        if (operand instanceof String) {
            if (((String) operand).startsWith("{") && ((String) operand).endsWith("}")) {
                return getDynamicVarValue((String) operand, environment, memory);
            }
            else {
                return ((String) operand).replaceAll("^\"|\"$", "");
            }
        } else if (operand instanceof Variable) {
            return getVarValue(((Variable) operand).getVariableName(), environment, memory);
        } else if (operand instanceof Number ||
                operand instanceof JSONArray ||
                operand instanceof JSONObject) {
            return operand;
        }

        throw new IllegalArgumentException("Unsupported operand type in condition.");
    }

    private static Object getDynamicVarValue(String dynamicVar, Map<String, Integer> environment, Map<Integer, Object> memory) {
        dynamicVar = dynamicVar.substring(1, dynamicVar.length() - 1);

        String[] parts = dynamicVar.split("\\.");
        if (parts.length == 2) {
            String variableName = parts[0];
            String propertyName = parts[1];
            if (environment.containsKey(variableName)) {
                Integer pointer = environment.get(variableName);
                if (memory.containsKey(pointer)) {
                    JSONObject data = (JSONObject) memory.get(pointer);
                    if (data.has(propertyName)) {
                        return data.get(propertyName);
                    } else {
                        throw new RuntimeException("Property `" + propertyName + "` not found for variable `" + variableName + "`");
                    }
                } else {
                    throw new RuntimeException("Variable `" + variableName + "` not in memory.");
                }
            } else {
                throw new RuntimeException("Variable `" + variableName + "` not set properly.");
            }
        } else {
            throw new RuntimeException("Dynamic variable `" + dynamicVar + "` improperly formatted.");
        }
    }

    private static Object getVarValue(String variableName, Map<String, Integer> environment, Map<Integer, Object> memory) {
        if (environment.containsKey(variableName)) {
            Integer pointer = environment.get(variableName);
            if (memory.containsKey(pointer)) {
                return memory.get(pointer);
            } else {
                throw new RuntimeException("Variable `" + variableName + "` not found in memory.");
            }
        } else {
            throw new RuntimeException("Variable `" + variableName + "` not defined in environment.");
        }
    }

    public static Boolean compareNumber(Number left, Number right, String operator) {
        switch (operator) {
            case "==": return left.equals(right);
            case "!=": return !left.equals(right);
            case "<": return left.doubleValue() < right.doubleValue();
            case "<=": return left.doubleValue() <= right.doubleValue();
            case ">": return left.doubleValue() > right.doubleValue();
            case ">=": return left.doubleValue() >= right.doubleValue();
            default: throw new IllegalArgumentException("Unsupported operator for number comparison: " + operator);
        }
    }

    public static Boolean compareString(String left, String right, String operator) {
        int comparison = left.compareTo(right);
        switch (operator) {
            case "==": return comparison == 0;
            case "!=": return comparison != 0;
            case "<": return comparison < 0;
            case "<=": return comparison <= 0;
            case ">": return comparison > 0;
            case ">=": return comparison >= 0;
            default: throw new IllegalArgumentException("Unsupported operator for string comparison: " + operator);
        }
    }

    public static Boolean compareJSONObject(JSONObject left, JSONObject right, String operator) {
        switch (operator) {
            case "==":
                return left.similar(right);
            case "!=":
                return !left.similar(right);
            default:
                throw new IllegalArgumentException("Unsupported operator for JSONObject comparison: " + operator);
        }
    }

    public static Boolean compareJSONArray(JSONArray left, JSONArray right, String operator) {
        switch (operator) {
            case "==":
                return left.similar(right);
            case "!=":
                return !left.similar(right);
            default:
                throw new IllegalArgumentException("Unsupported operator for JSONArray comparison: " + operator);
        }
    }
}