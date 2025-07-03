# Sistema de Tarjeta de Fidelidad Gamificada

Sistema de gestión de programa de fidelidad para una cadena de tiendas de conveniencia desarrollado en **Java 21** con **pruebas unitarias JUnit 5** siguiendo metodología **TDD (Test-Driven Development)**.

## 📋 Descripción del Proyecto

Este sistema permite gestionar clientes y sus acumulaciones de puntos mediante un programa de fidelidad gamificado que incluye:

- **Gestión de Clientes**: Crear, validar y administrar clientes
- **Sistema de Niveles**: Bronce, Plata, Oro, Platino con multiplicadores de puntos
- **Registro de Compras**: Calcular puntos automáticamente según reglas de negocio
- **Bonus de Streak**: +10 puntos por 3 compras seguidas en el mismo día
- **Aplicación de Consola**: Interfaz interactiva para operaciones

## 🏗️ Diseño del Sistema

### Arquitectura

El sistema sigue un diseño orientado a objetos limpio con las siguientes entidades principales:

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│     Cliente     │    │      Compra     │    │      Nivel      │
├─────────────────┤    ├─────────────────┤    ├─────────────────┤
│ - id            │    │ - idCompra      │    │ - umbralMinimo  │
│ - nombre        │    │ - idCliente     │    │ - umbralMaximo  │
│ - correo        │    │ - monto         │    │ - multiplicador │
│ - puntos        │    │ - fecha         │    └─────────────────┘
│ - nivel         │    └─────────────────┘
│ - streakDias    │
│ - comprasHoy    │
│ - ultimaCompra  │
└─────────────────┘
```

### Clases Principales

- **`Cliente`**: Entidad principal que representa un cliente del sistema
- **`Compra`**: Representa una transacción y calcula puntos automáticamente
- **`Nivel`**: Enum que define los niveles de fidelidad y sus multiplicadores
- **`GestionCompras`**: Clase que maneja operaciones CRUD de compras
- **`AplicacionConsola`**: Lógica de negocio de la aplicación
- **`Main`**: Interfaz de usuario y punto de entrada

### Reglas de Negocio

1. **Cálculo de Puntos Base**: $100 = 1 punto (redondeo hacia abajo)
2. **Multiplicadores por Nivel**:
   - Bronce: ×1.0
   - Plata: ×1.2
   - Oro: ×1.5
   - Platino: ×2.0
3. **Bonus de Streak**: +10 puntos por 3 compras seguidas en el mismo día
4. **Validación de Correo**: Debe contener el símbolo "@"

## 🚀 Instrucciones de Instalación y Uso

### Prerrequisitos

- **Java 21** o superior
- **Gradle 8.5** (incluido como wrapper)

### Compilación

```bash
# Clonar el repositorio
git clone https://github.com/Cloff23/Proyecto-Tarjeta-de-Fidelidad-Gamificada.git
cd Proyecto-Tarjeta-de-Fidelidad-Gamificada

# Compilar el proyecto
./gradlew build
```

### Ejecución

**En Windows (Recomendado para uso interactivo):**
```bash
# Usar el script batch para ejecución interactiva
.\run.bat
```

**En Linux/Mac o para ejecuciones no interactivas:**
```bash
# Ejecutar la aplicación con Gradle
./gradlew run
```

**Nota importante**: En Windows, `./gradlew run` puede tener problemas con la entrada interactiva del usuario. Se recomienda usar `.\run.bat` para una experiencia completa de la aplicación de consola.

## 🔧 Solución de Problemas

### Problema: `NoSuchElementException` al ejecutar `./gradlew run`

**Síntomas:**
- La aplicación se queda colgada en "75% EXECUTING"
- Error: `java.util.NoSuchElementException` en `Scanner.nextInt()`

**Causa:**
- Gradle en Windows no maneja correctamente la entrada interactiva (`System.in`)

**Solución:**
- Usar `.\run.bat` en lugar de `./gradlew run`
- El script `run.bat` compila con Gradle pero ejecuta directamente con Java

### Ejecución de Pruebas

```bash
# Ejecutar todas las pruebas
./gradlew test

# Ejecutar pruebas con información detallada
./gradlew test --info

