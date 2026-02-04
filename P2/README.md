# AEKI
**All rights reserverd.** 

Este es un proyecto de una empresa ficticia llamada AEKI, similar a IKEA, para la asignatura Gestión de Procesos de Negocio. La ejecución consiste en la recepción de un producto concreto y registrarlo en nuestra base de datos. En la carpeta "doc" podemos encontrar el enunciado de la tarea a realizar. Dentro de esta carpeta también podemos encontrar el diagrama Entidad-Relación de la base de datos creada para este proyecto. Además, al ejecutar el API REST podremos acceder a la documentación del mismo en Swagger a través de la URL marcada en el archivo *application.yml*.

## Configuración necesaria

  - El API REST corre localmente en el puerto 10000.
  - El servidor de fakeSMTP debe correr en el puerto 2525.
  - PostgreSQL necesita una base de datos llamada "AEKI" y para acceder desde el backend el usuario y la contraseña es "aeki" en ambos campos. Esto se puede modificar en las líneas 92, 93 y 94 del archivo **pom.xml** si se desea usar otra configuración.
  - La base de datos, al ejecutar **mvn sql:execute**, creará 6 almacenes, 6 productos y una serie de stocks para cada uno.
  - Para ejecutar las tareas del responsable del almacén se debe iniciar sesión con alguno de estos dos usuarios:
    - login: adrian / contraseña: adrian
    - login: lucas / contraseña: lucas

## Suposiciones realizadas

  - Los tests exponen el funcionamiento correcto del API Rest, no buscan el manejo correcto de errores. Aunque el programa maneja diferentes de ellos, no se ha contemplado manejarlos todos debido a la escasez de tiempo. Dicho programa está centrado en representar el conocimiento adquirido en Bonita y Spring.
  - El responsable de almacén no necesita saber más información de la presentada actualmente en el formulario, pues como se comentó en clase, el responsable siempre aceptará cualquier solicitud que reciba.
  - Cuando un empleado introduzca información de los productos, si no existe se creará con la información aportada. Si existe, se ignorará y se le mostrará la información registrada actualmente en la base de datos.
  - En los formularios presentados se solicita una cantidad mínima y máxima para el stock del producto y almacén introducido, de esta forma se creará el stock correspondiente en caso de no existir previamente.
  - La información solicitada en los formularios es la que se pide en el enunciado. Sin embargo, comprendemos que es un ejemplo, ya que no tiene sentido que el empleado del almacén registre la información de un producto y posteriormente se le vuelva a pedir.
  - En el API REST, si se hace una petición a */transfers* o a */receptions*, crea en la base de datos tanto un producto como un stock si alguno de ellos no existe. Esto se debe al caso presentado en el enunciado, pero una petición a estas direcciones no deberían crear dichas tuplas en las tablas. Se deberían tener procesos diferentes para la creación tanto de un stock como de un producto, con sus correspondientes direcciones.
