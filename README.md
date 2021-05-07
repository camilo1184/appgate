

Intente realizar la persistencia con R2DBC pero no me funciono, no había trabajado con programación reactiva y por eso lo deje con Jpa aunque soy consiente que no es la solución apropiada.

Cree dos servicios:

	- /geolocation/file 	->carga el archivo

	- /geolocation/ip/{ip}  ->Valida la ip.


Use una arquitectura MVC y plugins adicionales Lombok y Mapstruct
Para la estrategia de logging use logback