package com.wojnarowicz.sfg.recipe.domain;

import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fin_events")
@AttributeOverride(name = "id", column = @Column(name = "evnt_id"))
@AttributeOverride(name = "name", column = @Column(name = "evnt_name_tx"))
@SequenceGenerator(name = "default_seq", sequenceName = "evnt_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
public class FinancialEvent extends NamedEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "evnt_dt")
    private LocalDate eventDate;

    @ManyToOne
    @JoinColumn(name="cprt_id", nullable=false)
    private Counterparty counterparty;
}
