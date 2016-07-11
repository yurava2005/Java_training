package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.model.ContactData;

import java.util.Set;


public class ContactCreationTests extends TestBase{

  @Test (enabled = true)
  public void testContactCreation() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("FirstName").withMiddlename("MiddleName").withLastname("LastName").withNickname("NickName")
            .withTitle("Title").withCompany("Company").withAddress("Address").withHome("Home")
            .withMobile("Mobile").withWork("Work").withFax("Fax").withBday("18").withBmohth("March").withByear("1962")
            .withAday("5").withAmonth("January").withAyear("1976").withAddress2("Address2").withPhone2("Phone2")
            .withNotes("Notes").withGroup("test1");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()); // превратили в поток чисел - ID контактов при помощи метода MapToInt
    before.add(contact);
    Assert.assertEquals(before, after);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    //MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.));

  }




}
