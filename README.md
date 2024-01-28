# Actividad 1. Manejo de ficheros

## Objetivos

Aprender a trabajar de manera práctica los ficheros mediante una pequeña aplicación
de gestión de un almacén.

## Pautas de elaboración

**Requerimiento 1**

Esta práctica consiste en la implementación de un programa Java para la gestión del
almacén de artículos. Los artículos tendrán los siguientes atributos, id, nombre,
descripción, stock (o cantidad) y precio.

El usuario interactuará con el programa a través del siguiente menú, que servirá como interfaz.

- Añadir nuevo artículo
- Borrar artículo por id
- Consulta artículo por id
- Listado de todos los artículos
- Terminar el programa

Nada más comenzar la ejecución del programa se debe verificar si existe el fichero **artículos.dat**
(fichero que contendrá objetos ‘Articulo’). Si existe, debes leerlo para llenar una colección de
tipo ArrayList con todos los objetos ‘Articulo’ existentes en el fichero. Si no existe el archivo,
no tendrás nada que hacer por el momento, pero sí debes dejar la colección ArrayList disponible,
aunque esté vacía.

Las opciones del menú 1 a 5 trabajarán sobre la colección de tipo ArrayList para añadir, borrar,
consultar o listar, y no sobre el fichero **artículos.dat**.

Cuando el usuario decida terminar la ejecución del programa pulsando la opción 5, el programa
deberá crear el fichero **artículos.dat**, sobrescribiendo el anterior si existiera. Se escribirán
en el fichero tantos artículos como elementos tenga la colección ArrayList que has creado.

**Requerimiento 2**

Se añadirá una opción al menú que será “Exportar artículos a archivo CSV”, que creará un fichero
(artículos.csv) donde guardará la información de los artículos con el formato de un archivo CSV.
Se debe comprobar que dicho fichero se puede abrir con un programa como Excel o alguna herramienta
en online en la memoria de la actividad. Se podrá utilizar alguna biblioteca de java para hacer
esta funcionalidad.

**Requerimiento 3**

No se permite duplicar el id del artículo.

## Consideraciones de entrega

Para la entrega, se subirá **un documento PDF con todo lo necesario para demostrar el correcto funcionamiento
de la actividad** (resultados, capturas de pantalla, ficheros, fotos, etc.). No es necesario que el documento
PDF sea muy extenso, pero SÍ que incluya, al menos, la metodología de trabajo del grupo, las capturas de los
resultados obtenidos con los comentarios pertinentes, y la explicación de los puntos clave de la actividad
realizada. No cumplir con este punto puede llevar a suspender la actividad o a reducir considerablemente
la nota final.

Además, para toda la actividad se valorará la claridad de código, la modularidad y la eficiencia de los
algoritmos empleados.

Para la actividad se recomienda que todos los integrantes aporten una posible solución y luego elijan cual
será la solución final mediante consenso.

**Se recomienda el uso de GITHUB para realizar el trabajo** y dejar el código fuente en dicha plataforma, ya que,
si hay problemas con la entrega al subirla a la plataforma de EDIX, queda constancia en GITHUB de los commits
hechos. Se puede subir el código fuente también comprimido a la plataforma en su lugar si así se prefiere,
pero en este caso, el fichero PDF y el código comprimido (fichero .zip o .7z) deben de ir por separado.