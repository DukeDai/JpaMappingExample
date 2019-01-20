package com.dukedai.jpamapping.domain.entity;

import com.dukedai.jpamapping.domain.util.CreatableAndUpdatable;
import com.dukedai.jpamapping.domain.util.CreatedAtListener;
import com.dukedai.jpamapping.domain.util.UpdatedAtListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by duke on 2019/1/20
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"productId", "name"}, callSuper = false)
//@ToString(of = {"productId", "name"})
@EntityListeners({CreatedAtListener.class, UpdatedAtListener.class})
@Entity
@Table(name = "product")
public class ProductEntity implements CreatableAndUpdatable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long productId;

    @Basic
    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Date updatedAt;

    // Usage on mappedBy, the value is Java variable name in ProductItemEntity, not column name.
    // @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    private List<ProductItemEntity> itemList;

    // Usage of JoinedColumn, the value is column name.
    // Only one branch in the tree can be configured as EAGER, otherwise it'll hit "cannot simultaneously fetch
    // multiple bags" error.
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "productId")
    private List<ProductPropertyEntity> propertyList;


}
