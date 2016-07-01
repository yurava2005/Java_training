package ua.stqa.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    //int before = app.getGroupHelper().getGroupCount();
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    // int before = app.getGroupHelper().getGroupCount();
    GroupData group = new GroupData("test1", null, "test3");
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size() + 1);
    //Assert.assertEquals(after, before+1);


    // находим максимальный идентификатор. с ним созлдана новая группа
    int max = 0;
    for (GroupData g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    group.setId(max);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }

}
