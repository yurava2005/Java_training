package ua.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.model.GroupData;
import ua.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions () {
    app.goTo().groupPage();
    if (app.group().all().size() == 0){
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification() {

    // сформировать списко ДО из имен групп
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("test1").withHeader("test666").withFooter("test4");
    app.group().modify(group);
    // сформировать список групп ПОСЛЕ
    Groups after = app.group().all();
    // сравнить размеры
    assertEquals(after.size(), before.size());
    // удалить старую (до модификации) из списка before
    // добавить ту, что модифицировали
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }


}
