package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTests extends TestBase {
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
  public void TestContactPhones(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return  Arrays.asList(contact.getHome(),contact.getMobile(),contact.getWork())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public  static String cleaned (String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
