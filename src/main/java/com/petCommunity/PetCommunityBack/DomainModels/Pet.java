package com.petCommunity.PetCommunityBack.DomainModels;


import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Pet {
    public enum Sizes {
        SMALL,
        MEDIUM,
        LARGE
    }
    public enum Gender {
        MALE,
        FEMALE
    }
    public enum Priority{
        LOW,
        MEDIUM,
        HIGH
    }

    public enum Specie{
        DOG,
        CAT,
        OTHER
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
    @Column(nullable = false)
    private Date created_at;



    private void onCreate(){
        created_at = new Date(System.currentTimeMillis());
    }



}
