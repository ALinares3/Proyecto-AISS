# Proyecto-AISS
## PeertubeMiner y DailyMotionMiner
Son los proyectos que realizan consultas a las respectivas APIs y envían los resultados a VideoMiner. Cuentan con la siguiente estructura:
### Controller
Existe un controlador para los canales (ChannelController), que se encarga de enviar las peticiones HTTP, un GET a las APIs y un POST a VideoMiner mediante los servicios existentes.
Por ahora funcionan con las clases antiguas, hay que actualizarlos (y probablemente habrá que importar Transformer ya que los services funcionan con las clases de las APIs y el controller con los de VM).
### Modelos
Los modelos se dividen en 2 tipos, los obtenidos por las APIs y los de VideoMiner, que serán importados por el resto de clases.
Los de VM están hechos si no me he equivocado, los otros hay que hacer POJOs y por lo menos yo me están rayando obtenerlo bien.
### Servicios
Los servicios se encargan de la "lógica" de las peticiones.
Apoyan a controller, solo deben tener GET y POST. También un service por objeto de VM si hay.
### Transformador
El transformador convierte los datos de las APIs a objetos de la BD de VideoMiner.
Este va a ser gracioso de hacer, tendrá un montón de objetos en el constructor y un método void para cambiarlos, supongo.

OJO: Hay que convertir el tipo de algunos atributos de Integer a String

### Application
En el paquete principal existe esta clase que es la que se ejecuta.

## Videominer
Es una API que recibe datos de los otros proyectos y los envía a una base de datos H2. Se divide en las siguientes partes:

### Controller
Existen varios controladores con las consultas CRUD requeridas y con gestión de excepciones.
Creo que solo quedan corregir detalles aquí.
### Excepciones
Existe un gestor global de excepciones más una excepción 404 por objeto de la base de datos.
Hecho
### Modelos
Los modelos de la BD con sus relaciones entre ellos.
En sí estan bien, lo que habría que hacer es guardar los IDs dados como otra variable y generar los IDs automáticamente como Longs.
### Repositorios
La base de datos.
Hecha.

### Anotaciones
Aquí está más o menos las cosas que hay que ir haciendo, las dejo en el README que se vea claro, también hay cositas en los issues, se ven mejor si vais a projects. Creo que se puede subir el github en la entrega, aparte del zip, por lo que el README se puede hacer más bonito o lo que sea, pero no es importante.

### Pruebas Postman
Ahora mismo las pruebas de channel funcionan, se crea el canal con los datos(videos,captions,comments y user) pero los datos no se suben. Por ejemplo, el canal de prueba tiene 8 videos, pero al hacer la consulta de videos da 404. Hay que hacer que los datos se suban a la base de datos, creo que es con el POST de channel que tendría que llamar al de videos o algo por el estilo.
