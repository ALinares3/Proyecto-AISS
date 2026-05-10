# **Proyecto AISS**

El proyecto trata de un sistema de agregación de contenido multimedia desde múltiples plataformas de streaming

Este repositorio contiene tres aplicaciones Java Spring Boot:

-----------------------------------------------------------------------------

## Arquitectura General

### 1. **DailyMotionMiner** & **PeertubeMiner**

- dailymotionMiner
    Extrae datos desde la API de Dailymotion.
    Convierte los datos a los modelos de VideoMiner.
    Envía los datos al servicio videominer.

- peertubeMiner
    Extrae datos desde la API de PeerTube.
    Convierte los datos a los modelos de VideoMiner.
    Envía los datos al servicio videominer.

**Estructura:**
#### Controller
- **ChannelController**: Gestiona peticiones HTTP hacia las APIs externas (GET) y hacia VideoMiner (POST)
- Integración bidireccional con servicios
- ## Modelos
- **Modelos de APIs**: POJOs que representan estructura nativa de Dailymotion/PeerTube
- **Modelos de VideoMiner**: POJOs importados del sistema central para persistencia

# Application
- Punto de entrada de la aplicación Spring Boot
- Configuración automática de dependencias

### 2. **VideoMiner** (API Central)

API centralizada que recibe y almacena datos de los miners en base de datos H2.

#### Controller
- **ChannelController**: `/videominer/channels` - CRUD completo de canales
- **VideoController**: `/videominer/videos` - CRUD de videos
- ## Excepciones
- Exception Handler global configurado
- Excepciones 404 personalizadas:
  ## Modelos
Entidades de la base de datos con relaciones JPA:
- **Channel**: Contiene múltiples Videos (OneToMany)
- ## Repositorios
Interfaces que extienden `JpaRepository` para acceso a datos:
- `ChannelRepository`
- `VideoRepository`
- `CommentRepository`
- `CaptionRepository`
---

-----------------------------------------------------------------------------

## Cómo ejecutar

    ### En Windows
- dailymotionMiner:
cd proyectoAISS\dailymotionMiner
.\mvnw.cmd spring-boot:run
- peertubeMiner:
cd proyectoAISS\peertubeMiner
.\mvnw.cmd spring-boot:run
- VideoMinerTemplate26-main:
cd proyectoAISS\VideoMinerTemplate26-main
.\mvnw.cmd spring-boot:run

    ### En Linux/macOs
- dailymotionMiner:
cd proyectoAISS/dailymotionMiner
./mvnw spring-boot:run
- peertubeMiner:
cd proyectoAISS/peertubeMiner
./mvnw spring-boot:run
- VideoMinerTemplate26-main:
cd proyectoAISS/VideoMinerTemplate26-main
./mvnw spring-boot:run
