# Proyecto: selenium-intermedio-testng

## Qué es

Un framework de automatización de pruebas web construido con **Selenium 4** + **TestNG**, en **Java 21**, organizado bajo el patrón **Page Object Model (POM)**. Automatiza dos sitios distintos para cubrir dos tipos de escenario: un sitio de práctica con controles de formulario (inputs, alerts, drag-and-drop, etc.) y la navegación real por los menús del sitio público de Santander México.

## Estructura del proyecto

```
selenium-intermedio-testng/
├── pom.xml                          # Dependencias y configuración de Maven
├── testng.xml                       # Suite de TestNG (actualmente comentada)
├── src/
│   ├── main/java/
│   │   ├── base/BasePage.java              # Operaciones reutilizables de página
│   │   ├── utils/DriverFactory.java        # Creación del WebDriver de Chrome
│   │   └── pages/
│   │       ├── AutomationPracticePage.java # Page Object del sitio de práctica
│   │       └── SantanderHomePage.java      # Page Object del sitio de Santander
│   └── test/java/
│       ├── base/BaseTest.java              # Ciclo de vida del navegador por test
│       ├── listeners/ScreenshotListener.java # Captura de pantalla al fallar
│       └── tests/
│           ├── AutomationPracticeTest.java    # Pruebas de controles de formulario
│           └── SantanderNavigationTest.java   # Pruebas de navegación por menús
└── test-output/                     # Reportes generados por TestNG (HTML, XML, capturas)
```

## Las piezas, una por una

### `BasePage` — la base de todo Page Object

Clase abstracta de la que heredan todas las páginas. Centraliza las operaciones que cualquier página necesita, para no repetir código:

- `find`: espera explícita (`WebDriverWait`) hasta que un elemento sea visible.
- `click`: espera a que el elemento sea *clickable*, hace scroll hacia él con JavaScript y recién entonces hace clic — evita el error clásico de "element not interactable" cuando el elemento está fuera de vista.
- `write`: limpia el campo y escribe el valor.
- `selectByText` / `isSelected` / `exists`: utilidades sobre `<select>`, checkboxes/radios y existencia de un elemento.

Todas las esperas usan `WebDriverWait` con `ExpectedConditions`, es decir, esperas explícitas — nunca `Thread.sleep`.

### `DriverFactory` — quién crea el navegador

Clase utilitaria (constructor privado, no se instancia) que arma un `ChromeDriver` con:
- El gestor de contraseñas de Chrome desactivado (para que no interrumpa las pruebas con popups).
- Soporte para modo *headless* activable por propiedad del sistema (`-Dheadless=true`), útil para correr en CI sin abrir una ventana real.
- Selenium Manager resuelve automáticamente la versión de ChromeDriver compatible; no hay binarios que mantener a mano.

### `AutomationPracticePage` — Page Object del sitio de práctica

Cubre un formulario típico de práctica de automatización: inputs de texto, radio buttons, checkboxes, selects simples y múltiples, campos de fecha, carga de archivo, una tabla de productos, los tres tipos de `Alert` de JavaScript (alert, confirm, prompt), un menú por *hover*, copiar texto con doble clic, *drag-and-drop* y apertura/cierre de una pestaña nueva. Cada interacción compleja del sitio (por ejemplo, manejar alerts o encontrar la ventana nueva entre los *window handles*) queda encapsulada en un método con nombre de negocio, como `acceptSimpleAlert()` o `dragAndDropElement()`.

### `SantanderHomePage` — Page Object del sitio de Santander

Un Page Object mucho más grande: modela el menú de navegación completo del sitio público de Santander México (`santander.com.mx`), con más de 50 localizadores (`By`) apuntando a los enlaces reales del menú "Personas", "Empresas" y "PyMes" (tarjetas de crédito, hipotecas, seguros, cuentas, fondos de inversión, canales digitales, etc.). Cada `navigateToX()` abre el submenú correspondiente y hace clic en el enlace. Es el caso de uso de un Page Object aplicado a un sitio de producción real, no a un sandbox de práctica.

