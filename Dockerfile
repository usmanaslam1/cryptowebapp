FROM usmanaslam/apache:1.1
# copy the packaged war file into our docker image
COPY ./target/*.war /opt/tomcat/webapps/
