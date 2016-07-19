package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.model.ContactData;
import ua.stqa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void  ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("FirstName").withMiddlename("MiddleName").withLastname("LastName").withNickname("NickName")
              .withTitle("Title").withCompany("Company").withAddress("Address").withHome("Home")
              .withMobile("Mobile").withWork("Work").withFax("Fax").withBday("18").withBmohth("March").withByear("1962")
              .withAday("5").withAmonth("January").withAyear("1976").withAddress2("Address2").withPhone2("Phone2")
              .withNotes("Notes").withGroup("test1"));
    }
  }

  @Test (enabled = true)
  public void testContactDeletion(){
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next(); // так работают множества - через итератор
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()-1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));

  }


}
