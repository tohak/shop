package com.shop.domain.entity.other;

import com.shop.domain.entity.base.BaseEntity;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "new_tbl")
@ToString(callSuper = true)
@Entity
public class News extends BaseEntity {

    @Length(max = 100)
    @Column(name = "title_new")
    private String title;

    @Length(max = 300)
    @Column(name = "description_new")
    private String description;
}
