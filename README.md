# Mini Proyecto U2 – Agenda e Inventario Inteligentes  
### *Estructuras de Datos – Unidad 2*

---

# Tabla de Contenidos
- [Objetivos del Proyecto](#objetivos-del-proyecto)
- [Requisitos de Software](#requisitos-de-software)
- [Como ejecutar el proyecto](#como-ejecutar-el-proyecto)
- [Decisiones de disenio](#decisiones-de-disenio)
- [Precondiciones de los algoritmos](#precondiciones-de-los-algoritmos)
- [Casos borde](#casos-borde)
- [Datasets utilizados](#datasets-utilizados)
- [Buenas practicas implementadas](#buenas-practicas-implementadas)

---

## Objetivos del Proyecto

- Implementar algoritmos de **ordenamiento** y **búsqueda** sobre distintas estructuras de datos.
- Analizar el rendimiento de los algoritmos mediante **múltiples ejecuciones y medición de tiempos**.
- Aplicar **búsqueda binaria** en arreglos y **búsqueda secuencial** en listas simplemente enlazadas.
- Justificar la **elección del algoritmo** según el tipo y estado de los datos utilizados.

---

## Requisitos de Software

- **Java Development Kit (JDK) 21 LTS**
- **Gestor de dependencias:** Maven
- **IDE recomendado:**
  - IntelliJ IDEA Community Edition
  - Visual Studio Code con *Extension Pack for Java*

---

## Como ejecutar el proyecto

1. Clonar el repositorio usando Git:

git clone https://github.com/C-ael/Mini_proyecto_U2_Agenda_e_Inventario_Inteligentes.git

3. Abrir el proyecto en el IDE como proyecto **Maven**.
4. Verificar que los archivos `.csv` estén ubicados en `src/main/resources`.
5. Ejecutar la clase:

ed.u2.app.Main

6. Usar el **menú interactivo por consola** para acceder a:
- Agenda de citas
- Inventario
- Pacientes

---

## Decisiones de disenio

- El proyecto sigue la **estructura estándar de Maven**, separando código fuente y recursos.
- Se dividió la lógica en **paquetes independientes**:
  - `app`: clases ejecutables del proyecto
  - `list`: lista simplemente enlazada de pacientes
  - `model`: modelos de datos
  - `search`: algoritmos de búsqueda
  - `sorting`: algoritmos de ordenamiento instrumentados
  - `util`: clases de utilidades (carga CSV y medición de tiempo)
  
- Los algoritmos de ordenamiento trabajan sobre tipos que implementan `Comparable<T>`.
- El rendimiento se mide ejecutando cada algoritmo **10 veces**, descartando las **3 primeras ejecuciones**.
- Se utiliza la **mediana** como valor representativo del tiempo de ejecución.
- La lista de pacientes se implementa como **lista simplemente enlazada (SLL)**, por lo que no se aplica búsqueda binaria sobre ella.

---

## Precondiciones de los algoritmos

### Búsqueda Binaria
- El arreglo debe estar **ordenado previamente**.
- Se aplica únicamente a:
  - Citas (ordenadas por `fechaHora`).
  - Inventario (ordenado por `stock`).

### Búsqueda Secuencial
- No requiere orden previo.
- Se utiliza en la lista enlazada de pacientes y en validaciones simples.

### Ordenamiento
- Los elementos deben implementar la interfaz `Comparable`.

---

## Casos borde

- **Arreglo vacío:** el sistema valida la longitud antes de ordenar o buscar.
- **Elemento no encontrado:** los métodos retornan `-1` o valores nulos según el caso.
- **Valores duplicados:**
  - En citas e inventario se analizan mediante búsquedas por rango (`lowerBound` / `upperBound`).
  - En pacientes se permite obtener la primera, última o todas las coincidencias.
- **Dataset desordenado:**
  - La búsqueda secuencial funciona correctamente.
  - La búsqueda binaria solo se ejecuta después del ordenamiento.
- **Errores en carga de CSV:**
  - El sistema valida la existencia y el formato de los archivos antes de procesarlos.

---

## Datasets utilizados

- `citas_100.csv`
- `citas_100_casi_ordenadas.csv`
- `inventario_500_inverso.csv`
- `pacientes_500.csv`

Estos datasets permiten evaluar:
- Datos desordenados
- Datos casi ordenados
- Datos inversos
- Presencia de valores repetidos

---

## Buenas practicas implementadas

- Uso de **estructura Maven** estándar.
- Separación clara de responsabilidades por paquete.
- Código fuente en inglés y mensajes de consola en español.
- Instrumentación de algoritmos sin impresiones durante la medición.
- Validación de datos antes de ejecutar búsquedas.
- Código legible, modular y reutilizable.

---
