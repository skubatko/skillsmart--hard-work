package ru.skubatko.dev.skillsmart.hard.work.task65.case1.persistence;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "applications")
public class ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "number", nullable = true)
    private String number;

    @Column(name = "channel", nullable = true)
    private String channel;

    @CreationTimestamp
    @Column(name = "createdAt", nullable = true)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt", nullable = true)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ApplicantEntity> applicants = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {return false;}
        ApplicationEntity that = (ApplicationEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
            "id = " + id + ")";
    }
}
