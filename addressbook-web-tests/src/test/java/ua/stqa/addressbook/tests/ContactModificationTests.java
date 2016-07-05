package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test (enabled = false)
  public void testContactModification() {
    app.goTo().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("FirstName", "MiddleName", "LastName", "NickName", "Title", "Company", "Address", "Home",
              "Mobile", "Work", "Fax", "19", "March", "1962", "5", "January", "1976", "Address2", "Phone2", "Notes", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = 0; // с двойки начинается нумерация строк в таблице
    app.getContactHelper().initContactModification(index);
    ContactData contact = new ContactData(before.get(index).getId(), "FirstName_Modified", "MiddleName", "LastName", "NickName", "Title", "Company", "Address", "Home",
            "Mobile", "Work", "Fax", "19", "March", "1962", "5", "January", "1976", "Address2", "Phone2", "Notes", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    before.remove(index);
    before.add(contact);

    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }

}
