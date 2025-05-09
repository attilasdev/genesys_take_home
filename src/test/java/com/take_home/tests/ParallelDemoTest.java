package com.take_home.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.take_home.pages.LoginPage;


public class ParallelDemoTest extends BaseTest {

    @Test
    @DisplayName("Parallel Demo Test 1")
    public void parallelTest1() {
        long threadId = Thread.currentThread().getId();
        logStep("Running test 1 in thread: " + threadId);
        
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        
        
        logStep("Test 1 sleeping to demonstrate parallel execution...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        logStep("Test 1 completing in thread: " + threadId);
        
    }

    @Test
    @DisplayName("Parallel Demo Test 2")
    public void parallelTest2() {
        long threadId = Thread.currentThread().getId();
        logStep("Running test 2 in thread: " + threadId);
        
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        
        logStep("Test 2 completing in thread: " + threadId);
        
    }

    @Test
    @DisplayName("Parallel Demo Test 3")
    public void parallelTest3() {
        long threadId = Thread.currentThread().getId();
        logStep("Running test 3 in thread: " + threadId);
        
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        
        
        logStep("Test 3 sleeping to demonstrate parallel execution...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        logStep("Test 3 completing in thread: " + threadId);
        
    }
}