package com.shop.domain.entity;

import lombok.*;

import javax.persistence.*;

/*
 * Доменая модель заказы, корзина
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Builder
@Entity
@Table(name = "order_tbl")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "order_state")
    private String state;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private User user;
}
