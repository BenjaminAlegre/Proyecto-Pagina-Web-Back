package aceleradora.socios.back;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import aceleradora.socios.back.clases.departamento.Autoridad;
import aceleradora.socios.back.clases.departamento.Departamento;
import aceleradora.socios.back.clases.departamento.Puesto;
import aceleradora.socios.back.clases.espacio.EstadoReserva;
import aceleradora.socios.back.clases.espacio.Lugar;
import aceleradora.socios.back.clases.espacio.Recurso;
import aceleradora.socios.back.clases.espacio.Reserva;
import aceleradora.socios.back.clases.evento.*;
import aceleradora.socios.back.clases.socio.Socio;
import aceleradora.socios.back.clases.ubicacion.Ubicacion;
import aceleradora.socios.back.repositorios.*;
import aceleradora.socios.back.services.AppService;
import aceleradora.socios.back.services.GeneradorCodigoUnico;
import aceleradora.socios.back.services.ReservaService;
import aceleradora.socios.back.services.UbicacionService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import aceleradora.socios.back.clases.socio.Categoria;
import aceleradora.socios.back.clases.socio.Etiqueta;
import aceleradora.socios.back.clases.Usuario;

import static aceleradora.socios.back.clases.evento.Modalidad.*;

import java.util.Random;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner init(EtiquetaRepository repoEtiqueta, CategoriaRepository repoCategoria, ParticipanteRepository repoParticipante, EventoRepository repoEvento,
                                  UserRepository userRepository, DepartamentoRepository departamentoRepository, AutoridadRepository autoridadRepository,
                                  PuestoRepository puestoRepository, EstadoEventoRepository estadoEventoRepository, AppService appService, UbicacionService ubicacionService,
                                  SocioRepository socioRepository, GeneradorCodigoUnico generadorCodigoUnico, PlataformaRepository plataformaRepository, LugarRepository lugarRepository,
                                  ReservaRepository reservaRepository,RecursoRepository recursoRepository, EstadoReservaRepository estadoReservaRepository) {

        return (args) -> {

            // SET TIPOS DE SOCIOS
            Optional<Categoria> categoriaValidacion = repoCategoria.findById(1L);
            if (!categoriaValidacion.isPresent()) {
                Categoria socioPlenario = new Categoria();
                socioPlenario.setNombre("socio plenario");
                Categoria socioAdherente = new Categoria();
                socioAdherente.setNombre("socio adherente");
                repoCategoria.save(socioPlenario);
                repoCategoria.save(socioAdherente);
            }

            // SET ETIQUETAS
            Optional<Etiqueta> etiquetaValidacion = repoEtiqueta.findById(1L);
            if (!etiquetaValidacion.isPresent()) {
                Etiqueta etiqueta1 = new Etiqueta();
                etiqueta1.setNombre("Normativa");

                Etiqueta etiqueta2 = new Etiqueta();
                etiqueta2.setNombre("RRII");

                Etiqueta etiqueta3 = new Etiqueta();
                etiqueta3.setNombre("Ambiente");

                Etiqueta etiqueta4 = new Etiqueta();
                etiqueta4.setNombre("Comité Ejecutivo");

                Etiqueta etiqueta5 = new Etiqueta();
                etiqueta5.setNombre("Comisión Directiva");

                Etiqueta etiqueta6 = new Etiqueta();
                etiqueta6.setNombre("Empresas");

                Etiqueta etiqueta7 = new Etiqueta();
                etiqueta7.setNombre("Cámaras");

                Etiqueta etiqueta8 = new Etiqueta();
                etiqueta8.setNombre("Presidentes");

                Etiqueta etiqueta9 = new Etiqueta();
                etiqueta9.setNombre("Gerentes");

                Etiqueta etiqueta10 = new Etiqueta();
                etiqueta10.setNombre("Comex");

                Etiqueta etiqueta11 = new Etiqueta();
                etiqueta11.setNombre("Pyme");

                Etiqueta etiqueta12 = new Etiqueta();
                etiqueta12.setNombre("Fiscal");

                Etiqueta etiqueta13 = new Etiqueta();
                etiqueta13.setNombre("Laborales");

                Etiqueta etiqueta14 = new Etiqueta();
                etiqueta14.setNombre("Legales");

                Etiqueta etiqueta15 = new Etiqueta();
                etiqueta15.setNombre("Envases");


                repoEtiqueta.saveAll(List.of(etiqueta1, etiqueta2, etiqueta3, etiqueta4, etiqueta5, etiqueta6, etiqueta7, etiqueta8, etiqueta9, etiqueta10,etiqueta11, etiqueta12, etiqueta13, etiqueta14, etiqueta15));

            }


            // SET SOCIOS
            Optional<Usuario> socioValidacion = userRepository.findById(1L);
            if (!socioValidacion.isPresent()) {
                List<Categoria> categorias = repoCategoria.findAll();
                List<Etiqueta> etiquetas = repoEtiqueta.findAll();

                Socio socio1 = new Socio("Arcor", "Pres. Arcor",12345678901L,1123232323L,"arcor@mail.com",
                        categorias.get(1),"arcor.com",Date.valueOf("2023-09-21"), etiquetas.subList(0, 2));
                socioRepository.save(socio1);

                Socio socio2 = new Socio("Cento Azucarero Argentino", "Pres. CAA", 23456789012L, 1134343434L, "CAA@mail.com",
                        categorias.get(0), "CAA.com", Date.valueOf("2023-09-21"), etiquetas.subList(2, 4));
                socioRepository.save(socio2);

                Socio socio3 = new Socio("CocaCola", "Pres. CocaCola", 34567890123L, 1145454545L, "cocacola@mail.com",
                        categorias.get(1), "cocacola.com", Date.valueOf("2023-09-21"), etiquetas.subList(4, 6));
                socioRepository.save(socio3);

                Socio socio4 = new Socio("Ivess", "Pres. Ivess", 45678901234L, 1156565656L, "ivess@mail.com",
                        categorias.get(1), "ivess.com", Date.valueOf("2023-09-21"), etiquetas.subList(6, 8));
                socioRepository.save(socio4);

                Socio socio5 = new Socio("Federación Argentina de Destilados y Aperitivos", "Pres. FADA", 56789012345L, 1167676767L, "FADA@mail.com",
                        categorias.get(0), "FADA.com", Date.valueOf("2023-09-21"), etiquetas.subList(8, 10));
                socioRepository.save(socio5);

                Socio socio6 = new Socio("Paladini", "Pres. Paladini", 67890123456L, 1178787878L, "paladini@mail.com",
                        categorias.get(1), "paladini.com", Date.valueOf("2023-09-21"), etiquetas.subList(10, 12));
                socioRepository.save(socio6);

                Socio socio7 = new Socio("Cámara Argentina de la Industria de Bebidas sin Alcohol", "Pres. CADIBSA", 78901234567L, 1189898989L, "CADIBSA@mail.com",
                        categorias.get(0), "CADIBSA.com", Date.valueOf("2023-09-21"), etiquetas.subList(12, 14));
                socioRepository.save(socio7);

                Socio socio8 = new Socio("Cachamai", "Pres. Cachamai", 89012345678L, 1191010101L, "cachamai@mail.com",
                        categorias.get(1), "cachamai.com", Date.valueOf("2023-09-21"), etiquetas.subList(14, 15));
                socioRepository.save(socio8);

                Socio socio9 = new Socio("Cámara Argentina de Especias Molineros de Pimenton y Afines", "Pres. CAEMPA", 90123456789L, 1191111111L, "CAEMPA@mail.com",
                        categorias.get(0), "CAEMPA.com", Date.valueOf("2023-09-21"), etiquetas.subList(0, 5));
                socioRepository.save(socio9);

                Socio socio10 = new Socio("Fargo", "Pres. Fargo", 34567890123L, 1145454545L, "fargo@mail.com",
                        categorias.get(1), "fargo.com", Date.valueOf("2023-09-21"), etiquetas.subList(5, 10));
                socioRepository.save(socio10);

                Socio socio11 = new Socio("Nestle", "Pres. Nestle", 45678901234L, 1156565656L, "nestle@mail.com",
                        categorias.get(1), "nestle.com", Date.valueOf("2023-09-21"), etiquetas.subList(10, 15));
                socioRepository.save(socio11);

                Socio socio12 = new Socio("Pepsico", "Pres. Pepsico", 56789012345L, 1167676767L, "pepsico@mail.com",
                        categorias.get(1), "pepsico.com", Date.valueOf("2023-09-21"), etiquetas.subList(0, 15));
                socioRepository.save(socio12);
            }

            //SET DEPARTAMENTOS
            Optional<Departamento> dptoValidacion = departamentoRepository.findById(1L);
            if (!dptoValidacion.isPresent()) {
                Departamento dpto1 = new Departamento();
                dpto1.setNombre("Economia, Desarrollo Regional y PYME");
                dpto1.setObjetivo("Su objetivo es el diseño de propuestas y seguimiento en materia de políticas de desarrollo productivo, acceso al financiamiento y mejora de la competitividad de los sectores de la industria de alimentos y bebidas, en particular las economías regionales y el entramado PyME.");
                dpto1.setEstaActivo(true);
                Departamento dpto2 = new Departamento();
                dpto2.setNombre("Normativa Alimentaria");
                dpto2.setObjetivo("Su objetivo principal consiste en el seguimiento y análisis de las regulaciones alimentarias vigentes y proyectos de las mismas, sean estos de carácter regional (MERCOSUR), nacional, provincial o municipal, dando cobertura a distintas instancias de discusión normativa.");
                dpto2.setEstaActivo(true);
                Departamento dpto3 = new Departamento();
                dpto3.setNombre("Asuntos Institucionales y Comunicación");
                dpto3.setObjetivo("Su objetivo es desarrollar la estrategia institucional y de comunicación para posicionar la agenda de propuestas de política pública para la mejora de los sectores de la IAB ante el Gobierno Nacional, los Gobiernos Provinciales y los Gobiernos Municipales.");
                dpto3.setEstaActivo(true);
                Departamento dpto4 = new Departamento();
                dpto4.setNombre("Política Fiscal y Tributaria");
                dpto4.setObjetivo("Su objetivo principal es analizar, evaluar y proponer las mejoras del sistema tributario que alcanza a la IAB, con intención de disminuir la carga tributaria y simplificar los regímenes correspondientes.");
                dpto4.setEstaActivo(true);
                Departamento dpto5 = new Departamento();
                dpto5.setNombre("Asuntos Laborales");
                dpto5.setObjetivo("Su objetivo es monitorear y analizar los temas legales y de política laboral, como así también las cuestiones relativas a la seguridad social de las empresas representadas por sus Cámaras.");
                dpto5.setEstaActivo(true);
                Departamento dpto6 = new Departamento();
                dpto6.setNombre("Comercio Exterior");
                dpto6.setObjetivo("Su objetivo es tener activa participación, seguimiento y monitoreo de las negociaciones económicas internacionales, en las que se encuentra involucrado el país, ya sea individualmente o como parte del MERCOSUR. Asimismo, atender la agenda de la política de internacionalización de los sectores de la IAB.");
                dpto6.setEstaActivo(true);
                Departamento dpto7 = new Departamento();
                dpto7.setNombre("Sustentabilidad y Política Ambiental");
                dpto7.setObjetivo("Su principal objetivo es el de atender todos aquellos temas que hacen al estudio de los planes, alternativas o proyectos de ley que tengan que ver con la gestión ambiental.");
                dpto7.setEstaActivo(true);

                //SET PUESTOS

                Puesto p1 = new Puesto("PRESIDENTE");
                Puesto p2 = new Puesto("VICEPRESIDENTE");
                Puesto p3 = new Puesto("JEFE DE DEPARTAMENTO");
                Puesto p4 = new Puesto("SECRETARIO");
                Puesto p5 = new Puesto("ASESOR EXTERNO");
                Puesto p6 = new Puesto("ASISTENTE DE DEPARTAMENTO");

                puestoRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6));

                // Autoridades del departamento 1
                Autoridad dpto1Autoridad1 = new Autoridad();
                dpto1Autoridad1.setUsuario(new Usuario("Marcelo Ceretti"));
                dpto1Autoridad1.setPuesto(puestoRepository.findByNombre("PRESIDENTE").get());

                Autoridad dpto1Autoridad2 = new Autoridad();
                dpto1Autoridad2.setUsuario(new Usuario("Alejandro Bestani"));
                dpto1Autoridad2.setPuesto(puestoRepository.findByNombre("VICEPRESIDENTE").get());

                Autoridad dpto1Autoridad3 = new Autoridad();
                dpto1Autoridad3.setUsuario(new Usuario("Guillermo Assumma"));
                dpto1Autoridad3.setPuesto(puestoRepository.findByNombre("VICEPRESIDENTE").get());

                Autoridad dpto1Autoridad4 = new Autoridad();
                dpto1Autoridad4.setUsuario(new Usuario("Paulina Campion_1"));
                dpto1Autoridad4.setPuesto(puestoRepository.findByNombre("JEFE DE DEPARTAMENTO").get());

                List<Autoridad> autoridades = new ArrayList<>();
                autoridades.add(dpto1Autoridad1);
                autoridades.add(dpto1Autoridad2);
                autoridades.add(dpto1Autoridad3);
                autoridades.add(dpto1Autoridad4);

                dpto1.setAutoridades(autoridades);

                // Autoridades del departamento 2
                Autoridad dpto2Autoridad1 = new Autoridad();
                dpto2Autoridad1.setUsuario(new Usuario("Natalio Tassara"));
                dpto2Autoridad1.setPuesto(puestoRepository.findByNombre("PRESIDENTE").get());

                Autoridad dpto2Autoridad2 = new Autoridad();
                dpto2Autoridad2.setUsuario(new Usuario("María Rosa Rabanal"));
                dpto2Autoridad2.setPuesto(puestoRepository.findByNombre("VICEPRESIDENTE").get());

                Autoridad dpto2Autoridad3 = new Autoridad();
                dpto2Autoridad3.setUsuario(new Usuario("Nora Engo"));
                dpto2Autoridad3.setPuesto(puestoRepository.findByNombre("SECRETARIO").get());

                Autoridad dpto2Autoridad4 = new Autoridad();
                dpto2Autoridad4.setUsuario(new Usuario("Abril Drach"));
                dpto2Autoridad4.setPuesto(puestoRepository.findByNombre("JEFE DE DEPARTAMENTO").get());

                List<Autoridad> autoridadesDpto2 = new ArrayList<>();
                autoridadesDpto2.add(dpto2Autoridad1);
                autoridadesDpto2.add(dpto2Autoridad2);
                autoridadesDpto2.add(dpto2Autoridad3);
                autoridadesDpto2.add(dpto2Autoridad4);

                dpto2.setAutoridades(autoridadesDpto2);

                // Autoridades del departamento 3
                Autoridad dpto3Autoridad1 = new Autoridad();
                dpto3Autoridad1.setUsuario(new Usuario("Diego Hekimian"));
                dpto3Autoridad1.setPuesto(puestoRepository.findByNombre("PRESIDENTE").get());

                Autoridad dpto3Autoridad2 = new Autoridad();
                dpto3Autoridad2.setUsuario(new Usuario("Cecilia Rena"));
                dpto3Autoridad2.setPuesto(puestoRepository.findByNombre("VICEPRESIDENTE").get());

                Autoridad dpto3Autoridad3 = new Autoridad();
                dpto3Autoridad3.setUsuario(new Usuario("Leandro Bel"));
                dpto3Autoridad3.setPuesto(puestoRepository.findByNombre("SECRETARIO").get());

                Autoridad dpto3Autoridad4 = new Autoridad();
                dpto3Autoridad4.setUsuario(new Usuario("Juliana Cortez Danese"));
                dpto3Autoridad4.setPuesto(puestoRepository.findByNombre("JEFE DE DEPARTAMENTO").get());

                List<Autoridad> autoridadesDpto3 = new ArrayList<>();
                autoridadesDpto3.add(dpto3Autoridad1);
                autoridadesDpto3.add(dpto3Autoridad2);
                autoridadesDpto3.add(dpto3Autoridad3);
                autoridadesDpto3.add(dpto3Autoridad4);

                dpto3.setAutoridades(autoridadesDpto3);

                // Autoridades del departamento 4
                Autoridad dpto4Autoridad1 = new Autoridad();
                dpto4Autoridad1.setUsuario(new Usuario("Fernando Guntern"));
                dpto4Autoridad1.setPuesto(puestoRepository.findByNombre("PRESIDENTE").get());

                Autoridad dpto4Autoridad2 = new Autoridad();
                dpto4Autoridad2.setUsuario(new Usuario("Mariela Compagnucci"));
                dpto4Autoridad2.setPuesto(puestoRepository.findByNombre("VICEPRESIDENTE").get());

                Autoridad dpto4Autoridad3 = new Autoridad();
                dpto4Autoridad3.setUsuario(new Usuario("María Silvana Gatti"));
                dpto4Autoridad3.setPuesto(puestoRepository.findByNombre("SECRETARIO").get());

                Autoridad dpto4Autoridad4 = new Autoridad();
                dpto4Autoridad4.setUsuario(new Usuario("Paulina Campion_2"));
                dpto4Autoridad4.setPuesto(puestoRepository.findByNombre("JEFE DE DEPARTAMENTO").get());

                Autoridad dpto4Autoridad5 = new Autoridad();
                dpto4Autoridad5.setUsuario(new Usuario("Alberto Serrano"));
                dpto4Autoridad5.setPuesto(puestoRepository.findByNombre("ASESOR EXTERNO").get());

                List<Autoridad> autoridadesDpto4 = new ArrayList<>();
                autoridadesDpto4.add(dpto4Autoridad1);
                autoridadesDpto4.add(dpto4Autoridad2);
                autoridadesDpto4.add(dpto4Autoridad3);
                autoridadesDpto4.add(dpto4Autoridad4);
                autoridadesDpto4.add(dpto4Autoridad5);

                dpto4.setAutoridades(autoridadesDpto4);

                // Autoridades del departamento 5
                Autoridad dpto5Autoridad1 = new Autoridad();
                dpto5Autoridad1.setUsuario(new Usuario("Juan Carlos Mariani"));
                dpto5Autoridad1.setPuesto(puestoRepository.findByNombre("PRESIDENTE").get());

                Autoridad dpto5Autoridad2 = new Autoridad();
                dpto5Autoridad2.setUsuario(new Usuario("Eduardo Viñales"));
                dpto5Autoridad2.setPuesto(puestoRepository.findByNombre("VICEPRESIDENTE").get());

                Autoridad dpto5Autoridad3 = new Autoridad();
                dpto5Autoridad3.setUsuario(new Usuario("Eduardo Schiel"));
                dpto5Autoridad3.setPuesto(puestoRepository.findByNombre("SECRETARIO").get());

                Autoridad dpto5Autoridad4 = new Autoridad();
                dpto5Autoridad4.setUsuario(new Usuario("Gabriela Petray"));
                dpto5Autoridad4.setPuesto(puestoRepository.findByNombre("ASISTENTE DE DEPARTAMENTO").get());

                List<Autoridad> autoridadesDpto5 = new ArrayList<>();
                autoridadesDpto5.add(dpto5Autoridad1);
                autoridadesDpto5.add(dpto5Autoridad2);
                autoridadesDpto5.add(dpto5Autoridad3);
                autoridadesDpto5.add(dpto5Autoridad4);

                dpto5.setAutoridades(autoridadesDpto5);

                // Autoridades del departamento 6
                Autoridad dpto6Autoridad1 = new Autoridad();
                dpto6Autoridad1.setUsuario(new Usuario("Osvaldo D´Imperio"));
                dpto6Autoridad1.setPuesto(puestoRepository.findByNombre("PRESIDENTE").get());

                Autoridad dpto6Autoridad2 = new Autoridad();
                dpto6Autoridad2.setUsuario(new Usuario("Jorge Amigo"));
                dpto6Autoridad2.setPuesto(puestoRepository.findByNombre("SECRETARIO").get());

                Autoridad dpto6Autoridad3 = new Autoridad();
                dpto6Autoridad3.setUsuario(new Usuario("Paulina Campion_3"));
                dpto6Autoridad3.setPuesto(puestoRepository.findByNombre("JEFE DE DEPARTAMENTO").get());

                Autoridad dpto6Autoridad4 = new Autoridad();
                dpto6Autoridad4.setUsuario(new Usuario("Nicolas Ferinovic"));
                dpto6Autoridad4.setPuesto(puestoRepository.findByNombre("ASISTENTE DE DEPARTAMENTO").get());

                List<Autoridad> autoridadesDpto6 = new ArrayList<>();
                autoridadesDpto6.add(dpto6Autoridad1);
                autoridadesDpto6.add(dpto6Autoridad2);
                autoridadesDpto6.add(dpto6Autoridad3);
                autoridadesDpto6.add(dpto6Autoridad4);

                dpto6.setAutoridades(autoridadesDpto6);

                // Autoridades del departamento 7
                Autoridad dpto7Autoridad1 = new Autoridad();
                dpto7Autoridad1.setUsuario(new Usuario("Jordana Carvallo"));
                dpto7Autoridad1.setPuesto(puestoRepository.findByNombre("PRESIDENTE").get());

                Autoridad dpto7Autoridad2 = new Autoridad();
                dpto7Autoridad2.setUsuario(new Usuario("Juliana Cortez Danese"));
                dpto7Autoridad2.setPuesto(puestoRepository.findByNombre("JEFE DE DEPARTAMENTO").get());

                List<Autoridad> autoridadesDpto7 = new ArrayList<>();
                autoridadesDpto7.add(dpto7Autoridad1);
                autoridadesDpto7.add(dpto7Autoridad2);

                dpto7.setAutoridades(autoridadesDpto7);


                departamentoRepository.save(dpto1);
                departamentoRepository.save(dpto2);
                departamentoRepository.save(dpto3);
                departamentoRepository.save(dpto4);
                departamentoRepository.save(dpto5);
                departamentoRepository.save(dpto6);
                departamentoRepository.save(dpto7);

            }

            //SET UBICACION
            Ubicacion ubicacion1 = new Ubicacion("Av. Corrientes 650", "5", "San Nicolas", "Ciudad Autonoma de Buenos Aires");
            Ubicacion ubicacionGuardada1 = ubicacionService.agregarUbicacion(ubicacion1);

            Ubicacion ubicacion2 = new Ubicacion("Av. 53 1025", "0", "La Plata", "Provincia de Buenos Aires");
            Ubicacion ubicacionGuardada2 = ubicacionService.agregarUbicacion(ubicacion2);

            Ubicacion ubicacion3 = new Ubicacion("Blvd. San Juan 75", "15", "Córdoba", "Córdoba");
            Ubicacion ubicacionGuardada3 = ubicacionService.agregarUbicacion(ubicacion3);

            //SET ESTADO EVENTO
            Optional<EstadoEvento> estadoEventoValidacion = estadoEventoRepository.findById(1L);
            if (!estadoEventoValidacion.isPresent()) {
                EstadoEvento estadoEvento1 = new EstadoEvento("Activo");
                EstadoEvento estadoEvento2 = new EstadoEvento("Suspendido");
                EstadoEvento estadoEvento3 = new EstadoEvento("Cancelado");
                EstadoEvento estadoEvento4 = new EstadoEvento("Finalizado");

                estadoEventoRepository.saveAll(List.of(estadoEvento1, estadoEvento2, estadoEvento3, estadoEvento4));
            }

            //SET PLATAFORMAS
            Optional<PlataformaEvento> plataformaEventoValidacion = plataformaRepository.findById(1L);
            if(!plataformaEventoValidacion.isPresent()) {
                PlataformaEvento plataformaEvento1 = new PlataformaEvento("Meet");
                PlataformaEvento plataformaEvento2 = new PlataformaEvento("Teams");
                PlataformaEvento plataformaEvento3 = new PlataformaEvento("Zoom");
                PlataformaEvento plataformaEvento4 = new PlataformaEvento("Slack");
                PlataformaEvento plataformaEvento5 = new PlataformaEvento("Discord");

                plataformaRepository.saveAll(List.of(plataformaEvento1,plataformaEvento2,plataformaEvento3,plataformaEvento4,plataformaEvento5));
            }

            //SET EVENTO Y PARTICIPANTES
            appService.crearEventosYParticipantes();

            //SET LUGAR
            Optional<Lugar> LugarValidacion = lugarRepository.findById(1L);
            if(!LugarValidacion.isPresent()){
                for(int i = 1; i <= 5; i++) {
                    Lugar lugar = new Lugar();
                    lugar.setNombre("SALA " + i);
                    lugarRepository.save(lugar);
                }
            }

            // SET RECURSO
            Optional<Recurso> RecursoValidacion = recursoRepository.findById(1L);
            if(!RecursoValidacion.isPresent()){
                Recurso recurso1 = new Recurso();
                recurso1.setNombre("Proyector");

                Recurso recurso2 = new Recurso();
                recurso2.setNombre("Pizarra");

                Recurso recurso3 = new Recurso();
                recurso3.setNombre("Netbook");

                Recurso recurso4 = new Recurso();
                recurso4.setNombre("Marcadores");

                Recurso recurso5 = new Recurso();
                recurso5.setNombre("Pizzarrón Inteligente");

                Recurso recurso6 = new Recurso();
                recurso6.setNombre("Pantalla");

                recursoRepository.save(recurso1);
                recursoRepository.save(recurso2);
                recursoRepository.save(recurso3);
                recursoRepository.save(recurso4);
                recursoRepository.save(recurso5);
                recursoRepository.save(recurso6);
            }

            // SET ESTADO RESERVA
            Optional<EstadoReserva> estadoReservaValidacion = estadoReservaRepository.findById(1L);
            if(!estadoReservaValidacion.isPresent()){
                EstadoReserva estado1 = new EstadoReserva();
                estado1.setNombre("Aceptado");

                EstadoReserva estado2 = new EstadoReserva();
                estado2.setNombre("Pendiente");

                EstadoReserva estado3 = new EstadoReserva();
                estado3.setNombre("Rechazado");

                EstadoReserva estado4 = new EstadoReserva();
                estado4.setNombre("Observado");

                estadoReservaRepository.save(estado1);
                estadoReservaRepository.save(estado2);
                estadoReservaRepository.save(estado3);
                estadoReservaRepository.save(estado4);
            }


            // SET RESERVA
            Optional<Reserva> ReservaValidacion = reservaRepository.findById(1L);
            if(!ReservaValidacion.isPresent()){
                for(int i = 1; i< 10; i++) {
                    Reserva reserva = new Reserva();

                    reserva.setLugar(lugarRepository.getReferenceById((long)(i % 5) + 1));
                    reserva.setEstadoReserva(estadoReservaRepository.getReferenceById((long)(i % 3) + 1));
                    reserva.setDepartamento(departamentoRepository.getReferenceById((long)(i % 7) + 1));
                    reserva.setNombre("Nombre Reservdor " + i);
                    reserva.setEmail("email" + i + "@gmail.com");
                    reserva.setDescripcion("Descripcion de la reserva" + i);

                    Recurso recursos = recursoRepository.getReferenceById(2L);
                    List<Recurso> listaRecursos = new ArrayList<>();
                    listaRecursos.add(recursos);
                    reserva.setRecursos(listaRecursos);

//                    String fechaTexto = "2018-04-21";
//                    String horaInicioTexto = "03:00:00";
//                    String horaFinTexto = "04:30:00";
//
//                    LocalDate fecha = LocalDate.parse(fechaTexto);
//                    reserva.setFecha(fecha);
//                    LocalTime horaInicio = LocalTime.parse(horaInicioTexto);
//                    reserva.setHoraInicio(horaInicio);
//                    LocalTime horaFin = LocalTime.parse(horaFinTexto);
//                    reserva.setHoraFin(horaFin);

                    reserva.setFecha(LocalDate.now());
                    reserva.setHoraInicio(LocalTime.now());
                    reserva.setHoraFin(LocalTime.now().plusHours(i % 4+1).plusMinutes(i+30));

                    reservaRepository.save(reserva);
                }
            }

        };

    }



}
