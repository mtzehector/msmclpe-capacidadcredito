package mx.gob.imss.dpes.capacidadcreditoback.service;

import mx.gob.imss.dpes.capacidadcreditoback.entity.McltCapacidadCredito;
import mx.gob.imss.dpes.capacidadcreditoback.model.ReporteCartaCapacidad;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.service.ServiceDefinition;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;

@Provider
public class ReadCapacidadCreditoService extends ServiceDefinition<ReporteCartaCapacidad, ReporteCartaCapacidad> {

  @Inject
  CapacidadCreditoPersistenceService persistenceService;

  @Override
  public Message<ReporteCartaCapacidad> execute(Message<ReporteCartaCapacidad> request) throws BusinessException {

    McltCapacidadCredito capacidadCredito = new McltCapacidadCredito();
    capacidadCredito.setCveSolicitud(request.getPayload().getSolicitud().getId());
    Message<McltCapacidadCredito> load
        = persistenceService.consultaCapacidadPorSolicitud(new Message<>(capacidadCredito));
    request.getPayload().setMcltCapacidadCredito(load.getPayload());

    return request;
  }
}
