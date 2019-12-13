/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.clientejwt;

import java.net.HttpURLConnection;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author avbravo
 */
public class ClienteJWTTest {
    
    public ClienteJWTTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of run method, of class ClienteJWT.
     */
    @Test
    public void testRun() throws Exception {
        System.out.println("run");
        ClienteJWT instance = new ClienteJWT();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTokens method, of class ClienteJWT.
     */
    @Test
    public void testGetTokens() {
        System.out.println("getTokens");
        ClienteJWT instance = new ClienteJWT();
        String expResult = "";
        String result = instance.getTokens();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of connect method, of class ClienteJWT.
     */
    @Test
    public void testConnect() throws Exception {
        System.out.println("connect");
        ClienteJWT instance = new ClienteJWT();
        Response expResult = null;
        Response result = instance.connect();
        
               assertEquals(expResult.getStatus(), HttpURLConnection.HTTP_OK);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
