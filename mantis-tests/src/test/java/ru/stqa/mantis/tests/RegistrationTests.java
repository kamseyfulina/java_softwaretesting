package ru.stqa.mantis.tests;

import org.testng.annotations.Test;

import static ru.stqa.mantis.tests.TestBase.app;

public class RegistrationTests {

    @Test
    public  void testRegisration(){
        app.registration().start("user1","user1@localhost.localadmin");
    }
}
