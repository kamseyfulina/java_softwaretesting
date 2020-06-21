package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class ContactCreationTests extends TestBase {
  String defaultNameGroup;

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    defaultNameGroup = app.group().defaultNameGroup();
  }

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Петр").withMiddlename("Иванович").withLastname("Иванов")
            .withHome("88435235412").withMobile("89503336699").withEmail("ivanov@testmail.ru").withGroup(defaultNameGroup);
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);

  }
}
