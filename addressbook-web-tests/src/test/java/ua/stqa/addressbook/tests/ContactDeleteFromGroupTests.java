package ua.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.model.ContactData;
import ua.stqa.addressbook.model.Contacts;
import ua.stqa.addressbook.model.GroupData;
import ua.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTests extends TestBase {

  private ContactData targetContact;
  private GroupData targetGroup;

  @BeforeMethod
  public void ensurePreconditions() {
    Groups allGroups = app.db().groups();
    // if there are no any group at all
    if (allGroups.size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("created by ContactDeleteFromGroupTests"));
      app.goTo().homePage();
    }
    targetGroup = app.db().groups().iterator().next();
    Contacts allContacts = app.db().contacts();

    if (allContacts.size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("created by ContactDeleteFromGroupTests"));
    }
    //  ContactData newContact = app.db().contacts().iterator().next().inGroup(targetGroup);
    targetContact = app.db().contacts().iterator().next();
    app.contact().addContactToGroup(targetContact, targetGroup);
    app.goTo().homePage();

  }


  @Test
  public void testDeleteContactFromGroup() {
    Groups before = targetContact.getGroups();
    app.contact().deleteContactFromGroup(targetContact, targetGroup);
    ContactData newContact = app.db().getContactByID(targetContact.getId());
    Groups after = newContact.getGroups();
    assertThat(after.size(), equalTo(before.size() - 1));

    GroupData group = app.db().groups().iterator().next();
    Contacts contacts = group.getContacts();
  }
}
