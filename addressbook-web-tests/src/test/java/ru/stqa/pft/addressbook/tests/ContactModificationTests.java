package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getGroupCount();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Иван", "Иванович", "Иванов", "ivanov", "ST", "88435235412", "89503336699", "ivanov@testmail.ru", "26", "January", "1992","test"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Олег", "Иванович", "Иванов", "ivanov", "ST", "88435235412", "89503336699", "ivanov@testmail.ru", "26", "January", "1992",null),false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getGroupCount();
    Assert.assertEquals(after,before);
  }
}
