package ua.goit.java8.javadeveloper.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Taras on 11.11.2017.
 */

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id",columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER) // зв'язок ролі <--> юзери
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    public Role(){

    }

    public Role(String name){
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Role withId(UUID id){
        this.id = id;
        return this;
    }

    public Role withName(String name){
        this.name = name;
        return this;
    }


    @Override
    public String toString(){
        return "Role{" +
                "id=" + id.toString() +
                ", name='" + name + '\'' +
                "}";
    }

    private String showUsers(){
        String result = "";
        for (User user: users){
            result += user.printPureUser() + ",\n    ";
        }
        return result;
    }

    public String showRoleUsers(){
        return "Role{" +
                "id=" +id.toString() + " " +
                "name=" + name + ";\n    " +
                "{Users:\n    " +
                showUsers() +
                "}}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;

        Product obj2 = (Product) obj;
        if((this.id == obj2.getId()) && (this.name.equals(obj2.getName()))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int tmp = 0;
        tmp = ( id + name).hashCode();
        return tmp;
    }
}
