package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactModificationTests extends TestBase {
  String defaultNameGroup;
  @BeforeTest
  public void ensurePreconditions() {

    app.goTo().groupPage();
    defaultNameGroup = app.group().defaultNameGroup();

    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("Петр").withMiddlename("Иванович").withLastname("Иванов")
              .withHome("88435235412").withMobile("89503336699").withWork("222222225566").withEmail("ivanov@testmail.ru").withAddress("ул. Главная"));
    }
  }

  @Test
  public void testContactModification(){
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Сидр").withLastname("Сидоров").withMiddlename(modifiedContact.getMiddlename()).withEmail(modifiedContact.getEmail()).withHome(modifiedContact.getHome()).withMobile(modifiedContact.getMobile()).withWork(modifiedContact.getWork()).withAddress(modifiedContact.getAddress());
    app.goTo().homePage();
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after,equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }




}
