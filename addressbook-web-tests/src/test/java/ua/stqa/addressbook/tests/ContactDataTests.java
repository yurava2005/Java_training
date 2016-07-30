package ua.stqa.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.stqa.addressbook.model.ContactData;
import ua.stqa.addressbook.model.GroupData;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

// Проверки на корректность отображения даннніх контактов на главной странице
public class ContactDataTests extends TestBase {



  private Map<String, ContactData> contactDataCache = new HashMap<String, ContactData>();
  private final String fromMainPage = "fromMainPage";
  private final String fromEditForm = "fromEditForm";
  private final String fromViewForm = "fromViewForm";

  public Map<String, ContactData> getContactTestData(int mode) {
    // вспомагательный метод - форирует кеш-массив - типа MAP - с ключами доступа
    // [fromMainPage] - представление данных на главной странице
    // [fromEditForm] - данные, полученные из формы редактирования
    // [fromViewForm] - данные со страницы просмотра
    // mode = 1 - сравнение с главной формой
    // mode = 2 - сравнение с формой просмотра
    if (! contactDataCache.isEmpty()) {
      Map<String, ContactData> copy = new HashMap<String, ContactData>();
      copy.putAll(contactDataCache);
      return copy;
    }
    ContactData contact = app.contact().all().iterator().next();
    contactDataCache.put(fromEditForm, app.contact().infoFromEditForm(contact));
    if (mode == 1) {
      contactDataCache.put(fromMainPage, contact);
    }
    if (mode == 2) {
      contactDataCache.put(fromViewForm, app.contact().infoFromViewForm(contact));
    }

    Map<String, ContactData> copy = new HashMap<>();
    copy.putAll(contactDataCache);
    return copy;
  }

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      GroupData group = app.db().groups().iterator().next();
      app.contact().create(new ContactData().withFirstname("FirstName").withLastname("LastName")
              .withEmail("111@mail.ru").withEmail2("222@mail.ru").withEmail3("333@mail.ru   ")
              .withAddress("Address").withHome("Home").withFax("Fax")
              .withMobile("Mobile").withWork("Work").withPhone2("Phone2"));
    }
  }

  @Test (enabled = false, priority = 2)
  public void testPhoneContact() {
    app.goTo().homePage();
/*    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);*/
    ContactData contact = getContactTestData(1).get(fromMainPage);
    ContactData contactInfoFromEditForm = getContactTestData(1).get(fromEditForm);
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  @Test (enabled = false, priority = 1)
  public void testAddressContact() {
    app.goTo().homePage();
    ContactData contact = getContactTestData(1).get(fromMainPage);
    ContactData contactInfoFromEditForm = getContactTestData(1).get(fromEditForm);
    assertThat(contact.getAddress(), equalTo(cleanedAddress(contactInfoFromEditForm.getAddress())));
  }

  @Test (enabled = true)
  public void testEmailContact(){
    app.goTo().homePage();
    ContactData contact = getContactTestData(1).get(fromMainPage);
    ContactData contactInfoFromEditForm = getContactTestData(1).get(fromEditForm);
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  @ Test
  public void testViewContact (){
    app.goTo().homePage();
    ContactData contactData = getContactTestData(2).get(fromMainPage);

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
