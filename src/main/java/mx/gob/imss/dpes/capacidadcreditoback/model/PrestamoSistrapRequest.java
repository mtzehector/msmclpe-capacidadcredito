
package mx.gob.imss.dpes.capacidadcreditoback.model;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;

public class PrestamoSistrapRequest extends BaseModel{
  @Getter @Setter private String nss;
  @Getter @Setter private String grupoFamiliar;
  @Getter @Setter private String folio;
  @Getter @Setter private String periodoNomina;
}
