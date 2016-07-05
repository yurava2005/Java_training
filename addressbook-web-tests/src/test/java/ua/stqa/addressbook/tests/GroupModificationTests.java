package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase{

  @Test
  public void testGroupModification() {


    app.getNavigationHelper().gotoGroupPage();
    // сформировать списко ДО из имен групп
    List<GroupData> before = app.getGroupHelper().getGroupList();

    int groupToModify = before.size()-1; // собственно группа для модификации
    //region Модификация группы
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getGroupHelper().selectGroup(groupToModify);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(groupToModify).getId(), "tes666", "test2", "test4");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    //endregion Модификация группы

    // сформировать список групп ПОСЛЕ
    List<GroupData> after = app.getGroupHelper().getGroupList();
    // сравнить размеры
    Assert.assertEquals(after.size(), before.size());

    // удалить старую (до модификации) из списка before
    before.remove(groupToModify);
    // добавить ту, что модифицировали
    before.add(group);

    //отсортировать 2 списка по уникальному полю Id
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    // сравнить
    Assert.assertEquals(before, after);
  }
}
