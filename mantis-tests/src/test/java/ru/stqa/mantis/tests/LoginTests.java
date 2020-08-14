package ru.stqa.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.mantis.appmanager.HttpSession;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import static org.testng.Assert.*;

public class LoginTests extends TestBase{

  @BeforeMethod
  public void isNeedToRunTest() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(0000002);
  }

  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
    assertTrue(session.login("administrator","root"));
    assertTrue(session.isLoggedInAs("administrator"));
  }
}
