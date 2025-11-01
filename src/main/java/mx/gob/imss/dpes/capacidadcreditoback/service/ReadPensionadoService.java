/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcreditoback.service;

import mx.gob.imss.dpes.capacidadcreditoback.model.PensionadoRequest;
import mx.gob.imss.dpes.capacidadcreditoback.model.ReporteCartaCapacidad;
import mx.gob.imss.dpes.capacidadcreditoback.restclient.PensionadoClient;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.exception.RecursoNoExistenteException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import mx.gob.imss.dpes.interfaces.pensionado.model.Pensionado;

/**
 * @author salvador.pocteco
 */
@Provider
public class ReadPensionadoService extends ServiceDefinition<ReporteCartaCapacidad, ReporteCartaCapacidad> {

  @Inject
  @RestClient
  private PensionadoClient pensionadoClient;

  @Override
  public Message<ReporteCartaCapacidad> execute(Message<ReporteCartaCapacidad> request) throws BusinessException {

    PensionadoRequest pensionadoRequest = new PensionadoRequest();
    pensionadoRequest.setNss(request.getPayload().getSolicitud().getNss());
    pensionadoRequest.setGrupoFamiliar(request.getPayload().getSolicitud().getGrupoFamiliar());
    log.log(Level.INFO, "El request general hacia pensionado es : {0}", pensionadoRequest);
    Response respuesta = pensionadoClient.load(pensionadoRequest);

    if (respuesta.getStatus() == 200) {
      Pensionado pensionado = respuesta.readEntity(Pensionado.class);
      request.getPayload().getCartaCapacidadCredito().setTipoPension(pensionado.getTipoPension());
      request.getPayload().getCartaCapacidadCredito().setCiudad(pensionado.getEntidadFederativa().getDescEntidadFederativa());
      return new Message<>(request.getPayload());
    } else {
      log.log(Level.INFO,"El request general hacia pensionado es : {0}", respuesta.getStatus());
    }

    return response(null, ServiceStatusEnum.EXCEPCION, new RecursoNoExistenteException(), null);
  }
}
