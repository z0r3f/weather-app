FROM scratch
COPY weather-delivery/target/weather-delivery /app
EXPOSE 8443
RUN mkdir /data
CMD ["/app/weather-delivery"]
