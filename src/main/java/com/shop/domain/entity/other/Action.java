package com.shop.domain.entity.other;

import com.shop.domain.entity.base.BaseEntity;
import com.shop.domain.entity.product.Product;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "action_tbl")
@ToString(callSuper = true)
@Entity
public class Action extends BaseEntity {

    private String stock;

    @OneToMany
    @JoinColumn(name = "product_id")
    private Set<Product> products;

}
