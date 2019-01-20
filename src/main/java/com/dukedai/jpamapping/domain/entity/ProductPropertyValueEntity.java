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
@Table(name = "product_property_value")
@EntityListeners({CreatedAtListener.class, UpdatedAtListener.class})
public class ProductPropertyValueEntity implements CreatableAndUpdatable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productPropertyValueId")
    private Long productPropertyValueId;

    @Basic(optional = false)
    @Column(name = "productPropertyId", insertable = false, updatable = false)
    private Long productPropertyId;

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
    @JoinColumn(name = "productPropertyId")
    private ProductPropertyEntity productProperty;

}
