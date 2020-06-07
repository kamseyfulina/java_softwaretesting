package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("company"), contactData.getCompany());
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());
    selectByText(By.name("bday"), contactData.getBday());
    selectByText(By.name("bmonth"), contactData.getBmonth());
    type(By.name("byear"), contactData.getByear());

    if (creation) {
      selectByText(By.name("new_group"), contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }


  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
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

  public void goToCreateContactPage() {
    click(By.linkText("add new"));
  }

  public void returnToHomePage() {
    //данный клик используется для возврата на дом.страницу со страницы создания контакта
    click(By.linkText("home page"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createContact(ContactData contact) {
    goToCreateContactPage();
    fillContactForm(contact,true);
    submitContactCreation();
    returnToHomePage();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("table[id=maintable].center"));
    for (WebElement element:elements){
      String name = element.getText();
      ContactData contact = new ContactData(name,"","","","","","","","","","","");
      contacts.add(contact);
    }
    return contacts;
  }
}
