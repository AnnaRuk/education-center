package de.ait.ec.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity

public class Course {

    public enum State {
        DRAFT, PUBLISHED
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)

    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    private LocalDate beginDate;

    private LocalDate endDate;

    @Column(nullable = false)
    private double price;

    @Enumerated(value = EnumType.STRING)
    private State state;

}
