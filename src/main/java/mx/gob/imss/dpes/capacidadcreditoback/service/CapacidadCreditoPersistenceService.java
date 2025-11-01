/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcreditoback.service;

import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;

import mx.gob.imss.dpes.capacidadcreditoback.entity.McltCapacidadCredito;
import mx.gob.imss.dpes.capacidadcreditoback.repository.CapacidadBySolicitudSpecification;
import mx.gob.imss.dpes.capacidadcreditoback.repository.CapacidadRepository;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.baseback.persistence.BaseSpecification;
import mx.gob.imss.dpes.baseback.service.BaseCRUDService;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author andrea.cervantes
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class CapacidadCreditoPersistenceService extends BaseCRUDService<McltCapacidadCredito, McltCapacidadCredito, Long, Long> {

    @Autowired
    private CapacidadRepository repository;

    public Message<McltCapacidadCredito> execute(Message<McltCapacidadCredito> request) throws BusinessException {

        McltCapacidadCredito saved = save(request.getPayload());

        return new Message<>(saved);
    }

    public Message<McltCapacidadCredito> consultaCapacidadPorSolicitud(Message<McltCapacidadCredito> request)
        throws BusinessException {
        //Filtra por la clave de la solicitud
        Collection<BaseSpecification> constraints = new ArrayList<>();
        constraints.add( new CapacidadBySolicitudSpecification(request.getPayload().getCveSolicitud() ));

        //Ordena descendente para poder tomar la más reciente en el indice 0p
        List<Sort.Order> orden = new ArrayList<>();
        orden.add(new Sort.Order(Sort.Direction.DESC,"altaRegistro"));

        List<McltCapacidadCredito> capacidades = this.load(constraints, orden);
        if(capacidades != null){
            return new Message<>(capacidades.get(0));
        }
        return null;
    }

    @Override
    public JpaSpecificationExecutor<McltCapacidadCredito> getRepository() {
        return repository;
    }

    @Override
    public JpaRepository<McltCapacidadCredito, Long> getJpaRepository() {
        return repository;
    }


}
