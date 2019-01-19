package com.shop.domain.entity;

import com.shop.domain.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/*
* Доменая модель категории товаров
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
//@Builder()
@Entity
@Table(name = "category_tbl")
public class Category extends BaseEntity {

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> products;
}
