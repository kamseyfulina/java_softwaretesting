package ru.stqa.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {
  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void usersManageTab() {
    click(By.cssSelector("a[href='/mantisbt-1.2.20/manage_user_page.php']"));

  }
}
