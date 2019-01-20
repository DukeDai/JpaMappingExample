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
@Table(name = "product_property")
@EntityListeners({CreatedAtListener.class, UpdatedAtListener.class})
public class ProductPropertyEntity implements CreatableAndUpdatable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productPropertyId")
    private Long productPropertyId;

    @Basic(optional = false)
    @Column(name = "productId", insertable = false, updatable = false)
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

    // Only one branch in the tree can be configured as EAGER, otherwise it'll hit "cannot simultaneously fetch
    // multiple bags" error.
    @OneToMany(mappedBy = "productProperty", cascade = {CascadeType.ALL})
    // @JoinColumn(name = "productPropertyId")
    private List<ProductPropertyValueEntity> valueList;

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity product;

}
