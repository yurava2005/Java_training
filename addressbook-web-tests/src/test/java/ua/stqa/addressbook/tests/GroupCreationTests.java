package ua.stqa.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.model.GroupData;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    //int before = app.getGroupHelper().getGroupCount();
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createGroup(new GroupData("test1", null, "test3"));
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before+1);
  }

}
