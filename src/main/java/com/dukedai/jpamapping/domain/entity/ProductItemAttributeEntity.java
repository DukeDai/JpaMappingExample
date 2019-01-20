package com.dukedai.jpamapping.domain.entity;

import com.dukedai.jpamapping.domain.util.CreatableAndUpdatable;
import com.dukedai.jpamapping.domain.util.CreatedAtListener;
import com.dukedai.jpamapping.domain.util.UpdatedAtListener;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by duke on 2019/1/20
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "product_item_attribute")
@EntityListeners({CreatedAtListener.class, UpdatedAtListener.class})
public class ProductItemAttributeEntity implements CreatableAndUpdatable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productItemAttributeId")
    private Long productItemAttributeId;

    @Basic(optional = false)
    @Column(name = "productItemId", insertable = false, updatable = false)
    private Long productItemId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "value")
    private String value;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic
    @Column(name = "updatedAt", nullable = false)
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "productItemId")
    private ProductItemEntity productItem;

}
