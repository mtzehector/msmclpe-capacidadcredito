/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcreditoback.endpoint;

import mx.gob.imss.dpes.capacidadcreditoback.entity.McltCapacidadCredito;
import mx.gob.imss.dpes.capacidadcreditoback.service.CapacidadCreditoPersistenceService;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author andrea.cervantes
 */
@ApplicationScoped
@Path("/capacidadCredito")
public class CapacidadCreditoEndPoint
    extends BaseGUIEndPoint<McltCapacidadCredito, McltCapacidadCredito, McltCapacidadCredito> {

  @Context
  private UriInfo uriInfo;

  @Inject
  private CapacidadCreditoPersistenceService service;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Override
  public Response create(McltCapacidadCredito capacidad) throws BusinessException {

    Message<McltCapacidadCredito> execute
        = service.execute(new Message<>(capacidad));
    return toResponse(execute);
  }

  @GET
  @Path("/{id}")
  public Response getCapacidadCredito(@PathParam("id") Long id) throws BusinessException{
    McltCapacidadCredito capacidadCredito = new McltCapacidadCredito();
    capacidadCredito.setCveSolicitud(id);

    Message<McltCapacidadCredito> execute = service.consultaCapacidadPorSolicitud(new Message<>(capacidadCredito));
    return toResponse(execute);
  }

}
