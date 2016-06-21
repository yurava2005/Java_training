package ua.stqa.addressbook.tests;


import org.testng.annotations.Test;
import ua.stqa.addressbook.model.GroupData;


public class GroupDeletionTests extends TestBase {


  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, "test3"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();

  }


}
