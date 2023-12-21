# Building the application

## Prerequisites

For building the application, you need:

- JDK >= 17
- Docker (or other runtime)

## Building

The application can be built by executing the following command:

```shell
./gradlew clean build dockerClean docker --info
```

This will build the sources and a docker image which will be registered in the local docker registry.

# Executing the application

## Using docker compose

The application comes with a docker compose file that relies on the docker image that is built with `./gradlew docker`.
The docker compose file can be used with the following command:

```shell
docker compose -f docker/compose.yml up -d
```