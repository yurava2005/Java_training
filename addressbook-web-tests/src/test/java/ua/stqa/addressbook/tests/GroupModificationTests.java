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
    if(app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification() {

    // сформировать списко ДО из имен групп
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("test1").withHeader("test666").withFooter("test4");
    app.goTo().groupPage();
    app.group().modify(group);
    // сначала сравнить размер (менее тяжеловесная проверка макс быстрым способом)
    assertThat(app.group().count(), equalTo(before.size()));
    // только потом формировать списки для тяжедловесніх проверок
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    verifyGroupListInUI();
  }


}
