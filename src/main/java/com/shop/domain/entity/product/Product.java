package com.shop.domain.entity.product;

import com.shop.domain.entity.base.BaseEntity;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/*
 * Доменая модель товары
 */
@Getter
@Setter
@Entity
@Table(name = "product_tbl")
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Product extends BaseEntity {

    @Length(max = 20)
    @Column(name = "name_product")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "count_product")
    private Integer count;

    @Column(name = "product_state")
    private String state;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "history_id")
    private History history;
}
