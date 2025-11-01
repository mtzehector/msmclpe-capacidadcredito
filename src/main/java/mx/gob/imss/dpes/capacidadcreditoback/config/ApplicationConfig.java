/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcreditoback.config;

/**
 * @author antonio
 */

import javax.ws.rs.core.Application;
import java.util.Set;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new java.util.HashSet<>();
    addRestResourceClasses(resources);
    return resources;
  }

  /**
   * Do not modify addRestResourceClasses() method.
   * It is automatically populated with
   * all resources defined in the project.
   * If required, comment out calling this method in getClasses().
   */
  private void addRestResourceClasses(Set<Class<?>> resources) {
    resources.add(mx.gob.imss.dpes.capacidadcreditoback.assembler.CartaCapacidadAssembler.class);
    resources.add(mx.gob.imss.dpes.capacidadcreditoback.endpoint.CapacidadCreditoEndPoint.class);
    resources.add(mx.gob.imss.dpes.capacidadcreditoback.rules.CadenaOriginalRule.class);
    resources.add(mx.gob.imss.dpes.capacidadcreditoback.service.CapacidadCreditoPersistenceService.class);
    resources.add(mx.gob.imss.dpes.capacidadcreditoback.service.CreateEventoService.class);
    resources.add(mx.gob.imss.dpes.capacidadcreditoback.service.CreateReporteCartaCapacidadService.class);
    resources.add(mx.gob.imss.dpes.capacidadcreditoback.service.CreateSelloElectronicoCapacidadService.class);
    resources.add(mx.gob.imss.dpes.capacidadcreditoback.service.ReadCapacidadCreditoService.class);
    resources.add(mx.gob.imss.dpes.capacidadcreditoback.service.ReadPensionadoService.class);
    resources.add(mx.gob.imss.dpes.capacidadcreditoback.service.ReadPersonaBdtuService.class);
    resources.add(mx.gob.imss.dpes.capacidadcreditoback.service.ReadPersonaService.class);
    resources.add(mx.gob.imss.dpes.capacidadcreditoback.service.ReadPrestamoService.class);
    resources.add(mx.gob.imss.dpes.capacidadcreditoback.service.ReadPrestamoSistrapService.class);
    resources.add(mx.gob.imss.dpes.capacidadcreditoback.service.ReadSolicitudService.class);
    resources.add(mx.gob.imss.dpes.common.exception.AlternateFlowMapper.class);
    resources.add(mx.gob.imss.dpes.common.exception.BusinessMapper.class);
    resources.add(mx.gob.imss.dpes.common.rule.MontoTotalRule.class);
        resources.add(mx.gob.imss.dpes.common.rule.PagoMensualRule.class);

  }

}