package ua.stqa.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.model.ContactData;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

// Проверки на корректность отображения даннніх контактов на главной странице
public class ContactDataTests extends TestBase {



  private Map<String, ContactData> contactDataCache = new HashMap<String, ContactData>();
  private final String fromMainPage = "fromMainPage";
  private final String fromEditForm = "fromEditForm";

  public Map<String, ContactData> getContactTestData() {
    // вспомагательный метод - форирует кеш-массив - типа MAP - с ключами доступа
    // [fromMainPage] - представление данных на главной странице
    // [fromEditForm] - данные, полученные из формы редактирования
    if (! contactDataCache.isEmpty()) {
      Map<String, ContactData> copy = new HashMap<String, ContactData>();
      copy.putAll(contactDataCache);
      return copy;
    }
    ContactData contact = app.contact().all().iterator().next();
    contactDataCache.put(fromMainPage, contact);
    contactDataCache.put(fromEditForm, app.contact().infoFromEditForm(contact));
    Map<String, ContactData> copy = new HashMap<>();
    copy.putAll(contactDataCache);
    return copy;
  }

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("FirstName").withMiddlename("MiddleName").withLastname("LastName").withNickname("NickName")
              .withTitle("Title").withCompany("Company").withAddress("Address").withHome("Home")
              .withMobile("Mobile").withWork("Work").withFax("Fax").withBday("18").withBmohth("March").withByear("1962")
              .withAday("5").withAmonth("January").withAyear("1976").withAddress2("Address2").withPhone2("Phone2")
              .withNotes("Notes").withGroup("test1"));
    }
  }

  @Test (enabled = false, priority = 2)
  public void testPhoneContact() {
    app.goTo().homePage();
/*    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);*/
    ContactData contact = getContactTestData().get(fromMainPage);
    ContactData contactInfoFromEditForm = getContactTestData().get(fromEditForm);
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  @Test (enabled = false, priority = 1)
  public void testAddressContact() {
    app.goTo().homePage();
    ContactData contact = getContactTestData().get(fromMainPage);
    ContactData contactInfoFromEditForm = getContactTestData().get(fromEditForm);
    assertThat(contact.getAddress(), equalTo(cleanedAddress(contactInfoFromEditForm.getAddress())));
  }

  @Test (enabled = true)
  public void testEmailContact(){
    app.goTo().homePage();
    ContactData contact = getContactTestData().get(fromMainPage);
    ContactData contactInfoFromEditForm = getContactTestData().get(fromEditForm);
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }


  @AfterMethod
  public void clearCache(){
    contactDataCache.clear();
  }


  private String mergePhones(ContactData contact) {
    // сформировать массив, превратить в поток, отфильтровать по непустым значениям, применить функцию очистки, склеить
    return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork(), contact.getPhone2())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactDataTests::cleanedPhone)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    // сформировать массив, превратить в поток, отфильтровать по непустым значениям, применить функцию очистки, склеить
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactDataTests::cleanedEmail)
            .collect(Collectors.joining("\n"));
  }

  public static String cleanedEmail(String email) {

    return email.replaceAll("\\s+$", "");
  }
  public static String cleanedPhone(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }


  public static String cleanedAddress(String address) {
    // Разделим на строки
    String[] str = address.split("\n");
    // Удалим лишнее
    int i=0;
    for (String s: str){
      str[i] = s.replaceAll("\\s+", " ").replaceAll("\\s+$", "");
      i++;
    }
    // Склеим обратно через новую строку
    return Arrays.stream(str).collect(Collectors.joining("\n"));
  }
}
