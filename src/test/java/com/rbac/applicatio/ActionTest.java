package com.rbac.applicatio;

import org.apache.struts2.StrutsJUnit4TestCase;
import org.junit.Test;

import javax.servlet.ServletException;
import java.io.UnsupportedEncodingException;

public class ActionTest extends StrutsJUnit4TestCase {

    @Test
    public void testAction() throws ServletException, UnsupportedEncodingException {
        String result = executeAction("/test/page");
        System.out.println("Show " + result);
//        assertEquals("", result);
    }

}
