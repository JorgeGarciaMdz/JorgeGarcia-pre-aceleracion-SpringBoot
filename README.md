# README

This README would normally document whatever steps are necessary to get the
application up and running.

Things you may want to cover:

* java version 8.

* Database configuration
    * Se utiliza MySql maria DB 8.0.26
    * En application.properties se debe reemplazar:
        * path: url_base_de_datos
        * Usuario: su_usuario.
        * Contraseña: su_contraseña.

* Run proyect
    * In linux:
        * mvn spring-boot:run
        * ./mvnw spring-boot:run
    * In windows
        * mvnw.cmd spring-boot:run

* Api test with postman:
    * import java.postman_collection.json file on application Postman.