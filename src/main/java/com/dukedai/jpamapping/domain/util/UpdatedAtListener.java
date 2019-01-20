package com.dukedai.jpamapping.domain.util;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by duke on 2019/1/20
 */
public class UpdatedAtListener {
    @PrePersist
    @PreUpdate
    public void setUpdatedAt(final Updatable entity) {
        entity.setUpdatedAt(new Date());
    }
}
