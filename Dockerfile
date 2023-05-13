FROM openjdk:17.0-jdk
COPY bot/target/bot-*.jar weather.jar
EXPOSE 8443
RUN mkdir /data
CMD java -XX:+UnlockExperimentalVMOptions -Dcom.sun.management.jmxremote -Djavax.net.debug=ssl -Dmicronaut.environments=docker ${JAVA_OPTS} -jar weather.jar
