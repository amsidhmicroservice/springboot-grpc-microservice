package com.amsidh.mvc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "userinfo")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer balance;
}
