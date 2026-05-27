Prueba tecnica Android

Aplicación Android desarrollada en Kotlin para consumo de APIs REST,
persistencia local con Room y arquitectura MVVM.

Características TécnicasLenguaje: Kotlin.  Arquitectura: MVVM (Model-View-ViewModel).Principios de Desarrollo: SOLID y Código Documentado.  Persistencia: SQLite mediante Room Database.  Red: Retrofit con manejo de excepciones Try/Catch.  Inyección de Dependencias: Hilt.

Estructura del Proyecto:

El código está organizado en capas para garantizar la escalabilidad y facilidad de mantenimiento:  Capa de Seguridad: Implementa la validación de versiones cruzando la versión local con el API y gestiona la autenticación de usuarios.  Capa de Datos: Maneja la base de datos SQLite local para almacenar información del usuario y esquemas de tablas.  Capa de Presentación: Contiene las 3 pantallas requeridas (Home, Tablas, Localidades).  

Requerimientos Implementados1. Capa de Seguridad y Control   Validación de Versión: Se consume el endpoint de parámetros para comparar la versión local vs API.  Si la versión local < API: Se notifica actualización disponible.  Si la versión local > API: Se indica mediante un mensaje.  Autenticación: Consumo de AuthenticaUsuarioApp con headers y body específicos.  Manejo de Errores: Control de HTTP Response Codes diferentes a 200 con mensajes de alerta.  2. Capa de Datos (SQLite)   Almacenamiento de Usuario: Si el login es exitoso (200 OK), se persiste en base de datos: Usuario, Identificación y Nombre.  Sincronización: Se consume el endpoint de Esquema y se almacenan las tablas retornadas en la base de datos local.  3. Capa de Presentación (UI)   Pantalla HOME: Muestra los datos del usuario logueado (Nombre, ID, Usuario) obtenidos de la DB local.  Pantalla TABLAS: Listado de la información de tablas obtenida en el proceso de sincronización.  Pantalla LOCALIDADES: Consumo en tiempo real del endpoint de localidades, exhibiendo AbreviacionCiudad y NombreCompleto.   Instrucciones de EjecuciónClonar el repositorio.Abrir en Android Studio.Sincronizar el proyecto con Gradle.

Documentación y Buenas PrácticasSe utilizó Try/Catch en todos los consumos de API para prevenir cierres inesperados.  El código incluye comentarios explicativos en los métodos clave siguiendo los principios SOLID.  Manejo de errores detallado para fallas de red o respuestas no exitosas del servidor.
