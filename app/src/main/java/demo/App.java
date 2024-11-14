package demo;

import com.gradle.CustomApp;

public class App {
    public String getGreeting() {
        return "CustomApp identifier is: " + CustomApp.identifier;
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
