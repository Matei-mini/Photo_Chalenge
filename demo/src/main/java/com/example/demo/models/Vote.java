package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "votes", schema = "dbo", uniqueConstraints = @UniqueConstraint(columnNames = {"voter_id", "picture_id"}))
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "voter_id")
    private Users voter;

    @ManyToOne
    @JoinColumn(name = "picture_id")
    private Picture picture;

    @Column(nullable = false)
    @Min(1)
    private Integer value;
}
