package com.shop.domain.entity;

import com.shop.domain.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "advertising_tbl")
@Entity
public class Advertising extends BaseEntity {

    private String filename;
}
