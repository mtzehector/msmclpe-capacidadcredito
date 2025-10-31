package mx.gob.imss.dpes.capacidadcreditoback.service;

import mx.gob.imss.dpes.capacidadcreditoback.model.ReporteCartaCapacidad;
import mx.gob.imss.dpes.capacidadcreditoback.restclient.SelloElectronicoClient;
import mx.gob.imss.dpes.capacidadcreditoback.rules.CadenaOriginalRule;
import mx.gob.imss.dpes.common.exception.SelloElectronicoException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import mx.gob.imss.dpes.interfaces.serviciosdigitales.model.SelloElectronicoResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class CreateSelloElectronicoCapacidadService
    extends ServiceDefinition<ReporteCartaCapacidad, ReporteCartaCapacidad> {

  @Inject
  @RestClient
  private SelloElectronicoClient selloElectronicoClient;

  @Inject
  CadenaOriginalRule cadenaOriginalRule;

  @Override
  public Message<ReporteCartaCapacidad> execute(Message<ReporteCartaCapacidad> request) {

    CadenaOriginalRule.Output cadena =
        cadenaOriginalRule.apply(cadenaOriginalRule.new Input(request.getPayload()));
    request.getPayload().getCartaCapacidadCredito().setCadenaOriginal(cadena.getRequest().getCadenaOriginal());

    Response sello = selloElectronicoClient.create(cadena.getRequest());

    if (sello.getStatus() == 200) {
      SelloElectronicoResponse selloResponse = sello.readEntity(SelloElectronicoResponse.class);
      request.getPayload().setSello(selloResponse);
      return request;
    }

    return response(null, ServiceStatusEnum.EXCEPCION, new SelloElectronicoException(), null);
  }
}
