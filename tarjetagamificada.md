# Proyecto «Tarjeta de Fidelidad Gamificada
Programa de línea de comandos en **Java** con **pruebas unitarias JUnit 5**  

- Este es un gran Momento para hacer uso de [TDD](https://aula.usm.cl/course/view.php?id=47173&section=15#tabs-tree-start)
- **Tarea Individual**

---

## 1. Enunciado general
Desarrollar un sistema que gestione un programa de fidelidad para una cadena de tiendas de conveniencia.  
El sistema debe:

1. Administrar **clientes** y su acumulación de puntos.  
2. Registrar **compras** y otorgar puntos según reglas de negocio.  
3. Gestionar **niveles** (Bronce, Plata, Oro, Platino).  

Todo se opera por consola mediante un menú de texto. 

El código debe venir acompañado de pruebas unitarias **[JUnit 5](https://junit.org/junit5/)** y de medición de cobertura con alguna de estas herramienttas:
- [EcclEmma](https://www.eclemma.org/) ([ver videos de uso de JUnit](https://drive.google.com/drive/u/0/folders/185WaYB_TMbQwMU-14Oi58ARyXfIdDG4H))
- [SonarQube](https://www.sonarsource.com/products/sonarqube/)

---

## 2. Requisitos funcionales

### 2.1 Gestión de Clientes

| Operación | Detalles |
|-----------|----------|
| **Agregar cliente** | Atributos: `id`, `nombre`, `correo`, `puntos` (0), `nivel` (Bronce), `streakDias` (0). |
| **CRUD completo** | Crear – Listar – Actualizar – Eliminar. |
| **Restricción** | El `correo` debe ser válido (`@`). |

### 2.2 Registro de Compras

| Operación | Detalles |
|-----------|----------|
| **Registrar compra** | Datos: `idCompra`, `idCliente`, `monto`, `fecha`. |
| **Regla puntos base** | Cada \$100 ⇒ 1 pto (redondeo hacia abajo). |
| **Multiplicador por nivel** | Bronce ×1 • Plata ×1.2 • Oro ×1.5 • Platino ×2 |
| **bonnus** | 3 compras seguidas (dentro de un mismo día) ⇒ +10 pts; se reinicia cada dái. |
| **Histórico** | CRUD de compras. |

### 2.3 Niveles de Fidelidad

| Nivel | Umbral (puntos totales) | Beneficio |
|-------|-------------------------|-----------|
| Bronce | 0 – 499 | — |
| Plata  | 500 – 1499 | +20 % puntos |
| Oro    | 1500 – 2999 | +50 % puntos |
| Platino| 3000 + | +100 % puntos |

El nivel se recalcula tras cada compra.

---

## 3. Requisitos técnicos

| Ítem | Detalle |
|------|---------|
| tipo App | Por consola |
| Lenguaje | Java 21+ |
| Build | Maven / Gradle (documentar en README) (Ver videos de uso JUnit)|
| Pruebas | JUnit 5 + assertions estándar |
| Persistencia | En memoria |
| Estilo | Diseño OO limpio (entidades, repositorios, proyecto en general ) |
| Medir Cobertura | Usar EclEmma (JaCoCo) o SnarQube |
| TDD | Se sugiere uso de TDD |
| Modalidad | Trabajo individual|

---

## 4. Menú principal (CLI)
- Gestión de Clientes
- Gestión de Compras
- Mostrar Puntos / Nivel de un Cliente.
- Salir

## 5. Entregables
Repositorio GitHub (público) con:
- Código fuente organizado
- Suite de tests JUnit.
- README.md que incluya:
  - Descripción del diseño (diagrama UML o otro), no incluir enlaces a repositorios personales (por ejemplo en Sharepoint).
  - Instrucciones para compilar, ejecutar y probar.
  - Ejemplo de salida de tests.
  - Licencia
  - Otras onsideraciones vistas previamente en curso
  - Responde a pregunta: **¿Qué tipo de cobertura he medido y por qué?**


## 6. Dudas y preguntas
 - Cualquier duda o descubrimiento,en el foro de la semana.
