package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;


public class ContactDeletionTests extends TestBase{
  String defaultNameGroup;

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    defaultNameGroup = app.group().defaultNameGroup();

    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().groupPage();
      defaultNameGroup = app.group().defaultNameGroup();
      app.contact().create(new ContactData().withFirstname("Петр").withMiddlename("Иванович").withLastname("Иванов")
              .withHome("88435235412").withMobile("89503336699").withEmail("ivanov@testmail.ru").withGroup(defaultNameGroup));
    }
  }

  @Test
  public void testContactDeletion(){
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(),before.size() - 1);
    MatcherAssert.assertThat(after, equalTo(before.without(deletedContact)));
  }


}
