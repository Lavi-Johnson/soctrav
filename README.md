# soctrav

Welcome to Social Traveler !!! 

We are using maven to do the build. Please ignore the module soctrav-app for now.. Web Socket module is a war that deploys to tomcat.. We need to run this module in order to get the chat feature running. In order to hit it you need to specify http://host/websocket

In order to run social traveler run in terminal:

mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"