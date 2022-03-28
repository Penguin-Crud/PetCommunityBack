package com.petCommunity.PetCommunityBack.DomainModels;


import lombok.*;
import javax.persistence.*;
import java.util.Date;


@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class    Pet {
    public enum Sizes {
        small,
        medium,
        large
    }
    public enum Gender {
        male,
        female
    }
    public enum Priority{
        low,
        medium,
        high
    }

    public enum Specie{
        dog,
        cat,
        other
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean chip;
    private String race;
    @Enumerated(EnumType.STRING)
    private Sizes size;
    private String age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private Specie specie;
    private Boolean vaccines;
    @Column(length = 200)
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false , updatable = false)
    private Date created_at;


    @PrePersist
    private void onCreate(){
        created_at = new Date(System.currentTimeMillis());
    }
}
