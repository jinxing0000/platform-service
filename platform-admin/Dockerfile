FROM java:8
VOLUME /tmp
COPY target/platform-admin.jar platform-admin.jar
RUN bash -c "touch /platform-admin.jar"
EXPOSE 8081
ENTRYPOINT ["java","-jar","platform-admin.jar"]