package ua.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("FirstName", "MiddleName", "LastName", "NickName", "Title", "Company", "Address", "Home",
              "Mobile", "Work", "Fax", "19", "March", "1962", "5", "January", "1976", "Address2", "Phone2", "Notes", "test1"));
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(
            new ContactData("FirstName_Modified", "MiddleName", "LastName", "NickName", "Title", "Company", "Address", "Home",
                    "Mobile", "Work", "Fax", "19", "March", "1962", "5", "January", "1976", "Address2", "Phone2", "Notes", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();


  }

}
