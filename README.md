# Juegos Favoritos

App Android nativa para gestionar una colección de videojuegos, desarrollada en Kotlin con Jetpack Compose durante mis prácticas en ALTEN. Los datos se guardan en una base de datos local con Room.

## Funcionalidades

- **Listado de juegos** con portada, estudio, plataformas y clasificación PEGI, cada una con su icono.
- **Filtro por plataforma** y **ordenación** por nombre o por ID.
- **Añadir juegos** con un formulario: nombre, estudio, fecha de lanzamiento, URL de la portada, plataformas y PEGI (elegido en un diálogo con los iconos oficiales).
- **Eliminar juegos** con diálogo de confirmación y aviso en pantalla.
- **Juegos por estudio:** al pulsar el nombre de un estudio se abren todos sus juegos.
- **Perfil** con las preferencias del usuario (plataforma filtrada y tipo de orden), que se mantienen al cerrar la app.

## Tecnologías

| Área | Herramientas |
| --- | --- |
| Lenguaje | Kotlin |
| Interfaz | Jetpack Compose, Material 3 |
| Arquitectura | MVVM (ViewModel + estado de pantalla), repositorio |
| Base de datos | Room (entidades, DAO y conversores) |
| Preferencias | DataStore |
| Navegación | Navigation 3 |
| Imágenes | Coil |
| Asincronía | Kotlin Coroutines y Flow |

## Arquitectura

Los datos de la base de datos (`Dbo`) se convierten en modelos de negocio (`Bo`) mediante mappers, y la interfaz solo trabaja con estos últimos. El DAO devuelve `Flow`, así que el listado se actualiza solo al añadir o borrar un juego.

    app/src/main/java/com/example/juegosfavoritos_practica5/
    ├── data/
    │   ├── domain/        Modelos de negocio (GameBo, StudioBo) y constantes
    │   ├── entity/        Entidades de Room (GameDbo, StudioDbo)
    │   ├── local/         Base de datos, DAO, conversores y preferencias (DataStore)
    │   └── mapper/        Conversión entre entidades y modelos
    ├── domain/repository/ GameRepository
    └── ui/
        ├── common/        Barra superior, barra inferior y estructura común
        ├── game/          Listado, filtro, PEGI, borrado y formulario
        ├── studio/        Juegos de un estudio
        ├── preference/    Perfil y preferencias
        ├── navigation/    Rutas y navegación
        └── theme/

## Cómo ejecutarla

**Requisitos:** Android Studio reciente, JDK 21 y un dispositivo o emulador con Android 8.0 (API 26) o superior.

1. Clona el repositorio y ábrelo en Android Studio.
2. Espera a que Gradle sincronice las dependencias.
3. Ejecuta la app con el botón **Run**.

## Autor

**Alejandro Gadea Vicente**
[Portfolio](https://alejandro-gadea.vercel.app) · [LinkedIn](https://www.linkedin.com/in/alejandro-gadea-vicente-6146b3389) · [GitHub](https://github.com/alexga22604)
