package com.shop.domain.entity;

import com.shop.domain.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_tbl")
@Entity
public class Account extends BaseEntity {

    @Column(name = "acc_num")
    @Length(min = 20, max = 20)
    private String accNum;

    @Column(name = "total_sum")
    private BigDecimal totalSumm;

    @OneToOne
    @JoinColumn(name = "fk_user_id")
    private User user;
}
