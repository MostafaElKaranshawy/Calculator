package com.example.demo;
public class User {
    private String operand1 = new String();
    private String operand2 = new String();
    private String operator = new String();
    public User(String operand1, String operand2, String operator) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
    }
    public void setOperand1(String operand) {
        this.operand1 = operand;
    }
    public void setOperand2(String operand) {
        this.operand2 = operand;
    }
    public void setOperator(String operand) {
        this.operator = operand;
    }
    public String getOperand1() {
        return operand1;
    }
    public String getOperand2() {
        return operand2;
    }
    public String getOperator() {
        return operator;
    }
}
