# soctrav

Welcome to Social Traveler !!! 

We are using maven to do the build. Web Socket module is a war that deploys to tomcat.. We need to run this module in order to get the chat feature running. You can access this feature once authenticated (http://localhost:8080/nav/chat.html). In order to hit websocket directly you need to specify http://host/websocket .

In order to run social traveler run in terminal:

mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"

Mysql:
We use mysql as the relational db.. https://www.mysql.com/downloads/

Run schema script in database folder schema.sql .
