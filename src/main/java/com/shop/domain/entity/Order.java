package com.shop.domain.entity;

import com.shop.domain.entity.base.BaseEntity;
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
//@Builder
@Entity
@Table(name = "order_tbl")
public class Order extends BaseEntity {

    @Column(name = "order_state")
    private String state;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private User user;
}
