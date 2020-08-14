package ru.stqa.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class RegistrationTests extends TestBase {

  @BeforeMethod
  //0000001-id issue со статусом new
  //0000002-id issue со статусом closed
  public void isNeedToRunTest() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(0000001);
  }

  @Test
  public void testRegisration() throws IOException, MessagingException {
    long now = System.currentTimeMillis();
    String user = "user" + now;
    String password = "password";
    String email = String.format("user%s@localhost",now);
    app.james().createUser(user,password);
    app.registration().start(user, email);
    //List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000); - для встроенного почтового сервера
    List<MailMessage> mailMessages = app.james().waitForMail(user,password, 60000);//для внешнего почтового сервера
    String confirmationLink = app.mail().findConfirmationLink(mailMessages,email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }



  //@AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
