package mx.gob.imss.dpes.capacidadcreditoback.service;

import mx.gob.imss.dpes.capacidadcreditoback.model.PrestamoSistrapRequest;
import mx.gob.imss.dpes.capacidadcreditoback.model.PrestamoSistrapResponse;
import mx.gob.imss.dpes.capacidadcreditoback.model.ReporteCartaCapacidad;
import mx.gob.imss.dpes.capacidadcreditoback.restclient.PrestamoSistrapClient;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.exception.RecursoNoExistenteException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class ReadPrestamoSistrapService extends ServiceDefinition<ReporteCartaCapacidad, ReporteCartaCapacidad> {

  @Inject
  @RestClient
  private PrestamoSistrapClient sistrapClient;

  @Override
  public Message<ReporteCartaCapacidad> execute(Message<ReporteCartaCapacidad> request) throws BusinessException {
    PrestamoSistrapRequest sistrapRequest = new PrestamoSistrapRequest();
    sistrapRequest.setNss(request.getPayload().getSolicitud().getNss());
    sistrapRequest.setFolio(request.getPayload().getSolicitud().getNumFolioSolicitud());
    sistrapRequest.setGrupoFamiliar(request.getPayload().getSolicitud().getGrupoFamiliar());
    sistrapRequest.setPeriodoNomina(request.getPayload().getPrestamo().getNumPeriodoNomina().toString());

    Response response = sistrapClient.load(sistrapRequest);
    if (response.getStatus() == 200){
      PrestamoSistrapResponse sistrapResponse = response.readEntity(PrestamoSistrapResponse.class);
      request.getPayload().getCartaCapacidadCredito().setTipoPension(sistrapResponse.getTipoPension());

      return new Message<>(request.getPayload());
    }
    return response(null, ServiceStatusEnum.EXCEPCION, new RecursoNoExistenteException(), null);
  }
}
