package ru.skubatko.dev.skillsmart.hard.work.task65.case2.persistence;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "personId", nullable = true)
    private UUID personId;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "country", nullable = true)
    private String country;

    @CreationTimestamp
    @Column(name = "createdAt", nullable = true)
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {return false;}
        CarEntity carEntity = (CarEntity) o;
        return id != null && Objects.equals(id, carEntity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
