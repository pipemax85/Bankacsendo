
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

Cada vez que se hace una transacción se actualizan las cantidades disponibles, por tanto se recomienda tener eso en cuenta a la hora de hacer pruebas, existe un menú de navegación y se recomienda utilizarlo, por que si se tuvieran dos pestañas abiertas al mismo tiempo hay que tener en cuenta que los datos guardados en la base de datos se consultan al cargar la pestaña de billetes por cuestiones de velocidad y eficiencia.  

Existen dos entidades creadas en la base de datos 
* COIN:  (Haciendo referencia al nombre del problema original) que es donde se guardan los billetes con sus denominaciones y cantidades.
* lOGWITHDRAWALCOIN: Es donde se guarda el registro de las transacciones hechas por el cliente.

El código fue hecho en ingles por convención, las etiquetas y en general todo el contacto con el cliente esta en español, los comentarios se dejaron en español por facilidad para el revisor del proyecto. 
 
## Creacion de base de datos 

CREATE DATABASE "ATM"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Colombia.1252'
    LC_CTYPE = 'Spanish_Colombia.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE coin (
 id bigint PRIMARY KEY,
 denomination decimal NOT NULL,
 amount decimal NOT NULL,
 created_Date TIMESTAMP NOT NULL
);

CREATE TABLE logwithdrawalcoin (
 id bigint PRIMARY KEY,
 quantity decimal NOT NULL,
 created_Date TIMESTAMP NOT NULL
);

## Instrucciones para ver las interfaces 

Al proyecto le puse por nombre "primefaces" , por tanto la carpeta madre tiene ese nombre  y también la ruta de acceso a las vistas.

Yo tengo configurado el puerto 8080 para ver los proyectos, voy a poner el ejemplo basado en ese supuesto., Para ingresar a las vistas desde un navegador, una vez desplegado el aplicativo sería. 

Vista del administrador (Billetes)-  http://localhost:8080/primefaces/faces/coin.xhtml
Vista del cliente (Transacciones( - http://localhost:8080/primefaces/faces/change.xhtml 

La pagina de billetes también esta en la ruta base, configurado en el web.xml por tanto otra posible ruta de acceso es http://localhost:8080/primefaces/ , igual dentro de las paginas hay navegacion por tanto es suficiente con entrar a una sola
