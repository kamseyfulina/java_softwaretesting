package ru.stqa.mantis.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

  public SessionHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.xpath("//input[@value='Login']"));
  }
}