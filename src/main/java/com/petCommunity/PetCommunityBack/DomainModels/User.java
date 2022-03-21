package com.petCommunity.PetCommunityBack.DomainModels;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name= "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String username;
    private  String email;
    @JsonIgnore
    private String password;
    private String logo;
    private String adress;
    private Integer capacity;
    @ManyToMany
    private Set<Role> roles;

    public User(String name) {
        this.username = name;
    }

    public User(String username, String email, String encode) {
        this.username = username;
        this.email = email;
        this.password = encode;
    }


}
