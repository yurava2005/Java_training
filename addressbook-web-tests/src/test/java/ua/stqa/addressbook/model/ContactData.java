package ua.stqa.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "addressbook")

public class ContactData {

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String firstname;

  @Expose
  @Column(name = "middlename")
  private String middlename = "";

  @Expose
  @Column(name = "lastname")
  private String lastname = "";

  @Expose
  @Column(name = "nickname")
  private String nickname = "";

  @Expose
  @Column(name = "title")
  private String title = "";

  @Expose
  @Column(name = "company")
  private String company = "";

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address = "";

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String home = "";

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobile = "";

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String work = "";

  @Expose
  @Column(name = "fax")
  @Type(type = "text")
  private String fax = "";

  @Expose
  @Column(name = "bday")
  @Type(type = "byte")
  private byte bday = 0;

  @Expose
  @Column(name = "bmonth")
  private String bmohth = "-";

  @Expose
  @Column(name = "byear")
  private String byear = "";

  @Expose
  @Column(name = "aday")
  @Type(type = "byte")
  private byte aday = 0;

  @Expose
  @Column(name = "amonth")
  private String amonth = "-";

  @Expose
  @Column(name = "ayear")
  private String ayear = "";

  @Expose
  @Column(name = "address2")
  @Type(type = "text")
  private String address2 = "";

  @Expose
  @Column(name = "phone2")
  @Type(type = "text")
  private String phone2 = "";

  @Expose
  @Column(name = "notes")
  @Type(type = "text")
  private String notes = "";

  @Expose
  @Transient
  private String group;

  @Expose
  @Transient
  private String allPhones;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;

  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2 = "";

  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3 = "";

  @Expose
  @Transient
  private String allEmails = "";

  @Expose
  @Column(name = "photo")
  @Type(type = "text")
  private String photo; // использую тип String вместо File, для генерации пути для фото в тектовом формате

  @Transient
  private boolean actual;

  public boolean isActual() {
    return actual;
  }

  public ContactData withActual(boolean actual) {
    this.actual = actual;
    return this;
  }


  public int getId() {
    return id;
  }

  public String getFirstname() {
      return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }


  public String getLastname() {
      return lastname;
  }

  public String getNickname() {
      return nickname;
    }

  public String getTitle() {
      return title;
  }

  public String getCompany() {
      return company;
  }

  public String getAddress() {
      return address;
  }

  public String getHome() {
      return home;
  }

  public String getMobile() {
      return mobile;
    }

  public String getWork() {
      return work;
  }

  public String getFax() {
      return fax;
  }

  public String getBday() {
    if (bday == 0) {
      return null;
    } else {
      return Byte.toString(bday);
    }
  }

  public String getBmohth() {
    return bmohth;
  }

  public String getByear() {
      return byear;
  }

  public String getAday() {
    if (aday == 0) {
      return null;
    } else {
      return Byte.toString(aday);
    }
  }

  public String getAmonth() {
      return amonth;
  }

  public String getAyear() {
      return ayear;
  }

  public String getAddress2() {
      return address2;
  }

  public String getPhone2() {
      return phone2;
  }

  public String getNotes() {
      return notes;
  }

  public String getGroup() {
    return group;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
      return email2;
  }

  public String getEmail3() {
      return email3;
    }

  public String getAllEmails() {
    return allEmails;
  }

  public File getPhoto() {
    if (photo == null) {
      return null;
    } else {
      return new File(photo);
    }
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHome(String home) {
    this.home = home;
    return this;
  }

  public ContactData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactData withWork(String work) {
    this.work = work;
    return this;
  }

  public ContactData withFax(String fax) {
    this.fax = fax;
    return this;
  }

  public ContactData withBday(String bday) {
    if (bday == null) {
      this.bday = 0;
    } else {
      this.bday = Byte.parseByte(bday);
    }
    return this;
  }

  public ContactData withBmohth(String bmohth) {
    this.bmohth = bmohth;
    return this;
  }

  public ContactData withByear(String byear) {
    this.byear = byear;
    return this;
  }

  public ContactData withAday(String aday) {
    if (aday == null) {
      this.aday = 0;
    } else {
      this.aday = Byte.parseByte(aday);
    }
    return this;
  }

  public ContactData withAmonth(String amonth) {
    this.amonth = amonth;
    return this;
  }

  public ContactData withAyear(String ayear) {
    this.ayear = ayear;
    return this;
  }

  public ContactData withAddress2(String address2) {
    this.address2 = address2;
    return this;
  }

  public ContactData withPhone2(String phone2) {
    this.phone2 = phone2;
    return this;
  }

  public ContactData withNotes(String notes) {
    this.notes = notes;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withPhoto(String photo) {
    this.photo = photo;
    return this;
  }
//  public ContactData withPhoto(String photo) {
//
//    this.photo = new File(file_photo);
//    return this;
//  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (bday != that.bday) return false;
    if (aday != that.aday) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
    if (title != null ? !title.equals(that.title) : that.title != null) return false;
    if (company != null ? !company.equals(that.company) : that.company != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (home != null ? !home.equals(that.home) : that.home != null) return false;
    if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
    if (work != null ? !work.equals(that.work) : that.work != null) return false;
    if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
    if (bmohth != null ? !bmohth.equals(that.bmohth) : that.bmohth != null) return false;
    if (byear != null ? !byear.equals(that.byear) : that.byear != null) return false;
    if (amonth != null ? !amonth.equals(that.amonth) : that.amonth != null) return false;
    if (ayear != null ? !ayear.equals(that.ayear) : that.ayear != null) return false;
    if (address2 != null ? !address2.equals(that.address2) : that.address2 != null) return false;
    if (phone2 != null ? !phone2.equals(that.phone2) : that.phone2 != null) return false;
    if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
    if (group != null ? !group.equals(that.group) : that.group != null) return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
    return email3 != null ? email3.equals(that.email3) : that.email3 == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (company != null ? company.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (home != null ? home.hashCode() : 0);
    result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
    result = 31 * result + (work != null ? work.hashCode() : 0);
    result = 31 * result + (fax != null ? fax.hashCode() : 0);
    result = 31 * result + (int) bday;
    result = 31 * result + (bmohth != null ? bmohth.hashCode() : 0);
    result = 31 * result + (byear != null ? byear.hashCode() : 0);
    result = 31 * result + (int) aday;
    result = 31 * result + (amonth != null ? amonth.hashCode() : 0);
    result = 31 * result + (ayear != null ? ayear.hashCode() : 0);
    result = 31 * result + (address2 != null ? address2.hashCode() : 0);
    result = 31 * result + (phone2 != null ? phone2.hashCode() : 0);
    result = 31 * result + (notes != null ? notes.hashCode() : 0);
    result = 31 * result + (group != null ? group.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (email2 != null ? email2.hashCode() : 0);
    result = 31 * result + (email3 != null ? email3.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", middlename='" + middlename + '\'' +
            ", lastname='" + lastname + '\'' +
            ", nickname='" + nickname + '\'' +
            ", photo='" + photo + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

}
