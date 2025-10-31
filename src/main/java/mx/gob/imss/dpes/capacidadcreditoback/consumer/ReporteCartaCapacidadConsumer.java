package mx.gob.imss.dpes.capacidadcreditoback.consumer;

import mx.gob.imss.dpes.capacidadcreditoback.model.ReporteCartaCapacidad;
import mx.gob.imss.dpes.capacidadcreditoback.service.*;
import mx.gob.imss.dpes.common.enums.EventEnum;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.service.ServiceDefinition;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import java.util.logging.Level;
import mx.gob.imss.dpes.common.consumer.BaseConsumer;
import mx.gob.imss.dpes.interfaces.evento.model.Evento;

@MessageDriven(name = "ReporteCartaCapacidadConsumer", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/MCLPETopic"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})

public class ReporteCartaCapacidadConsumer extends BaseConsumer {

    @Inject
    ReadSolicitudService readSolicitudService;
    @Inject
    ReadPersonaBdtuService readPersonaService;
    @Inject
    ReadPrestamoService readPrestamoService;
    @Inject
    ReadPensionadoService readPensionadoService;
    @Inject
    ReadCapacidadCreditoService readCapacidadCreditoService;
    @Inject
    CreateSelloElectronicoCapacidadService createSelloElectronicoCapacidadService;
    @Inject
    CreateReporteCartaCapacidadService createReporteCartaCapacidadService;

    @Inject
    CreateEventoService createEventoService;

    @Override
    protected void proccess(Message message) {
        ServiceDefinition[] steps = {
            readSolicitudService,
            readCapacidadCreditoService,
            readPersonaService,
            //readPrestamoService,
            readPensionadoService,
            createSelloElectronicoCapacidadService,
            createReporteCartaCapacidadService
        };

        if (EventEnum.CREAR_SOLICITUD_CAPACIDAD_CREDITO.equals(message.getHeader().getEvent())) {

            try {
                log.log(Level.INFO, "Consumir evento de creación de solicitud {0}", message);
                ReporteCartaCapacidad reporteCartaCapacidad = new ReporteCartaCapacidad();
                reporteCartaCapacidad.getSolicitud().setId((Long) message.getPayload());
                Message<ReporteCartaCapacidad> response
                        = createReporteCartaCapacidadService.executeSteps(steps, new Message<>(reporteCartaCapacidad));

                if (!Message.isException(response)) {
                    Evento evento = new Evento();
                    evento.setId(reporteCartaCapacidad.getSolicitud().getId());
                    evento.setEvent(EventEnum.CREAR_REPORTE_CAPACIDAD_CREDITO);
                    createEventoService.execute(new Message<>(evento));
                }

                log.log(Level.INFO, "Se termino de atender el evento de creación de solicitud {0}", message);
            } catch (BusinessException ex) {
                log.log(Level.SEVERE, null, ex);
            }

        }
    }
}
