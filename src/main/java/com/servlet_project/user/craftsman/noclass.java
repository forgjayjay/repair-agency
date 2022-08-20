package com.servlet_project.user.craftsman;

public class noclass {
    public static void main(String[] args) {
        String str = "weed";
        String num = "2";
        try {
            System.out.println(Integer.valueOf(str) + " " + Integer.valueOf(num));
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
