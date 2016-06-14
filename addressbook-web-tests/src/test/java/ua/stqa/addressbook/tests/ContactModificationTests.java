package ua.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(
            new ContactData("FirstName_Modified", "MiddleName", "LastName", "NickName", "Title", "Company", "Address", "Home",
                    "Mobile", "Work", "Fax", "19", "March", "1962", "5", "January", "1976", "Address2", "Phone2", "Notes"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();


  }

}
