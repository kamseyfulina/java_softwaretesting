package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("Петр").withMiddlename("Иванович").withLastname("Иванов")
              .withHome("88435235412").withMobile("89503336699").withWork("222222225566").withEmail("ivanov@testmail.ru"));
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test"));
    }
  }

  @Test
  public void testContactDeletionFromGroup() {
    GroupData groupForContact = null;
    ContactData contactForDeletion = null;
    Integer idGroup;
    boolean needForSearch = true;
    Groups dbGroups = app.db().groups();
    Contacts dbContacts = app.db().contacts();

    for (GroupData dbgroup : dbGroups) {

      if (needForSearch = false) {
        break;
      } else {
        if (dbgroup.getContacts().size() > 0) {
          contactForDeletion = dbgroup.getContacts().iterator().next();
          groupForContact = dbgroup;
          needForSearch = false;
          break;
        }
      }
    }

    app.goTo().homePage();
    if (groupForContact == null) {
      groupForContact = dbGroups.iterator().next();
      contactForDeletion = dbContacts.iterator().next();
      app.contact().addToGroup(contactForDeletion, groupForContact);
      app.goTo().homePage();
    }

    app.contact().removeFromGroup(contactForDeletion, groupForContact);
    app.goTo().homePage();
    idGroup = groupForContact.getId();
    GroupData groupForRemove = (GroupData) app.db().groups().stream().filter(a -> Objects.equals(idGroup, a.getId())).findFirst().orElse(null);
    assertThat(groupForRemove.getContacts().contains(contactForDeletion), equalTo(false));
  }
}
