package mx.gob.imss.dpes.capacidadcreditoback.model;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.capacidadcreditoback.entity.McltCapacidadCredito;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.interfaces.serviciosdigitales.model.Persona;
import mx.gob.imss.dpes.common.model.Reporte;
import mx.gob.imss.dpes.interfaces.capacidadcredito.model.CartaCapacidadCredito;
import mx.gob.imss.dpes.interfaces.documento.model.Documento;
import mx.gob.imss.dpes.interfaces.prestamo.model.Prestamo;
import mx.gob.imss.dpes.interfaces.serviciosdigitales.model.SelloElectronicoResponse;
import mx.gob.imss.dpes.interfaces.solicitud.model.Solicitud;

public class ReporteCartaCapacidad extends BaseModel {

  @Getter @Setter CartaCapacidadCredito cartaCapacidadCredito = new CartaCapacidadCredito();
  @Getter @Setter Reporte reporte = new Reporte();
  @Getter @Setter Solicitud solicitud = new Solicitud();
  @Getter @Setter Persona persona = new Persona();
  @Getter @Setter Prestamo prestamo = new Prestamo();
  @Getter @Setter String xml;
  @Getter @Setter McltCapacidadCredito mcltCapacidadCredito = new McltCapacidadCredito();
  @Getter @Setter SelloElectronicoResponse sello = new SelloElectronicoResponse();
  @Getter @Setter Documento documento = new Documento();
}
