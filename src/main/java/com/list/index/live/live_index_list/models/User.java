package com.list.index.live.live_index_list.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.list.index.live.live_index_list.models.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Objects;

@Entity
@Table(name = "users", schema = "project2")
@Data
@Getter
@JsonPropertyOrder({"userId", "firstName", "lastName", "email", "password", "role"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @JsonProperty(value = "userId")
    private int userId;

    @Column(name = "first_name")
    @JsonProperty(value = "firstName")
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty(value = "lastName")
    private String lastName;

    @Column(name = "email")
    @JsonProperty(value = "email")
    private String email;

    @Column(name = "password")
    @JsonProperty(value = "password")
    private String password;

    @Column(name = "role")
    @JsonProperty(value = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, email, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
