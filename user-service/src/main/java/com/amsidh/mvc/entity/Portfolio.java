package com.amsidh.mvc.entity;

import com.amsidh.mvc.common.Ticker;
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
@Table(name = "portfolio")
public class Portfolio {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Ticker ticker;
    private Integer quantity;

}