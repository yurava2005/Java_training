package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.Set;

public class ContactModificationTests extends TestBase {

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
  public void testContactModification() {

    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("FirstName_Modified").withMiddlename("MiddleName").withLastname("LastName").withNickname("NickName")
                    .withTitle("Title").withCompany("Company").withAddress("Address").withHome("Home")
                    .withMobile("Mobile").withWork("Work").withFax("Fax").withBday("18").withBmohth("March").withByear("1962")
                    .withAday("5").withAmonth("January").withAyear("1976").withAddress2("Address2").withPhone2("Phone2")
                    .withNotes("Notes");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }


}
