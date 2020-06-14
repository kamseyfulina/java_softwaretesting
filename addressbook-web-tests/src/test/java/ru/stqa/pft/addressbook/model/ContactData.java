package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private  String firstname;
  private  String middlename;
  private  String lastname;
  private  String nickname;
  private  String company;
  private  String home;
  private  String mobile;
  private  String email;
  private  String bday;
  private  String bmonth;
  private  String byear;
  private  String group;

  public ContactData(String firstname, String middlename, String lastname, String nickname, String company, String home, String mobile, String email, String bday, String bmonth, String byear, String group) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.company = company;
    this.home = home;
    this.mobile = mobile;
    this.email = email;
    this.bday = bday;
    this.bmonth = bmonth;
    this.byear = byear;
    this.group = group;
  }
  public ContactData(String firstname, String lastname){
    this.firstname = firstname;
    this.middlename = null;
    this.lastname = lastname;
    this.nickname = null;
    this.company = null;
    this.home = null;
    this.mobile = null;
    this.email = null;
    this.group = null;

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

  public String getEmail() {
    return email;
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

  public String getGroup () {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            ", middlename='" + middlename + '\'' +
            ", lastname='" + lastname + '\'' +
            ", nickname='" + nickname + '\'' +
            ", company='" + company + '\'' +
            ", home='" + home + '\'' +
            ", mobile='" + mobile + '\'' +
            ", email='" + email + '\'' +
            ", bday='" + bday + '\'' +
            ", bmonth='" + bmonth + '\'' +
            ", byear='" + byear + '\'' +
            ", group='" + group + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstname, that.firstname) &&
           Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname);
  }


}
