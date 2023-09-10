package co.edu.uniquindio.women_entrepeneurs_api.entidades;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "content")
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return Objects.equals(id, blog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
