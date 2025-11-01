/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcreditoback.entity;

import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.entity.LogicDeletedEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author andrea.cervantes
 */
@Entity
@Table(name = "MCLT_CAPACIDAD_CREDITO")
public class McltCapacidadCredito extends LogicDeletedEntity<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_CAPACIDAD_CREDITO")
    @GeneratedValue(generator = "SEQ_GEN_CAPACIDAD_CREDITO")
    @GenericGenerator(
            name = "SEQ_GEN_CAPACIDAD_CREDITO",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "MCLS_CAPACIDAD_CREDITO")
                ,
        @Parameter(name = "initial_value", value = "1")
                ,
        @Parameter(name = "increment_size", value = "1")
            }
    )
    @Getter @Setter private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CVE_SOLICITUD")
    @Getter
    @Setter
    private Long cveSolicitud;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "IMP_CAPACIDAD_TOTAL")
    @Getter @Setter private BigDecimal impCapacidadTotal;
    @Column(name = "IMP_CAPACIDAD_FIJA")
    @Getter @Setter private BigDecimal impCapacidadFija;
    @Column(name = "IMP_CAPACIDAD_VARIABLE")
    @Getter @Setter private BigDecimal impCapacidadVariable;
    @Column(name = "CVE_TIPO_CREDITO")
    @Getter @Setter private Long tipoCredito;
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
   
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof McltCapacidadCredito)) {
            return false;
        }
        McltCapacidadCredito other = (McltCapacidadCredito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    
}
