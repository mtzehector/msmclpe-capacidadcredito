/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcreditoback.repository;

import mx.gob.imss.dpes.capacidadcreditoback.entity.McltCapacidadCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author andrea.cervantes
 */
public interface CapacidadRepository extends JpaRepository<McltCapacidadCredito, Long>,
        JpaSpecificationExecutor<McltCapacidadCredito> {
    
}
