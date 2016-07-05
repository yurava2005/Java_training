package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test (enabled = false)
  public void testContactDeletion(){

    app.goTo().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("FirstName", "MiddleName", "LastName", "NickName", "Title", "Company", "Address", "Home",
              "Mobile", "Work", "Fax", "18", "March", "1962", "5", "January", "1976", "Address2", "Phone2", "Notes", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = 0;
    app.getContactHelper().selectContact(index);
    app.getContactHelper().deleteSelectedContact();

    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(before.size()-1, after.size());
    before.remove(index);
    Assert.assertEquals(before, after);

  }
}
