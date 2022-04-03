FROM usmanaslam/apache_tomcat:0.4
# copy the packaged war file into our docker image
COPY ./target/*.war /opt/tomcat/webapps/
