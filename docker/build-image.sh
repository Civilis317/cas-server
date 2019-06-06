#!/usr/bin/env bash

# init
REMOTE_IMAGE=openjdk:8u151-jre-alpine3.7
DOCKER_REGISTRY=317
LOCAL_IMAGE=cas53
LOCAL_VERSION=1.0
SRC=../target/cas.war

# remove any existing images with identical name
docker rmi $DOCKER_REGISTRY/$LOCAL_IMAGE:$LOCAL_VERSION

# build new image
docker build --build-arg SRC=$SRC --build-arg SOURCE_IMAGE=$REMOTE_IMAGE --no-cache -t $DOCKER_REGISTRY/$LOCAL_IMAGE:$LOCAL_VERSION .

# remove remote image, to free up disk space
docker rmi $REMOTE_IMAGE

# find new image
docker images | grep $LOCAL_IMAGE
