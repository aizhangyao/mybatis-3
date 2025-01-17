package com.aiz.pojo;

import java.io.Serializable;

/**
 * @author ZhangYao
 * @version 1.0
 * @className User
 * @description User
 * @date Create in 23:45 2023/3/27
 */
public class User implements Serializable {
  private Integer id;

  private String username;

  private String password;

  private Integer age;

  private String sex;

  private String email;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User() {
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", username='" + username + '\'' +
      ", password='" + password + '\'' +
      ", age=" + age +
      ", sex='" + sex + '\'' +
      ", email='" + email + '\'' +
      '}';
  }
}
