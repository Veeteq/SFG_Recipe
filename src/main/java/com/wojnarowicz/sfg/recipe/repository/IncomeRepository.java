package com.wojnarowicz.sfg.recipe.repository;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.wojnarowicz.sfg.recipe.domain.Income;
import com.wojnarowicz.sfg.recipe.dto.IDailySummaryByUser;

public interface IncomeRepository extends CrudRepository<Income, Long>{

    Set<Income> findByOperationDate(LocalDate operationDate);
   
    @Query(value = "SELECT DECODE(i.ITEM_NAME_TX, '_Transfer', 1, 0) transferFlag," + 
            "       u.USER_NAME_TX userName," + 
            "       sum(e.INCO_PRIC_AM) amount" + 
            "  FROM INCOMES AS e," + 
            "       ITEMS AS i," + 
            "       USERS AS u" + 
            " WHERE e.ITEM_ID = i.ITEM_ID" + 
            "   AND e.USER_ID = u.USER_ID" + 
            "   AND e.OPER_DT = :operationDate" + 
            " GROUP BY DECODE(i.ITEM_NAME_TX, '_Transfer', 1, 0)," + 
            "       u.USER_NAME_TX",
    nativeQuery = true)
    Set<IDailySummaryByUser> findDailySummaryByUser(LocalDate operationDate);
}
