package com.rbac.applicatio;

import org.apache.struts2.StrutsJUnit4TestCase;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import java.io.UnsupportedEncodingException;

public class ActionTest extends StrutsJUnit4TestCase {

    @Autowired
    @Before
    public void setUp() {
        try {
            super.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {}

    @Test
    public void testAction() throws ServletException, UnsupportedEncodingException {
        String result = executeAction("/test/page");
        System.out.println("Show " + result);
//        assertEquals("", result);
    }

}
