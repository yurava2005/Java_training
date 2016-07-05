package ua.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ua.stqa.addressbook.model.ContactData;

import java.util.ArrayList;
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
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("work"), contactData.getWork());
    type(By.name("fax"), contactData.getFax());
    //--------------------
    // birthday
    //--------------------
    selectFromDropDownList("bday", contactData.getBday());
    selectFromDropDownList("bmonth", contactData.getBmohth());
    type(By.name("byear"), contactData.getByear());

    //--------------------
    // anniversary
    //--------------------
    selectFromDropDownList("aday", contactData.getAday());
    selectFromDropDownList("amonth", contactData.getAmonth());
    type(By.name("ayear"), contactData.getAyear());

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
      selectFromDropDownList("new_group", contactData.getGroup());
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

  public void initContactModification( int index) {
    // к индексу нужно прибавить двойку, чтобы попасть в нужную строку таблицы
    click(By.xpath("//table[@id='maintable']/tbody/tr[" + (index + 2) + "]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
   // click(By.name("selected[]"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    if (isAlertPresent()) wd.switchTo().alert().accept();

  }

  public void createContact(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    int i = 2;
    for (WebElement element : elements) {

      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastName = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + i + "]/td[2]")).getText();
      String firstName = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + i + "]/td[3]")).getText();
      i += 1;
      ContactData contact = new ContactData(id, firstName, null, lastName, null, null, null, null, null,
              null, null, null, null, null, null, null, null, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }


}
