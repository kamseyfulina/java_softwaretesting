package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("middlename"),contactData.getMiddlename());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("nickname"),contactData.getNickname());
    type(By.name("company"),contactData.getCompany());
    type(By.name("home"),contactData.getHome());
    type(By.name("mobile"),contactData.getMobile());
    type(By.name("email"),contactData.getEmail());
    selectByText(By.name("bday"),contactData.getBday());
    selectByText(By.name("bmonth"),contactData.getBmonth());
    type(By.name("byear"),contactData.getByear());
    //click(By.name("theform"));
  }


  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
    assertConfirmation();
  }

  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));

  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }
}
