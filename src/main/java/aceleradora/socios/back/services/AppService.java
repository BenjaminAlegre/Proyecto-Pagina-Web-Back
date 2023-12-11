package aceleradora.socios.back.services;

import aceleradora.socios.back.clases.evento.Evento;
import aceleradora.socios.back.clases.evento.Modalidad;
import aceleradora.socios.back.clases.evento.Participante;
import aceleradora.socios.back.clases.evento.TipoParticipante;
import aceleradora.socios.back.repositorios.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppService {
    private final EstadoEventoRepository estadoEventoRepository;
    private final DepartamentoRepository departamentoRepository;
    private final UbicacionRepository ubicacionRepository;
    private final ParticipanteRepository repoParticipante;
    private final SocioRepository repoSocio;
    private final EventoRepository repoEvento;
    private final GeneradorCodigoUnico generadorCodigoUnico;

    public AppService(EstadoEventoRepository estadoEventoRepository, DepartamentoRepository departamentoRepository, UbicacionRepository ubicacionRepository, ParticipanteRepository repoParticipante, EventoRepository repoEvento, SocioRepository socioRespository, GeneradorCodigoUnico generadorCodigoUnico) {
        this.estadoEventoRepository = estadoEventoRepository;
        this.departamentoRepository = departamentoRepository;
        this.ubicacionRepository = ubicacionRepository;
        this.repoParticipante = repoParticipante;
        this.repoEvento = repoEvento;
        this.repoSocio = socioRespository;
        this.generadorCodigoUnico = generadorCodigoUnico;
    }

    @Transactional
    public void crearEventosYParticipantes() {
        for (int y = 1; y <= 20; y++) {
            Evento evento = new Evento();
            evento.setNombre("Evento " + y);
            evento.setDescripcion("Descripción del evento " + y);
            evento.setFechaInicio(LocalDate.now());
            evento.setFechaFin(LocalDate.now().plusDays(y % 5));
            evento.setHoraInicio(LocalTime.now());
            evento.setHoraFin(LocalTime.now().plusHours(y % 4));

            if (y % 6 == 0) {
                evento.setModalidad(Modalidad.HIBRIDA);
                evento.setLugar(ubicacionRepository.getReferenceById(1));
                evento.setDepartamento(departamentoRepository.getReferenceById(3L));
                evento.setEstado(estadoEventoRepository.getReferenceById(3L));
                evento.setLinkReunion("meet/"+y+".com");
                evento.setPlataforma("Meet");
            }
            else if (y % 2 == 0) {
                evento.setModalidad(Modalidad.VIRTUAL);
                evento.setDepartamento(departamentoRepository.getReferenceById(1L));
                evento.setEstado(estadoEventoRepository.getReferenceById(1L));
                evento.setLinkReunion("zoom/"+y+".com");
                evento.setPlataforma("Zoom");
            }
            else if (y % 3 == 0) {
                evento.setModalidad(Modalidad.PRESENCIAL);
                evento.setLugar(ubicacionRepository.getReferenceById(y==9?3:2));
                evento.setDepartamento(departamentoRepository.getReferenceById(2L));
                evento.setEstado(estadoEventoRepository.getReferenceById(2L));
            }
            else if (y % 5 == 0) {
                evento.setModalidad(Modalidad.VIRTUAL);
                evento.setDepartamento(departamentoRepository.getReferenceById(4L));
                evento.setEstado(estadoEventoRepository.getReferenceById(4L));
                evento.setLinkReunion("teams/"+y+".com");
                evento.setPlataforma("Teams");
            }
            else {
                evento.setModalidad(Modalidad.VIRTUAL);
                evento.setDepartamento(departamentoRepository.getReferenceById((long) y%7+1));
                evento.setEstado(estadoEventoRepository.getReferenceById((long) y%4+1));
                evento.setLinkReunion("discord/"+y+".com");
                evento.setPlataforma("Discord");
            }

            List<Participante> participantes = new ArrayList<>();

            for (int i = 1; i <= y%5+1; i++) {
                Participante participante = new Participante();
                participante.setNombre("Nombre " + i);
                participante.setApellido("Apellido " + i);

                if (i % 2 == 0) {
                    participante.setEstado("Estado del Asociado");
                    participante.setTipoParticipante(TipoParticipante.ASOCIADO);
                    participante.setSocioAsociado(repoSocio.findById(1L).get());
                    participante.setEmail(null);
                } else {
                    participante.setEstado("Estado del Invitado");
                    participante.setTipoParticipante(TipoParticipante.INVITADO);
                    participante.setSocioConvocante(repoSocio.findById(2L).get());
                    participante.setEntidadQueRepresenta("Empresa del Invitado");
                    participante.setEmail("unmail" + i + "@macmail.com");
                }

                repoParticipante.save(participante); // Guardar el participante en la base de datos
                participantes.add(participante); // Agregar el participante a la lista de participantes del evento
            }

            evento.setParticipantes(participantes); // Establecer la lista de participantes en el evento
            Evento eventoguardado = repoEvento.save(evento); // Guardar el evento en la base de datos
            eventoguardado.setCodigoUUID(generadorCodigoUnico.generarCodigoUnico(evento.getId()));
            repoEvento.save(eventoguardado);
        }
    }
}