### `BaseTest` — el ciclo de vida de cada prueba

Se ejecuta antes (`@BeforeMethod`) y después (`@AfterMethod`) de cada `@Test`: abre un navegador nuevo, navega a la URL base, y al terminar lo cierra. Como las pruebas de Santander navegan por decenas de enlaces reales y a veces terminan en pestañas nuevas o ventanas inesperadas, `BaseTest` también resuelve un problema práctico: `returnToHomePage()` detecta si sigue habiendo una ventana abierta con el handle original, cierra las demás y vuelve al home; y si el navegador se perdió por completo (una excepción de WebDriver), lo reinicia solo. Esto hace que una prueba de navegación no arrastre el estado roto de la anterior.

### `ScreenshotListener` — evidencia automática de fallos

Implementa `ITestListener` de TestNG y se dispara en `onTestFailure`. Toma una captura de pantalla del navegador en el momento exacto del fallo, la guarda en `test-output/screenshots/` con el nombre del método y un timestamp, y agrega un enlace a esa captura dentro del reporte de TestNG. Así, cuando algo falla, no hay que reproducirlo a mano para ver qué pasó: la captura ya quedó guardada.

### `AutomationPracticeTest` — pruebas de controles de UI

Cuatro pruebas, ejecutadas en orden por `priority`:
1. Llenar el formulario completo y validar que los controles principales quedaron marcados.
2. Interactuar con la tabla de productos.
3. Manejar los tres tipos de `Alert`.
4. Ejecutar hover, doble clic, drag-and-drop y pestaña nueva — y, si se activa la propiedad `-DforceFailure=true`, forzar un fallo intencional para demostrar que `ScreenshotListener` efectivamente captura la pantalla.

### `SantanderNavigationTest` — pruebas de navegación real

Nueve pruebas que recorren, agrupadas por sección del menú (créditos, canales digitales, cuentas, inversiones y seguros, ayuda y beneficios, empresas, PyMes, banca privada, "Acerca de"), decenas de enlaces reales del sitio. El método `validateNavigationFlow` es el corazón de la clase: por cada enlace, vuelve al home, abre el menú, hace clic, y valida que la navegación ocurrió — todo envuelto en un `try/catch` que, si algo falla (un elemento que cambió, un timeout, una ventana que no abrió), no detiene la prueba completa: registra el fallo en un `SoftAssert` y sigue con el siguiente enlace. Al final, `softAssertions.assertAll()` reporta todos los fallos acumulados de una sola vez. Esto es clave para un sitio de producción real: si un solo enlace cambió, no quieres que eso oculte el resultado de los otros 15 que sí funcionan.

## Cómo se ejecuta

```bash
mvn clean test                        # ejecución normal, abre Chrome
mvn clean test -Dheadless=true        # sin interfaz gráfica (CI)
mvn clean test -DforceFailure=true    # fuerza un fallo para probar la captura automática
```

Los reportes quedan en `test-output/index.html` y `test-output/emailable-report.html`; las capturas de fallos, en `test-output/screenshots/`.

## Resultado de la última ejecución incluida en el proyecto

Según `test-output/testng-results.xml` (corrida del 8 de septiembre de 2026, suite "Default suite"):

| Total | Passed | Failed | Skipped |
|---|---|---|---|
| 9 | 8 | 1 | 0 |

El único fallo fue en `shouldNavigateThroughPersonasHelpAndBenefits`, dentro de `SantanderNavigationTest` — consistente con lo esperado de navegar por un sitio real, donde un enlace del menú de ayuda pudo haber cambiado o no respondió a tiempo. El proyecto también incluye una captura (`shouldCompleteFormAndControls-20260908-063848.png`) guardada por `ScreenshotListener` en una corrida anterior de `AutomationPracticeTest`.

