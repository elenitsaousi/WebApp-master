package hua.WebApp.SpringBoot.entities.RecommendationLetter;


import javax.persistence.*;

@Entity
@Table(name ="RecommendationLetters")

public class RecommendationLetter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long Rid;
    private String Text;
    private String Uid;
    private String email;
    private String prof_email;
    public RecommendationLetter() {

    }

    public RecommendationLetter(Long id, String text, String email, String prof_email) {
        this.id = id;
        this.email = email;
        this.prof_email = prof_email;
        Text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProf_email() {
        return prof_email;
    }

    public void setProf_email(String prof_email) {
        this.prof_email = prof_email;
    }

    public Long getRid() {
        return Rid;
    }

    public void setRid(Long rid) {
        Rid = rid;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
}
