package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.List;


public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData("Иван", "Иванович", "Иванов", "ivanov", "ST", "88435235412", "89503336699", "ivanov@testmail.ru", "26", "January", "1992","test"));
    }
  }

  @Test
  public void testContactDeletion(){
    List<ContactData> before = app.contact().list();
    int index =before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(),before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before,after);

  }


}
