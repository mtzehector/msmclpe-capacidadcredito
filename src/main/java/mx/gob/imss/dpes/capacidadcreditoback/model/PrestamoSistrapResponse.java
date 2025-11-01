/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.capacidadcreditoback.model;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;

/**
 *
 * @author antonio
 */
public class PrestamoSistrapResponse extends BaseModel{
  @Getter @Setter private String nss;
  @Getter @Setter private String folio;
  @Getter @Setter private String grupoFamiliar;
  @Getter @Setter private String apellidoPaterno;
  @Getter @Setter private String apellidoMaterno;
  @Getter @Setter private String nombre;
  @Getter @Setter private String curp;
  @Getter @Setter private String telefono;
  @Getter @Setter private String correoElectronico;
  @Getter @Setter private String tipoTrabajador;
  @Getter @Setter private String tipoCredito;
  @Getter @Setter private String tipoPension;
  @Getter @Setter private String nombreComercialEntidadFinanciera;
  @Getter @Setter private String razonSocialEntidadFinanciera;
  @Getter @Setter private String telefonoEntidadFinanciera;
  @Getter @Setter private String paginaWebEntidadFinanciera;
  @Getter @Setter private String cat;
  @Getter @Setter private String totalPrestamo;
  @Getter @Setter private String descuentoMensual;
  @Getter @Setter private String tasaAnual;
  @Getter @Setter private String montoSolicitado;
  @Getter @Setter private String totalMensualidadesDescontadas;
  @Getter @Setter private String plazo;  
  
  @Getter @Setter private String totalConInteres;
  @Getter @Setter private String nominaPrimerDescuento;
  @Getter @Setter private String idPrestamoFinanciero;
  @Getter @Setter private String idEntidadFinanciera;
  @Getter @Setter private String idGrupoFamiliar;
  @Getter @Setter private String idMovimiento;
  @Getter @Setter private String registrosCargados;
  
}
