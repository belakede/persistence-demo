package me.belakede.persistence.demo.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "notes")
public class Note implements PersistentEntity, Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private User user;

    @Column(nullable = false)
    private String summary;

    private String description;

    public Note() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (!id.equals(note.id)) return false;
        if (!user.equals(note.user)) return false;
        return summary.equals(note.summary);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + summary.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", user=" + user.getFullName() +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
