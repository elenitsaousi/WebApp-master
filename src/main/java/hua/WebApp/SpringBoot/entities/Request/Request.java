package hua.WebApp.SpringBoot.entities.Request;


import javax.persistence.*;

@Entity
@Table(name ="Requests")

public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String text;
    private Long  mark;
    private String Dest;
    private String status = "Pending";
    private String Uid;
    private String mail;

    public Request() {
    }

    public Request(Long id, String name, String email, Long mark, String dest, String status, String text, String mail) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mail = mail;
        this.text = text;
        this.mark = mark;
        this.Dest = dest;
        this.status = status;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getMark() {
        return mark;
    }

    public void setMark(Long mark) {
        this.mark = mark;
    }

    public String getDest() {
        return Dest;
    }

    public void setDest(String dest) {
        Dest = dest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String agreed) {
        this.status = agreed;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}