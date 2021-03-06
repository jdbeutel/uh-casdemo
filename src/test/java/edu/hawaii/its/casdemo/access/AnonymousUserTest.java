package edu.hawaii.its.casdemo.access;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class AnonymousUserTest {

    private User user;

    @Before
    public void setUp() {
        user = new AnonymousUser();
    }

    @Test
    public void testConstructions() {
        assertNotNull(user);
        assertEquals("anonymous", user.getUsername());
        assertEquals("anonymous", user.getUid());
        assertEquals(null, user.getUhuuid());
        assertEquals("", user.getPassword());
        assertEquals(1, user.getAuthorities().size());
        assertTrue(user.hasRole(Role.ANONYMOUS));
    }
}
