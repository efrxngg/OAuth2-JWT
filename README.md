# Autenticación y Autorización Mediante el Servidor de Recursos OAuth 2.0 🔒

El propósito de este proyecto es servir como base para la seguridad mediante **JWT** de futuros proyectos. Para poder probarlo solo es necesario tener el **JDK 17** instalado y un IDE con soporte para Lombok. La base de datos utilizada es **H2**, cuenta con pruebas de integración a los diferentes endpoints y **Swagger** como herramienta de documentación de la **API REST** a los cuales puedes acceder mediante la siguiente **URI**:

```
[URL]:[PORT]/swagger-ui/index.html#/
```

## Este proyecto cuenta con siguientes endpoints

| EndPoint  | Funcion                                                                  |
| :-------- |--------------------------------------------------------------------------|
| /sign-up  | Registrar usuario y además genera un Refresh Token                       |
| /sign-in  | Logear al usuario y retornar un nuevo Refresh Token elimando el anterior |
| /sign-out | Eliminar el refresh token del Usuario                                    |
| /refresh  | Generar nuevos Access Token                                              |

## ¿Como generar un par de nuevas claves RSA con una validez de 90 dias?

Si tiene JDK correctamente instalado junto con las variables de entorno entonces, en tu terminal puedes pegar la siguiente linea de comando

```
keytool -genkey -alias "jwt-sign-key" -keyalg RSA -keystore jwt-keystore.jks -keysize 4096
```

La línea de comando anterior es un ejemplo de cómo usar el utilitario **keytool** para generar un par de claves RSA con una longitud de 4096 bits y almacenarlas en un archivo de almacén de claves llamado "jwt-keystore.jks". El par de claves se almacena bajo el alias "jwt-sign-key". Esta herramienta ya viene incluida en el JDK
y sirve para firmar los tokens generados.

Luego en el **application.properties** debes especificar la locacion, la contraseña, la frase y el alias del archivo que generaste

## Contenido del JWT

- Algoritmo de encriptación utilizado **SHA256**
- Carga Util **Rol**
- Firma **RSA**
