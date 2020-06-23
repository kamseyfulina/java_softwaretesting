package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/stru.jpg");
    ContactData contact = new ContactData().withFirstname("Петр").withMiddlename("Иванович").withLastname("Иванов")
            .withHome("88435235412").withMobile("89503336699").withWork("222222225566")
                    .withEmail("ivanov@testmail.ru").withGroup(defaultNameGroup).withPhoto(photo);
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after,equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));


  }

  @Test
  public void testCurrentDir(){
    File currentDir = new File(".");
  }
}