# Generar reporte de cobertura
./gradlew jacocoTestReport
```

## 🎮 Uso de la Aplicación

Al ejecutar la aplicación, se mostrará un menú con las siguientes opciones:

```
=== SISTEMA DE FIDELIDAD GAMIFICADA ===
1. Crear cliente
2. Registrar compra
3. Mostrar puntos/nivel de cliente
4. Listar clientes
5. Salir
=====================================
```

### Ejemplo de Uso

1. **Crear Cliente**:
   - Seleccione opción 1
   - Ingrese ID: `CLI001`
   - Ingrese nombre: `Juan Pérez`
   - Ingrese correo: `juan@email.com`

2. **Registrar Compra**:
   - Seleccione opción 2
   - Ingrese ID de compra: `COMP001`
   - Ingrese ID del cliente: `CLI001`
   - Ingrese monto: `150.0`

3. **Ver Puntos/Nivel**:
   - Seleccione opción 3
   - Ingrese ID del cliente: `CLI001`

## 🧪 Pruebas Unitarias

El proyecto incluye una suite completa de pruebas unitarias desarrolladas siguiendo TDD:

### Ejemplo de Salida de Tests

```bash
./gradlew test

BUILD SUCCESSFUL in 2s
4 actionable tasks: 4 executed

Tests run: 15, Failures: 0, Errors: 0, Skipped: 0
```

### Cobertura de Código

El proyecto utiliza **JaCoCo** para medir la cobertura de código:

```bash
./gradlew jacocoTestReport
```

Los reportes se generan en: `build/reports/jacoco/test/html/index.html`

## 📊 Medición de Cobertura

### ¿Qué tipo de cobertura he medido y por qué?

**Tipo de cobertura medida**: **Cobertura de líneas de código**

**¿Por qué he elegido este tipo de cobertura?**

1. **Simplicidad y Claridad**: La cobertura de líneas es fácil de entender y medir, tanto para desarrolladores como para evaluadores
2. **Identificación de Código No Probado**: Permite identificar rápidamente qué líneas de código no están siendo ejecutadas por las pruebas, facilitando la detección de código muerto o no probado
3. **Adecuada para Metodología TDD**: En TDD, el objetivo fundamental es que cada línea de código tenga una prueba que la valide, por lo que la cobertura de líneas es la métrica más directa para verificar este principio
4. **Herramienta Estándar de la Industria**: JaCoCo es una herramienta ampliamente utilizada, confiable y reconocida en el ecosistema Java
5. **Integración Perfecta con Gradle**: Se integra nativamente con el sistema de build, generando reportes automáticamente después de cada ejecución de pruebas
6. **Métrica Objetiva**: Proporciona un número claro y cuantificable que permite establecer objetivos medibles (en este caso, >90% de cobertura)

**Cobertura objetivo**: >90% de líneas de código cubiertas por pruebas

**Herramienta utilizada**: JaCoCo (Java Code Coverage Library) integrado con Gradle

## 🔄 Metodología TDD

Este proyecto fue desarrollado siguiendo estrictamente la metodología **Test-Driven Development (TDD)** con el ciclo Red-Green-Refactor:

1. **🔴 RED**: Escribir una prueba que falle
2. **🟢 GREEN**: Escribir el código mínimo para que la prueba pase
3. **🔄 REFACTOR**: Mejorar el código sin romper las pruebas

### Evidencia en Commits

El historial de Git muestra claramente el proceso TDD:

```
1. "Agregar test para creación de cliente (RED)"
2. "Implementar clase Cliente y enum Nivel (GREEN)"
3. "Agregar test para registro de compras (RED)"
4. "Implementar clase Compra con cálculo de puntos (GREEN)"
...
```

## 📁 Estructura del Proyecto

```
├── src/
│   ├── main/java/cl/usm/fidelidad/
│   │   ├── Cliente.java
│   │   ├── Compra.java
│   │   ├── Nivel.java
│   │   ├── GestionCompras.java
│   │   ├── AplicacionConsola.java
│   │   └── Main.java
│   └── test/java/cl/usm/fidelidad/
│       ├── ClienteTest.java
│       ├── CompraTest.java
│       ├── StreakTest.java
│       ├── GestionComprasTest.java
│       ├── AplicacionConsolaTest.java
│       └── MainTest.java
├── build.gradle
├── run.bat
└── README.md
```

### Scripts de Ejecución

- **`run.bat`**: Script para Windows que compila y ejecuta la aplicación directamente con Java, evitando problemas de entrada interactiva con Gradle
- **`build.gradle`**: Configuración del proyecto con soporte para JaCoCo y configuración de entrada/salida estándar

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 👨‍💻 Autor

Desarrollado por Claudio Villagrán como proyecto académico siguiendo las especificaciones de la tarea de Tarjeta de Fidelidad Gamificada.

## 🤝 Contribuciones

Este es un proyecto académico individual. Las contribuciones no están abiertas al público.

---

**Nota**: Este proyecto demuestra la aplicación práctica de TDD, programación orientada a objetos y buenas prácticas de desarrollo en Java. 