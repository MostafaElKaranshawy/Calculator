package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.User;

@Service
public class userService {
    private String ans = "0";
    // List<User> userlist = new ArrayList<User> (Arrays.asList(
    //     new User("1", "2", "+")
    //     // new User("2", "3", "-"),
    //     // new User("3", "2", "+")
    // ));
    // public List<User> getUserlist() {
    //     return userlist;
    // }
    // public User getUser(String operand){
    //     for(User user : userlist){
    //         if(user.getOperand1().equals(operand))
    //             return user;
    //     }
    //     return null;
    // }
    public String get(){
        return this.ans;
    }
    public String save(User user){
        this.ans = "0";
        float a = Float.parseFloat(user.getOperand1());
        float b = Float.parseFloat(user.getOperand2());
        String s = user.getOperator();
        float ans1 = 0;
        if(s.equals("+"))ans1 = a + b;
        else if(s.equals("-"))ans1 = a - b;
        else if (s.equals("*"))ans1 = a * b;
        else if (s.equals("/")){
            if(b == 0)ans = "E";
            else ans1 = a/b;
        }
        else if (s.equals("sqrt(x)"))ans1 = (float)Math.sqrt(b);
        else if (s.equals("1/x"))ans1 = 1/b;
        else if(s.equals("x2"))ans1 = b*b;
        else if(s.equals("%"))ans1 = b/100;
        if(!ans.equals("E"))ans = String.valueOf(ans1);
        user.setOperand1(ans);
        System.out.println(user.getOperand1() + " " + user.getOperand2() + " " + user.getOperator());
        return this.ans;
        // userlist.add(user);
        // return userlist.add(user);
    }
}
