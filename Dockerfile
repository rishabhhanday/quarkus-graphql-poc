FROM amd64/openjdk
COPY ./target/quarkus-app/ ./
EXPOSE 5000
ENTRYPOINT ["java","-jar","quarkus-run.jar"]