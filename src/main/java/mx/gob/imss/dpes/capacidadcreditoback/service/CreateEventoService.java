/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcreditoback.service;

import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import mx.gob.imss.dpes.interfaces.evento.model.Evento;
import mx.gob.imss.dpes.capacidadcreditoback.restclient.EventoClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;

/**
 *
 * @author antonio
 */
@Provider
public class CreateEventoService extends ServiceDefinition<Evento, Evento> {

    @Inject
    @RestClient
    private EventoClient client;
    
    @Override
    public Message<Evento> execute(Message<Evento> request) throws BusinessException {        
      log.log( Level.INFO, "Solicitando el Evento: {0}", request.getPayload());   
      Response event = client.create( request.getPayload() );
      return response(event, request);
    }

}
