package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.model.ContactData;
import ua.stqa.addressbook.model.Contacts;
import ua.stqa.addressbook.model.GroupData;
import ua.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {

  private ContactData targetContact;
  private GroupData targetGroup;

  @BeforeMethod
  public void ensurePreconditions() {

    Contacts allContacts = app.db().contacts();
    //region Contact creation if not exist
    if (allContacts.size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("FirstName").withMiddlename("MiddleName").withLastname("LastName").withNickname("NickName")
              .withTitle("Title").withCompany("Company").withAddress("Address").withHome("Home")
              .withMobile("Mobile").withWork("Work").withFax("Fax").withBday("18").withBmohth("March").withByear("1962")
              .withAday("5").withAmonth("January").withAyear("1976").withAddress2("Address2").withPhone2("Phone2")
              .withNotes("Notes"));
    }
    //endregion Contact creation if not exists
    targetContact = app.db().contacts().iterator().next();

    //region получить список групп целевого контакта
    Groups contactGroups = targetContact.getGroups();
    Groups allGroups = app.db().groups();

    if (allGroups.size() == contactGroups.size()) {
      // если нет групп, в которые можно добавить контакт, создать новую целевую группу
      app.goTo().groupPage();
      targetGroup = new GroupData().withName("created by ContactAddToGroupTests " + allGroups.size());
      app.group().create(targetGroup);
      app.goTo().homePage();
    } else {
      // выбрать целевую группу из тех, в которіе контакт еще не входит путем изъятия из общего списка групп тех, в которые
      // входит конткат
      Assert.assertTrue(allGroups.size() > contactGroups.size());
      Groups setDiff = new Groups(allGroups);
      for (GroupData g : contactGroups) {
        setDiff = new Groups(allGroups.without(g));
      }
      targetGroup = setDiff.iterator().next();
      System.out.println(setDiff);
    }
    //endregion
  }

  @Test
  public void testAddContactToGroup() {
    Groups before = targetContact.getGroups();
    app.contact().addContactToGroup(targetContact, targetGroup);
    ContactData newContact = app.db().getContactByID(targetContact.getId());
    Groups after = newContact.getGroups();
    assertThat(after.size(), equalTo(before.size()+1));
  }
}
