FROM openjdk:17.0-jdk
COPY bot/target/bot-*.jar weather.jar
EXPOSE 443
EXPOSE 80
RUN mkdir /data
CMD java -XX:+UnlockExperimentalVMOptions -Dcom.sun.management.jmxremote -Djavax.net.debug=ssl ${JAVA_OPTS} -jar weather.jar
