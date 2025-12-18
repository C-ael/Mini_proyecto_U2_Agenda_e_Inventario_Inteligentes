# Mini Proyecto U2 – Agenda e Inventario Inteligentes  
### *Estructuras de Datos – Unidad 2*

---

# Tabla de Contenidos
- [Objetivos del Proyecto](#objetivos-del-proyecto)
- [Requisitos de Software](#requisitos-de-software)
- [Cómo ejecutar el proyecto](#cómo-ejecutar-el-proyecto)
- [Decisiones de diseño](#decisiones-de-diseño)
- [Precondiciones de los algoritmos](#precondiciones-de-los-algoritmos)
- [Casos borde](#casos-borde)
- [Datasets utilizados](#datasets-utilizados)
- [Buenas prácticas implementadas](#buenas-prácticas-implementadas)

---

## Objetivos del Proyecto

- Implementar algoritmos de **ordenamiento** y **búsqueda** sobre distintas estructuras de datos.
- Analizar el rendimiento de los algoritmos mediante **múltiples ejecuciones y medición de tiempos**.
- Aplicar **búsqueda binaria** en arreglos y **búsqueda secuencial** en listas simplemente enlazadas.
- Justificar la **elección del algoritmo** según el tipo y estado de los datos.

---

## Requisitos de Software

- **Java Development Kit (JDK) 17 o superior**
- **IDE recomendado:**
  - IntelliJ IDEA Community Edition
  - Visual Studio Code con *Extension Pack for Java*

---

## Cómo ejecutar el proyecto
1. Clonar el repositorio

**git clone** https://github.com/C-ael/Mini_proyecto_U2_Agenda_e_Inventario_Inteligentes.git

2. Abrir el proyecto en el IDE.
3. Verificar que los archivos `.csv` estén en la carpeta `resources`.
4. Ejecutar la clase:


4. Usar el **menú interactivo por consola** para acceder a:
- Agenda de citas
- Inventario
- Pacientes

---

## Decisiones de diseño

- Se separó la lógica en **módulos independientes**:
- Agenda (citas)
- Inventario
- Pacientes
- Los algoritmos de ordenamiento y búsqueda se implementaron de forma **genérica** usando `Comparable<T>`.
- El rendimiento se mide ejecutando cada algoritmo **10 veces**, descartando las primeras **3 ejecuciones** para evitar efectos de calentamiento de la JVM.
- Se utiliza la **mediana** como valor representativo del tiempo de ejecución.
- La lista de pacientes se implementa como **lista simplemente enlazada (SLL)**, por lo que no se aplica búsqueda binaria en ella.

---

## Precondiciones de los algoritmos

### Búsqueda Binaria
- El arreglo debe estar **ordenado ascendentemente**.
- Se aplica únicamente a:
- Citas (ordenadas por fecha y hora).
- Inventario (ordenado por stock).

### Búsqueda Secuencial
- No requiere orden previo.
- Se utiliza en arreglos y en la lista enlazada de pacientes.

### Ordenamiento
- Los elementos deben implementar la interfaz `Comparable`.

---

## Casos borde

- **Arreglo vacío**: los métodos retornan sin error y no realizan búsquedas.
- **Elemento no encontrado**: los algoritmos retornan `-1` o `null`.
- **Valores repetidos**:
- En pacientes, se permite encontrar el primero, el último o todos según el criterio.
- **Dataset desordenado**:
- La búsqueda secuencial funciona correctamente.
- La búsqueda binaria puede fallar si no se cumple la precondición de orden.
- **Entrada inválida por consola**:
- El sistema valida la entrada para evitar errores de ejecución.

---

## Datasets utilizados

- `citas_100.csv`
- `citas_100_casi_ordenadas.csv`
- `inventario_500_inverso.csv`
- `pacientes_500.csv`

Estos datasets permiten evaluar:
- Datos ordenados
- Datos inversos
- Datos casi ordenados
- Datos con valores repetidos

---

## Buenas prácticas implementadas

- Código modular y reutilizable.
- Separación clara de responsabilidades.
- Uso de genéricos.
- Medición de tiempos encapsulada en utilidades.
- Manejo de errores de entrada por consola.
- Estructura de paquetes clara y coherente.

---
