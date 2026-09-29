# Sistema de Gestión de Festival de Cine

Trabajo Práctico Final — Programación Orientada a Objetos (UADE)
Alumna: Milano, Carina Victoria — LU: 1227609

Aplicación de escritorio en Java (Swing) para gestionar un Festival Internacional
de Cine: ediciones, secciones y premios, películas, funciones presenciales y por
streaming, venta de entradas, espectadores y evaluaciones del jurado con cálculo
de ganadores por sección.

## Requisitos

- JDK 17 o superior (el proyecto está configurado en IntelliJ con JDK 25 / Temurin,
  pero compila y corre sin problemas desde JDK 17).
- No usa dependencias externas ni build tool (Maven/Gradle): es un proyecto Java
  plano, pensado para abrirse directamente en IntelliJ IDEA.

## Cómo ejecutarlo desde IntelliJ IDEA (recomendado)

1. Clonar el repositorio y abrir la carpeta `FestivalCine` como proyecto en
   IntelliJ IDEA (`File → Open`).
2. Verificar que el SDK del proyecto sea JDK 17+ (`File → Project Structure → Project`).
3. Ejecutar la clase `app.Main` (clic derecho → `Run 'Main.main()'`).
4. Al iniciar, el sistema carga automáticamente los datos desde los archivos
   `.txt` de la raíz del proyecto y precarga ediciones/funciones de ejemplo, y
   luego se abre la ventana principal (Swing).
5. Al cerrar la ventana, el sistema pregunta si se desean guardar los cambios
   en los archivos `.txt`.

## Cómo ejecutarlo por línea de comandos

Parado en la carpeta raíz del proyecto (`FestivalCine`, donde están los `.txt`
de datos):

```bash
# Compilar todas las fuentes a la carpeta out/
javac -d out -encoding UTF-8 $(find src -name "*.java")

# Copiar el recurso gráfico (la imagen de la ventana principal)
cp resources/cine.jpg out/

# Ejecutar
java -cp out app.Main
```

En Windows con PowerShell:

```powershell
Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName } > sources.txt
javac -d out -encoding UTF-8 "@sources.txt"
Copy-Item resources\cine.jpg out\
java -cp out app.Main
```

> Importante: los archivos `.txt` (`salas.txt`, `directores.txt`, `actores.txt`,
> `jurados.txt`, `espectadores.txt`, `peliculas.txt`, `evaluaciones.txt`) deben
> quedar en el **directorio de trabajo** desde el que se ejecuta `java`, no
> dentro de `out/`. Por eso se recomienda correr el `java -cp out app.Main`
> desde la raíz del proyecto.

## Estructura del proyecto

```
src/
  modelo/         → Entidades del dominio (Festival, Pelicula, Funcion, Entrada, etc.)
  excepciones/    → Excepciones personalizadas
  servicios/      → Lógica de negocio (FestivalServicio, PeliculaServicio, EntradaServicio)
  persistencia/   → Lectura/escritura de los archivos .txt
  ui/             → Interfaz gráfica Swing (ventana principal y paneles por módulo)
  app/            → Main.java (punto de entrada)
```

Más detalle de diseño, decisiones y alcance en [README.txt](README.txt).

## Datos de ejemplo

Al arrancar, `InicializadorDatos` precarga 3 ediciones del festival, sus
secciones y premios, asociaciones película-sección, y ~20 funciones (presenciales
y streaming) con algunas entradas ya vendidas. Las fechas de las funciones se
calculan en relación a la fecha del día en que se ejecuta el programa, así que
siempre se generan a futuro sin importar cuándo se corra el proyecto.
