package ru.stqa.pft.addressbook.tests;

import org.hibernate.SessionFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Objects;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddInGroup extends TestBase {

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
    public void testContactAddInGroup() {
        GroupData groupForContact = null;
        ContactData contactForAdd = null;
        Integer idContactForAdd;
        boolean needForSearch = true;
        Groups dbGroups = app.db().groups();
        Contacts dbContacts = app.db().contacts();
        for (ContactData dbcontact : dbContacts) {

            if(needForSearch = false){
                break;
            }
            else {
                if (!dbcontact.getGroups().equals(dbGroups)) {
                    for (GroupData dbGroup : dbGroups) {
                        if (!dbcontact.getGroups().contains(dbGroup)) {
                            groupForContact = dbGroup;
                            contactForAdd = dbcontact;
                            needForSearch = false;
                            break;
                        }
                    }
                }
            }
        }
        app.goTo().homePage();
        if (groupForContact == null){
            contactForAdd = new ContactData().withFirstname("Петр").withLastname("Иванов");
            app.contact().create(contactForAdd);
            groupForContact = dbGroups.iterator().next();
        }

        app.contact().addToGroup(contactForAdd, groupForContact);
        app.goTo().homePage();
        idContactForAdd = contactForAdd.getId();
        ContactData contactForAdd2 = (ContactData) app.db().contacts().stream().filter(a -> Objects.equals(idContactForAdd, a.getId())).findFirst().orElse(null);
        assertThat(contactForAdd2.getGroups().contains(groupForContact), equalTo(true));


    }

}
