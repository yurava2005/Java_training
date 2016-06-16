package ua.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase{

  @Test
  public void ContactCreationTest() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(
            new ContactData("FirstName", "MiddleName", "LastName", "NickName", "Title", "Company", "Address", "Home",
                     "Mobile", "Work", "Fax", "18", "March", "1962", "5", "January", "1976", "Address2", "Phone2", "Notes", "test1"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();

  }




}
