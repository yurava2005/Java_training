package ua.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.model.ContactData;
import ua.stqa.addressbook.model.Contacts;
import ua.stqa.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("created by @BeforeMethod (modification)"));
      app.goTo().homePage();
    }
    if (app.db().contacts().size() == 0) {
      GroupData group = app.db().groups().iterator().next();
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
    String amonthDB = contact.getAmonth().toLowerCase();
    Contacts tmp = before.without(modifiedContact).withAdded(contact);
    //print(after,tmp);

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

//  private void print(Contacts after, Contacts tmp) {
//    ContactData c1 = after.iterator().next();
//    ContactData c2 = after.iterator().next();
//
//    System.out.println(c1.getId()+ "////" + c2.getId());
//    System.out.println(c1.getFirstname()+ "////" + c2.getFirstname());
//    System.out.println(c1.getMiddlename()+ "////" + c2.getMiddlename());
//    System.out.println(c1.getId()+ "////" + c2.getId());
//    System.out.println(c1.getId()+ "////" + c2.getId());
//    System.out.println(c1.getId()+ "////" + c2.getId());
//    System.out.println(c1.getId()+ "////" + c2.getId());
//    System.out.println(c1.getId()+ "////" + c2.getId());
//    System.out.println(c1.getId()+ "////" + c2.getId());
//    System.out.println(c1.getId()+ "////" + c2.getId());

 /*   if (id != that.id) return false;
    if (bday != that.bday) return false;
    if (aday != that.aday) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
    if (title != null ? !title.equals(that.title) : that.title != null) return false;
    if (company != null ? !company.equals(that.company) : that.company != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (home != null ? !home.equals(that.home) : that.home != null) return false;
    if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
    if (work != null ? !work.equals(that.work) : that.work != null) return false;
    if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
    if (bmohth != null ? !bmohth.equals(that.bmohth) : that.bmohth != null) return false;
    if (byear != null ? !byear.equals(that.byear) : that.byear != null) return false;
    if (amonth != null ? !amonth.equals(that.amonth) : that.amonth != null) return false;
    if (ayear != null ? !ayear.equals(that.ayear) : that.ayear != null) return false;
    if (address2 != null ? !address2.equals(that.address2) : that.address2 != null) return false;
    if (phone2 != null ? !phone2.equals(that.phone2) : that.phone2 != null) return false;
    if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;

    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
    return email3 != null ? email3.equals(that.email3) : that.email3 == null;*/


}
