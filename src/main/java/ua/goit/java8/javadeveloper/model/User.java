package ua.goit.java8.javadeveloper.model;

/**
 * Created by t.oleksiv on 09/11/2017.
 */
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id",columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER) // зв'язок юзери <--> ролі
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String firstName, String lastName, String password, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User withId(UUID id){
        this.id = id;
        return this;
    }

    public User withUsername(String username){
        this.username = username;
        return this;
    }

    public User withLastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public User withFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public User withPassword(String password){
        this.password = password;
        return this;
    }

    public User withEmail(String email){
        this.email = email;
        return this;
    }

    public User withRoles(Set<Role> roles){
        this.roles = roles;
        return this;
    }

    @Override
    public String toString(){
        return "User{" +
                "id=" + id.toString() +
                ", username='" + username + '\'' +
                ", firstname= '" + firstName + '\'' +
                ", lastname= '" + lastName + '\'' +
                ", email= '" + email + '\'' +
                "}";
    }


    private String showRoles(){
        String result = "";
        for (Role role: roles){
            result += role + ",\n    ";
        }
        return result;
    }

    public String showUserRoles(){
        return "User{" +
                username + " " +
                firstName + " " +
                lastName + "; \n    " +
                "{Roles: \n    " +
                showRoles() +
                "}}";
    }

    public String printPureUser(){
        return "User{" +
                "id=" + id.toString() +
                ", username='" + username + '\'' +
                ", firstname= '" + firstName + '\'' +
                ", lastname= '" + lastName + '\'' +
                ", email= '" + email + '\'' +
                "}";
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;

        User obj2 = (User) obj;
        if((this.id == obj2.getId()) && (this.username.equals(obj2.getUsername()))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int tmp = 0;
        tmp = ( id + firstName + lastName).hashCode();
        return tmp;
    }

}