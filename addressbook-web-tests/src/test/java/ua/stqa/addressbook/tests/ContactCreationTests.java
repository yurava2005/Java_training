package ua.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase{

  @Test
  public void ContactCreationTest() {

    app.initContactCreation();
    app.fillContactForm(
            new ContactData("FirstName", "MiddleName", "LastName", "NickName", "Title", "Company", "Address", "Home",
                     "Mobile", "Work", "Fax", "18", "March", "1962", "5", "January", "1976", "Address2", "Phone2", "Notes"));
    app.submitContactCreation();
    app.returnToHomePage();

  }




}
