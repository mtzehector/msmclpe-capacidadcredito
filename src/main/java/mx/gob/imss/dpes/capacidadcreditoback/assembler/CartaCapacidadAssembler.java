/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcreditoback.assembler;

import mx.gob.imss.dpes.capacidadcreditoback.model.ReporteCartaCapacidad;
import mx.gob.imss.dpes.baseback.assembler.BaseAssembler;
import mx.gob.imss.dpes.common.enums.TipoCreditoEnum;
import mx.gob.imss.dpes.common.enums.TipoDocumentoEnum;
import mx.gob.imss.dpes.interfaces.documento.model.Documento;

import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.interfaces.capacidadcredito.model.CartaCapacidadCredito;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author antonio
 */
@Provider
public class CartaCapacidadAssembler
 extends BaseAssembler<ReporteCartaCapacidad, Documento, Long, Long>{

  @Override
  public ReporteCartaCapacidad toEntity(Documento source) {
    return new ReporteCartaCapacidad();
  }

  @Override
  public Long toPKEntity(Long source) {
    return source;
  }

  @Override
  public Documento assemble(ReporteCartaCapacidad source) {

    CartaCapacidadCredito cartaCapacidad = source.getCartaCapacidadCredito();
    if(cartaCapacidad.getCiudad() == null) cartaCapacidad.setCiudad("");
    cartaCapacidad.setFecha(new SimpleDateFormat("dd' de 'MMMM' de 'yyyy",Locale.forLanguageTag("es-ES"))
        .format(source.getSolicitud().getAltaRegistro()));
    cartaCapacidad.setFolio(source.getSolicitud().getNumFolioSolicitud());
    cartaCapacidad.setNombre(source.getPersona().getNombre());
    cartaCapacidad.setPrimerApe(source.getPersona().getPrimerApellido());
    cartaCapacidad.setSegundoApe(source.getPersona().getSegundoApellido());
    cartaCapacidad.setCurp(source.getSolicitud().getCurp());
    cartaCapacidad.setNss(source.getSolicitud().getNss());
    cartaCapacidad.setDelegacion(source.getSolicitud().getDelegacion());
    cartaCapacidad.setEmail(source.getPersona().getCorreoElectronico());
    cartaCapacidad.setTelefono(source.getPersona().getTelefono());
    cartaCapacidad.setTipoCredito( source.getMcltCapacidadCredito().getTipoCredito()!=null ?
        TipoCreditoEnum.forValue(source.getMcltCapacidadCredito().getTipoCredito()).getDescripcion():"");
    cartaCapacidad.setTipoTrabajador(source.getSolicitud().getRefTrabajador().substring(0,1).toUpperCase()
        + source.getSolicitud().getRefTrabajador().substring(1));
    cartaCapacidad.setImpCapacidadFija(source.getMcltCapacidadCredito().getImpCapacidadFija().toString());
    cartaCapacidad.setImpCapacidadVariable(source.getMcltCapacidadCredito().getImpCapacidadVariable().toString());
    cartaCapacidad.setImpCapacidadTotal(source.getMcltCapacidadCredito().getImpCapacidadTotal().toString());
    cartaCapacidad.setSelloDigital(source.getSello().getSello());
    cartaCapacidad.setCodigoQR(source.getSolicitud().getNumFolioSolicitud());

    source.getSello().setCadenaOriginal(cartaCapacidad.getCadenaOriginal());

    Documento documento = new Documento();
    documento.setCveSolicitud( source.getSolicitud().getId() );
    documento.setTipoDocumento(TipoDocumentoEnum.CARTA_CAPACIDAD_CREDITO);
    documento.setRefSello(source.getSello().toString());
    documento.setRefDocumento( source.getCartaCapacidadCredito().toString() );

    return documento;
  }

  @Override
  public Long assemblePK(Long source) {
    return source;
  }
  
}
