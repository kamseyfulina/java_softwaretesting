package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailsTests  extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Петр").withMiddlename("Иванович").withLastname("Иванов")
              .withHome("88435235412").withMobile("89503336699")
              .withEmail("ivanov@testmail.ru").withEmail2("ivanov2@testmail.ru").withEmail3("ivanov3@testmail.ru"));

    }
  }

  @Test
  public void TestContactEmails(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return  Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactEmailsTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public  static String cleaned (String email){
    return email.replaceAll("\\s","");
  }
}
