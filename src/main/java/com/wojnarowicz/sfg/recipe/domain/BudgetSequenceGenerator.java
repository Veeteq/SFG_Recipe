package com.wojnarowicz.sfg.recipe.domain;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

public class BudgetSequenceGenerator  extends SequenceStyleGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        
        if(object instanceof BaseEntity) {
            BaseEntity persistent = (BaseEntity) object;
            if(persistent.getId() != null && persistent.getId() >= 0) {
                return persistent.getId();
            }
        }
        return super.generate(session, object);
    }
}
