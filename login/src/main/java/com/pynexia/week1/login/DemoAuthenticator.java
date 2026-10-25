package com.pynexia.week1.login;

public final class DemoAuthenticator {
    private DemoAuthenticator() {}
    public static boolean authenticate(String identity, String password) {
        String normalized = identity.trim();
        return (normalized.equalsIgnoreCase("learner") || normalized.equalsIgnoreCase("learner@example.com"))
                && password.equals("Learn@123");
    }
}
