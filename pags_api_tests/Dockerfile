FROM maven:3.8.4-openjdk-11
ENV BASE_URL https://httpbin.org/status/

COPY src /src
COPY pom.xml /pom.xml

CMD [ "sh", "-c", "mvn -Durl=$BASE_URL test" ]