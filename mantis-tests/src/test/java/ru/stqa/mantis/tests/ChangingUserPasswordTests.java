package ru.stqa.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.mantis.model.MailMessage;
import ru.stqa.mantis.model.UserData;
import ru.stqa.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ChangingUserPasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangingUserPassword() throws IOException, MessagingException {
    Users users = app.db().usersWithoutAdmin();

    UserData user = users.iterator().next();
    String userName = user.getName();
    String userEmail = user.getEmail();
    String newPassword = "Super_password";
    app.session().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.goTo().usersManageTab();
    app.user().selectById(user.getId());
    app.user().initPasswordReset();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = app.mail().findConfirmationLink(mailMessages, userEmail);
    app.registration().finish(confirmationLink, newPassword);
    assertTrue(app.newSession().login(userName, newPassword));

  }

}
