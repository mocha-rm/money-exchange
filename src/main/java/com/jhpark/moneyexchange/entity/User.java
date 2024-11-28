package com.jhpark.moneyexchange.entity;

import com.jhpark.moneyexchange.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<UserCurrency> userCurrencies = new ArrayList<>();

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    protected User() {};
}
