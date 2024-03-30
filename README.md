# msgastollamadas_hexagonal
Microservicio mplementado con Spring Boot y MongoDb que permite controlar el gasto telefónico a partir de la lectura de un fichero de texto.
Utiliza la arquitectura hexagonal. El inbound adapter sería un RestController que expone una API para ejecutar cargar llamadas y
para obtener la lista de llamadas, así como el resumen de la cantidad de llamadas, tiempo total y gasto total (aún sin incoporar) por cada extensión telefónica. 
Además las operaciones están representadas por puertos de entrada y se implementan a través de Outbound adapters que implementan los puertos de salida. Es este caso los outbound adpter son objetos DAO.


