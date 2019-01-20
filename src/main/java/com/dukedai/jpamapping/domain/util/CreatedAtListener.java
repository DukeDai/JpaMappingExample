package com.dukedai.jpamapping.domain.util;

import javax.persistence.PrePersist;
import java.util.Date;

/**
 * Created by duke on 2019/1/20
 */
public class CreatedAtListener {

    @PrePersist
    public void setCreatedAt(final Creatable entity) {
        entity.setCreatedAt(new Date());
    }
}
