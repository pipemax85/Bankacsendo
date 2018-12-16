
# Problema de cambio de monedas recreado en un cajero ficticio 

En el actual proyecto se soluciona el problema de cambio de monedas https://es.wikipedia.org/wiki/Problema_de_cambio_de_monedas, con las consideración adicional de que las monedas son limitadas. 

La solución debe estar implementada en dos vistas, una de cara al administrador y otra de cara al cliente. En la del administrador pueden crearse, editarse o eliminarse las monedas son sus denominaciones y cantidades disponibles. En la del cliente, es donde se hace la implementación del algoritmo basado en los datos guardados, al realizarse una transacción de retiro por parte del cliente, la cantidad disponible de billetes de las denominaciones usadas son actualizadas. 

## Tecnologías usadas  

* Maven
* JSF
* Apache Tomcat 
* Postgresql 9.1-901-1
* Hibernate 5.3.7.Final
* Primefaces 6.0
* Java 1.8

## Configuración  

En el archivo hibernate.cfg.xml están los datos de acceso a la base de datos, estos deben actualizarse con los datos respectivos de la base de datos de la empresa.

## Consideraciones

Existen dos entidades creadas en la base de datos 
* COIN:  (Haciendo referencia al nombre del problema original) que es donde se guardan los billetes con sus denominaciones y cantidades.
* lOGWITHDRAWALCOIN: Es donde se guarda el registro de las transacciones hechas por el cliente.
