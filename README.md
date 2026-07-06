<div align="right">
  <img width="280" height="142" alt="Running-Pikachu-GIF" src="https://github.com/user-attachments/assets/a68cadd8-da04-4543-bcbf-e3ae79f9bfe1" />
</div>
## Taller 4: Pokémon TCG - Gestor de Colección 

##  Descripción del proyecto

El sistema nace para ayudar a unos usuarios a organizar su colección de cartas de Pokémon TCG. La aplicación permite cargar un inventario inicial desde un archivo de texto, administrar la colección (agregando, modificando y eliminando cartas) y visualizar una galería interactiva. Además, incorpora mecánicas de ordenamiento y cálculo de poder dinámico dependiendo del tipo de carta (Pokémon, Item, Supporter o Energy).

El sistema esta creado de tal forma en que el codigo se separan la interfaz gráfica de la lógica de negocio, e implementando múltiples patrones de diseño: **Singleton** (para la instancia única del sistema), **Factory** (para la instanciación dinámica de cartas), **Strategy** (para los distintos criterios de ordenamiento) y **Visitor** (para el cálculo de puntajes de poder).

##  Integrantes
* **Martin Alonso Arancibia Alzamora** | 22.273.853-9 | GitHub: [@martinnnql](https://github.com/martinnnql)
* **Ignacio Bastian Valdivia Rodriguez** | 22.179.357-9 | GitHub: [@nchq7](https://github.com/nchq7)

##  Estructura del Proyecto
El código fuente está modularizado en los siguientes paquetes para asegurar una alta cohesión y bajo acoplamiento:

* **`logica`**: Contiene el motor de la aplicación.
  * `App.java`: Clase principal que inicializa el sistema y lee el archivo inicial.
  * `SistemaImpl.java`: Implementación central que administra la lista de cartas aplicando el patrón *Singleton*.
  * `ISistema.java`: Interfaz que define los contratos del sistema.
* **`dominio`**: Define las entidades del negocio.
  * `Carta.java`: Clase abstracta base.
  * `Pokemon.java`, `Item.java`, `Supporter.java`, `Energy.java`: Clases concretas que heredan de `Carta` con sus atributos específicos.
* **`gui`**: Contiene toda la interfaz visual interactiva basada en Java Swing.
  * `VentanaPrincipal.java`: Contenedor base de la aplicación.
  * `PanelAdmin.java`: Pestaña con el formulario para el CRUD de cartas.
  * `PanelColeccion.java`: Pestaña que muestra la galería visual con los filtros de orden.
  * `DetalleCartas.java`: Ventana emergente con la vista ampliada y estadísticas.
  * `Imagen.java`: Clase utilitaria para la carga y renderizado de recursos gráficos.
* **`factory`**:
  * `FactoryCarta.java`: Implementa el patrón *Factory Method* para instanciar el tipo correcto de carta al leer el archivo de texto.
* **`strategy`**:
  * `IStrategy.java`, `StrategyOrdenarNombre.java`, `StrategyOrdenarPoder.java`, `StrategyOrdenarRareza.java`: Clases que encapsulan los algoritmos de ordenamiento visual del patrón *Strategy*.
* **`visitor`**:
  * `IVisitor.java`, `VisitorPoder.java`: Implementación del patrón *Visitor* para calcular el score de poder dependiendo de los atributos específicos de cada subtipo de carta sin alterar sus clases base.

##  Instrucciones de ejecución

1. **Clonar el repositorio:** Descarga o clona este repositorio en tu máquina local y ábrelo en tu IDE de preferencia.
2. **Ubicación del archivo de texto:** Asegúrate de que el archivo `sobres.txt` esté ubicado en el directorio raíz del proyecto (al mismo nivel que la carpeta `src` o `bin`, dependiendo de tu entorno).
3. **Carpeta de Imágenes:** En el directorio raíz del proyecto, asegúrate de que exista una carpeta llamada `imagenes/`. Dentro de esta carpeta debes colocar de forma manual las imágenes correspondientes a cada carta en formato `.png` (el nombre del archivo debe coincidir exactamente con el nombre de la carta, en minúsculas). También debe existir un archivo llamado `default.png` que actuará como imagen de reemplazo si no se encuentra la ilustración oficial.
4. **Ejecución:** Dirígete al paquete `logica`, busca el archivo `App.java` y ejecuta su método `main`.
