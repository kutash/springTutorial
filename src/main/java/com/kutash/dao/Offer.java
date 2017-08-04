package com.kutash.dao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="offers")
public class Offer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @NotNull(groups = {PersistenceValidationGroup.class, FormValidationGroup.class})
    @Size(min = 5, max = 100,groups = {PersistenceValidationGroup.class, FormValidationGroup.class})
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min = 10, max = 200,groups = {PersistenceValidationGroup.class, FormValidationGroup.class})
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public Offer(){
        this.user = new User();
    }

    public Offer(String name, User user, String text) {
        this.name = name;
        this.user = user;
        this.text = text;

    }

    public Offer(int id, String name, User user, String text) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername(){
        return user.getUsername();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer offer = (Offer) o;

        if (name != null ? !name.equals(offer.name) : offer.name != null) return false;
        if (text != null ? !text.equals(offer.text) : offer.text != null) return false;
        return user != null ? user.equals(offer.user) : offer.user == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
