package com.pynexia.week1.login;

import org.junit.Test;
import static org.junit.Assert.*;
public class DemoAuthenticatorTest {
    @Test public void acceptsUsernameOrEmail() {
        assertTrue(DemoAuthenticator.authenticate("learner", "Learn@123"));
        assertTrue(DemoAuthenticator.authenticate(" LEARNER@example.com ", "Learn@123"));
    }
    @Test public void rejectsWrongCredentials() {
        assertFalse(DemoAuthenticator.authenticate("someone", "Learn@123"));
        assertFalse(DemoAuthenticator.authenticate("learner", "learn@123"));
        assertFalse(DemoAuthenticator.authenticate("learner", " Learn@123 "));
        assertFalse(DemoAuthenticator.authenticate("", ""));
    }
}
