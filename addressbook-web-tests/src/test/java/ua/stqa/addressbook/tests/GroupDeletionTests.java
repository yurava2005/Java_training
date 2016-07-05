package ua.stqa.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.model.GroupData;

import java.util.List;


public class GroupDeletionTests extends TestBase {


  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    // сформировать списко ДО из имен групп
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int groupToDelete = before.size() - 1;
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, "test3"));
    }
    app.getGroupHelper().selectGroup(groupToDelete);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();

    // сформировать список ПОСЛЕ из имен групп
    List<GroupData> after = app.getGroupHelper().getGroupList();

    // удалить из списка ДО тот же элемент, что удалили тестом
    before.remove(groupToDelete);
    // сравнить новый список ДО и списокы ПОСЛЕ
    Assert.assertEquals(before, after);


  }


}
