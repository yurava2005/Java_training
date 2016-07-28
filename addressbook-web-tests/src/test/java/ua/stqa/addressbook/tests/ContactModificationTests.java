package ua.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.model.ContactData;
import ua.stqa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("FirstName").withMiddlename("MiddleName").withLastname("LastName").withNickname("NickName")
              .withTitle("Title").withCompany("Company").withAddress("Address").withHome("Home")
              .withMobile("Mobile").withWork("Work").withFax("Fax").withBday("18").withBmohth("March").withByear("1962")
              .withAday("5").withAmonth("January").withAyear("1976").withAddress2("Address2").withPhone2("Phone2")
              .withNotes("Notes"));
    }
  }

  @Test(enabled = true)
  public void testContactModification() {

    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("FirstName_Modified")
            .withMiddlename("MiddleName").withLastname("LastName").withNickname("NickName")
            .withTitle("Title").withCompany("Company").withAddress("Address").withHome("Home")
            .withMobile("Mobile").withWork("Work").withFax("Fax").withBday("18").withBmohth("March").withByear("1962")
            .withAday("5").withAmonth("January").withAyear("1976").withAddress2("Address2").withPhone2("Phone2")
            .withNotes("Notes").withPhoto("src/test/resources/cat.jpg").withEmail("123sti@gmail.com");
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
