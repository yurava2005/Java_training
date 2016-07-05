package ua.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions () {
    app.goTo().groupPage();
    if (app.group().list().size() == 0){
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification() {

    // сформировать списко ДО из имен групп
    List<GroupData> before = app.group().list();
    int index = before.size()-1;
    GroupData group = new GroupData().withId(before.get(index).getId()).
            withName("test1").withHeader("test666").withFooter("test4");
    app.group().modify(index, group);

    // сформировать список групп ПОСЛЕ
    List<GroupData> after = app.group().list();
    // сравнить размеры
    Assert.assertEquals(after.size(), before.size());

    // удалить старую (до модификации) из списка before
    before.remove(index);
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
