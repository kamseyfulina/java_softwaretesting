package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;


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
    attach(By.name("photo"), contactData.getPhoto());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("company"), contactData.getCompany());
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("work"), contactData.getWork());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("address"), contactData.getAddress());


//    selectByText(By.name("bday"), contactData.getBday());
//    selectByText(By.name("bmonth"), contactData.getBmonth());
//    type(By.name("byear"), contactData.getByear());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        selectByText(By.name("new_group"), contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
    assertConfirmation();
  }

  public void initContactModificationById(int id) {
    click(By.cssSelector("a[href='edit.php?id=" + id + "']"));

  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHome(home).withMobile(mobile).withWork(work).withAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
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

  public void create(ContactData contact) {
    goToCreateContactPage();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    contactCache = null;
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);//возвращаем попию кеша
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("#maintable tr:not(:first-child)"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();


      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }


  public void addToGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    selectByText(By.name("to_group"),group.getName());
    wd.findElement(By.name("add")).click();
  }

  public void removeFromGroup(ContactData contact, GroupData group) {
    selectByText(By.name("group"), group.getName());
    selectContactById(contact.getId());
    wd.findElement(By.name("remove")).click();
  }
}
