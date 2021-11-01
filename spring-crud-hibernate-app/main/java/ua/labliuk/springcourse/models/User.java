package ua.labliuk.springcourse.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "somedb")
public class User {

    @Id
    @Column(name = "account_id")
    private int account_id;

    @Column(name = "name")
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "surname")
    @NotEmpty (message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String surname;

    @Column(name = "age")
    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    @Column(name = "username")
    @NotEmpty (message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Username should be between 2 and 30 characters")
    private String username;

    @Column(name = "password")
    @NotEmpty (message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Password should be between 2 and 30 characters")
    private String password;

    public User(){
    }

    public User(int account_id,String name, String surname, int age, String username, String password) {
        this.account_id = account_id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
