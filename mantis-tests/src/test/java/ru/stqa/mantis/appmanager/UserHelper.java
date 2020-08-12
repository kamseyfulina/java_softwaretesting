package ru.stqa.mantis.appmanager;

import org.openqa.selenium.By;

public class UserHelper extends HelperBase {
  public UserHelper(ApplicationManager app) {
    super(app);
  }


  public void selectById(int userId) {
    click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + userId + "']"));
  }

  public void initPasswordReset() {
    click(By.xpath("//input[@value='Reset Password']"));
  }
}
