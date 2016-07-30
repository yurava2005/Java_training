package ua.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ua.stqa.addressbook.model.ContactData;
import ua.stqa.addressbook.model.Contacts;
import ua.stqa.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by юля on 03.06.2016.
 */
public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    attach(By.name("photo"), contactData.getPhoto());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("work"), contactData.getWork());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());

    //--------------------
    // birthday
    //--------------------
    if (contactData.getBday() != null) {
      selectFromDropDownList("bday", contactData.getBday());
      selectFromDropDownList("bmonth", contactData.getBmohth());
      type(By.name("byear"), contactData.getByear());
    }

    if (contactData.getAday() != null) {

      //--------------------
      // anniversary
      //--------------------
      selectFromDropDownList("aday", contactData.getAday());
      selectFromDropDownList("amonth", contactData.getAmonth());
      type(By.name("ayear"), contactData.getAyear());
    }
    //--------------------
    // Экспериментальный альтернативный поиск по xpath
    //--------------------
    type(By.xpath("//*[@name = 'address2']"), contactData.getAddress2());

    //--------------------
    //Поиск по имени от рекордера
    //--------------------
    //type(By.name("address2"),contactData.getAddress2());

    type(By.name("phone2"), contactData.getPhone2());
    type(By.name("notes"), contactData.getNotes());
    if (creation) {
      if (contactData.getGroups().size() > 0 ) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        selectFromDropDownList("new_group", contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void selectFromDropDownList(String selectLocator, String optionText) {
    //new Select(wd.findElement(By.xpath(selectLocator)).selectByVisibleText(optionText));
    new Select(wd.findElement(By.name(selectLocator))).selectByVisibleText(optionText);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void initContactModification(int index) {
    // к индексу нужно прибавить двойку, чтобы попасть в нужную строку таблицы
    click(By.xpath("//table[@id='maintable']/tbody/tr[" + (index + 2) + "]/td[8]/a/img"));
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withHome(home)
            .withWork(work).withMobile(mobile).withPhone2(phone2).withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  public ContactData infoFromViewForm(ContactData contact) {
    // initContactViewByID(contact.getId());

    return null;
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector("a[href = 'edit.php?id=" + id + "']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContactByID(int id) {
//    wd.findElements(By.cssSelector("selected[]")).get(index).click();
    wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    if (isAlertPresent()) wd.switchTo().alert().accept();

  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactByID(contact.getId());
    deleteSelectedContact();
    contactCache = null;
  }
/*  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }*/

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    //список контактов с главной формы
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    int i = 2;
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastName = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + i + "]/td[2]")).getText();
      String firstName = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + i + "]/td[3]")).getText();
      String allPhones = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + i + "]/td[6]")).getText();
      String address = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + i + "]/td[4]")).getText();
      String allEmails = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + i + "]/td[5]")).getText();
      //String[] phones = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + i + "]/td[6]")).getText().split("\n");
      i += 1;
      ContactData contact = new ContactData().withId(id).withFirstname(firstName).withLastname(lastName)
              .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }


  public void addContactToGroup(ContactData contact, GroupData group) {
    selectFromDropDownList("group", "[all]");
    selectContactByID(contact.getId());
    selectFromDropDownList("to_group", group.getName());
    click(By.name("add"));
    click(By.xpath("//div[@class='msgbox']/i/a"));
  }

  public void deleteContactFromGroup(ContactData contact, GroupData group) {
    selectFromDropDownList("group", group.getName());
    selectContactByID(contact.getId());
    click(By.name("remove"));
    click(By.xpath("//div[@class='msgbox']/i/a"));
  }
}
