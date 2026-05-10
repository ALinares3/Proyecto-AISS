# **Proyecto AISS**

Sistema de agregación de contenido multimedia desde Dailymotion y PeerTube. Las apps extraen videos, canales, comentarios y subtítulos, y los guardan en una base de datos centralizada.

Tres aplicaciones que trabajan juntas:
- **DailyMotionMiner** (puerto 8082): Extrae de Dailymotion
- **PeertubeMiner** (puerto 8081): Extrae de PeerTube
- **VideoMiner** (puerto 8083): API central con base de datos H2

---

## Requisitos

- Java 21 (para dailymotionMiner y peertubeMiner)
- Java 17 (para VideoMiner)

---

## Cómo ejecutar

**Las tres aplicaciones deben correr al mismo tiempo.** Abre 3 terminales:

#### Windows
```
# Terminal 1 - VideoMiner (la central)
cd proyectoAISS\VideoMinerTemplate26-main
.\mvnw.cmd spring-boot:run

# Terminal 2 - DailyMotionMiner
cd proyectoAISS\dailymotionMiner
.\mvnw.cmd spring-boot:run

# Terminal 3 - PeertubeMiner
cd proyectoAISS\peertubeMiner
.\mvnw.cmd spring-boot:run
```

#### Linux / macOS
```
# Terminal 1 - VideoMiner
cd proyectoAISS/VideoMinerTemplate26-main
./mvnw spring-boot:run

# Terminal 2 - DailyMotionMiner
cd proyectoAISS/dailymotionMiner
./mvnw spring-boot:run

# Terminal 3 - PeertubeMiner
cd proyectoAISS/peertubeMiner
./mvnw spring-boot:run
```

---

## Guía de uso

### Con Postman

**Todas las pruebas se hacen en VideoMiner (`http://localhost:8083`)**

Endpoints disponibles:
- `GET /videominer/channels` - Ver todos los canales
- `GET /videominer/channels/{id}` - Ver un canal específico
- `POST /videominer/channels` - Crear canal
- `GET /videominer/videos` - Ver todos los videos
- `GET /videominer/videos/{id}` - Ver un video
- `POST /videominer/videos` - Crear video
- `GET /videominer/captions` - Ver subtítulos
- `GET /videominer/comments` - Ver comentarios

### Cómo funciona el flujo

1. **VideoMiner** arranca primero y espera en `localhost:8083`
2. **DailyMotionMiner** y **PeertubeMiner** arrancan después
3. Haces una petición `POST /channels` en uno de los miners (ej: `http://localhost:8082/channels`) para extraer un canal
4. El miner busca en su API externa, convierte los datos y los envía a VideoMiner
5. VideoMiner guarda todo en la base de datos H2
6. Ves los datos en VideoMiner con `GET /videominer/channels`

### Base de datos H2

Consola disponible en: `http://localhost:8083/h2-ui`

---

## Estructura del proyecto

### DailyMotionMiner & PeertubeMiner
- **Controller**: Maneja peticiones GET (buscar en APIs externas) y POST (enviar a VideoMiner)
- **Service**: Lógica para extraer datos y transformarlos
- **Transformer**: Convierte modelos nativos de cada API a modelos de VideoMiner

### VideoMiner
- **Controller**: CRUD de canales, videos, comentarios y subtítulos
- **Repository**: Acceso a base de datos H2
- **Model**: Entidades JPA con relaciones (Canal → Videos → Comentarios y Subtítulos)
