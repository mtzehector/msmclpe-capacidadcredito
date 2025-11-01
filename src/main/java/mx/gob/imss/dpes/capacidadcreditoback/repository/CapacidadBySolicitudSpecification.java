/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcreditoback.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import mx.gob.imss.dpes.capacidadcreditoback.entity.McltCapacidadCredito;
import mx.gob.imss.dpes.capacidadcreditoback.entity.McltCapacidadCredito_;
import mx.gob.imss.dpes.baseback.persistence.BaseSpecification;

/**
 *
 * @author antonio
 */
public class CapacidadBySolicitudSpecification extends BaseSpecification<McltCapacidadCredito> {

  private final Long claveSolicitud;

  public CapacidadBySolicitudSpecification(Long claveSolicitud) {
    this.claveSolicitud = claveSolicitud;
  }

  @Override
  public Predicate toPredicate(Root<McltCapacidadCredito> root, CriteriaQuery<?> cq,
          CriteriaBuilder cb) {
      
        return cb.equal(root.get(McltCapacidadCredito_.CVE_SOLICITUD), this.claveSolicitud) ;
  }

}
