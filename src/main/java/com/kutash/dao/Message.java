package com.kutash.dao;


import com.kutash.validation.ValidEmail;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="messages")
public class Message implements Serializable {

    private static final long serialVersionUID = 8063111826064761657L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @NotBlank
    @Size(min=5, max=15)
    @Pattern(regexp="^\\w{8,}$")
    private String sender;

    @NotBlank
    @Size(min = 10, max = 1000)
    private String text;

    @NotBlank
    @Size(min=5, max=15)
    private String subject;

    @NotBlank
    @ValidEmail
    private String email;

    private String username;

    public Message(String sender, String text, String subject, String email, String username) {
        this.sender = sender;
        this.text = text;
        this.subject = subject;
        this.email = email;
        this.username = username;
    }

    public Message(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (sender != null ? !sender.equals(message.sender) : message.sender != null) return false;
        if (text != null ? !text.equals(message.text) : message.text != null) return false;
        if (subject != null ? !subject.equals(message.subject) : message.subject != null) return false;
        if (email != null ? !email.equals(message.email) : message.email != null) return false;
        return username != null ? username.equals(message.username) : message.username == null;
    }

    @Override
    public int hashCode() {
        int result = sender != null ? sender.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
