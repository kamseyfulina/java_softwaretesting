package ru.stqa.pft.addressbook.model;
import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name="addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name="id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name="firstname")
  private  String firstname;

  @Expose
  @Column(name="middlename")
  private  String middlename;

  @Expose
  @Column(name="lastname")
  private  String lastname;

  @Column(name="nickname")
  private  String nickname = "";

  @Column(name="company")
  private  String company = "";

  @Column(name="home")
  @Type(type ="text")
  private  String home = "";
  @Expose
  @Column(name="mobile")
  @Type(type ="text")
  private  String mobile = "";

  @Column(name="work")
  @Type(type ="text")
  private  String work= "";

  @Transient
  private  String allPhones= "";

  @Transient
  private  String allEmails= "";

  @Expose
  @Column(name="email")
  @Type(type ="text")
  private  String email;
  @Transient
  private  String bday= "";
  @Transient
  private  String bmonth= "";
  @Transient
  private  String byear= "";

  @Transient
  private  String email2= "";
  @Transient
  private  String email3 = "";

  @Expose
  @Column(name="address")
  @Type(type ="text")
  private  String address ;

  @Column(name="photo")
  @Type(type ="text")
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER)//для извлечения большого количество информации
  @JoinTable(name="address_in_groups", joinColumns = @JoinColumn(name="id"),inverseJoinColumns = @JoinColumn(name="group_id") )
  private final Set<GroupData> groups = new HashSet<GroupData>();

  public File getPhoto() {
    if (photo != null) {
      return new File(photo);
    } else {
      return null;
    }
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
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

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return  this;
  }

  public String getAllEmails() {

    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return  this;
  }

  public ContactData() {

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

  public ContactData withCompany(String company) {
    this.company = company;
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


  public ContactData withBday(String bday) {
    this.bday = bday;
    return this;
  }

  public ContactData withBmonth(String bmonth) {
    this.bmonth = bmonth;
    return this;
  }

  public ContactData withByear(String byear) {
    this.byear = byear;
    return this;
  }


  public Groups getGroups() {
    return new Groups(groups);
  }

  public ContactData(String firstname, String lastname){
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.middlename = null;
    this.lastname = lastname;
    this.nickname = null;
    this.company = null;
    this.home = null;
    this.mobile = null;
    this.mobile = null;
    this.email = null;
    this.email2 = null;
    this.email3 = null;
    this.address = null;

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

  public String getCompany() {
    return company;
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

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }
  public String getEmail3() {
    return email3;
  }

  public String getAddress() {
    return address;
  }

  public String getBday() {
    return bday;
  }

  public String getBmonth() {
    return bmonth;
  }

  public String getByear() {
    return byear;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", middlename='" + middlename + '\'' +
            ", lastname='" + lastname + '\'' +
            ", nickname='" + nickname + '\'' +
            ", company='" + company + '\'' +
            ", home='" + home + '\'' +
            ", mobile='" + mobile + '\'' +
            ", work='" + work + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", allEmails='" + allEmails + '\'' +
            ", email='" + email + '\'' +
            ", bday='" + bday + '\'' +
            ", bmonth='" + bmonth + '\'' +
            ", byear='" + byear + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", address='" + address + '\'' +
            ", photo='" + photo + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(middlename, that.middlename) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(nickname, that.nickname) &&
            Objects.equals(company, that.company) &&
            Objects.equals(home, that.home) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(work, that.work) &&
            Objects.equals(email, that.email) &&
            Objects.equals(bday, that.bday) &&
            Objects.equals(bmonth, that.bmonth) &&
            Objects.equals(byear, that.byear) &&
            Objects.equals(email2, that.email2) &&
            Objects.equals(email3, that.email3) &&
            Objects.equals(address, that.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, middlename, lastname, nickname, company, home, mobile, work, email, bday, bmonth, byear, email2, email3, address,photo);
  }

}
