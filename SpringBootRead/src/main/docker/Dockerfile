FROM openjdk:11.0.2-jdk
VOLUME /tmp
ENV APP_NAME @project.build.finalName@.@project.packaging@
ADD $APP_NAME testApp.jar
RUN sh -c 'touch /testApp.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=GMT+08","-jar","/testApp.jar"]