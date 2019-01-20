package com.dukedai.jpamapping.domain.entity;

import com.dukedai.jpamapping.domain.util.CreatableAndUpdatable;
import com.dukedai.jpamapping.domain.util.CreatedAtListener;
import com.dukedai.jpamapping.domain.util.UpdatedAtListener;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by duke on 2019/1/20
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "product_item")
@EntityListeners({CreatedAtListener.class, UpdatedAtListener.class})
public class ProductItemEntity implements CreatableAndUpdatable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productItemId")
    private Long productItemId;

    // when insert FK is not populated. when update FK must exist.
    @Basic(optional = false)
    @Column(name = "productId", insertable = false, updatable = false, nullable = false)
    private Long fkProductId;

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

    /*Two level FetchType.EAGER generates LEFT OUTER JOIN sql, will cause problem. Whatever using mappedBy or
    JoinColumn, List<ProductItemEntity> will have more items than expected. Solution: 1. use Set<ProductItemEntity>
    instead; 2. only use one level EAGER.
     */
    // @OneToMany(mappedBy = "productItem", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "productItemId")
    private List<ProductItemAttributeEntity> attributeList;

    // for cascading insert, bi-direction. If no such mapping, cascading insert won't work due to no way to set FK.
    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity product;

}
