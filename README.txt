# Environment recommendation: Tomcat7, JDK1.6, Maven3
# 2 wars need to deploy messageBoards-cxf-ws.war (WS server side), messageBoards-web.war (Web client)
# Recommend server Java Opts -Xmx1024m

# CXF Service Endpoints can be found at localhost:8080/messageBoards-cxf-ws after deployment

cd messageBoards
# mvn install modules with h2 (in memory database) profile
mvn install -P h2
# will generate war and jar into sub module target folders

cd messageBoards-cxf-ws/target

# You will find messageBoards-cxf-ws.war which is the CXF webservice package, 
# please deploy to localhost:8080 server, since client side is configured to connect to it

cd messageBoards-web/target

# There is messageBoards-web.war which is the web client package, 
# you can deploy to the same or other server which is able to connect to localhost:8080
