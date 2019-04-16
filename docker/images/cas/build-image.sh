#!/usr/bin/env bash

# Docker file starts with: FROM mongo:4.0.3
# local image: 317/mongodb:1.0 => remote image: mongo:4.1.3

#setup
# REMOTE_IMAGE=mongo:3.4.3
REMOTE_IMAGE=apereo/cas:v5.3.9
LOCAL_IMAGE=317/cas539:1.1

# remove any container instantiated from 317/cas539:1.1
docker rm 317_cas1.1

# delete any existing image with same name
docker rmi $LOCAL_IMAGE

# create new image
docker build --build-arg SOURCE_IMAGE=$REMOTE_IMAGE --no-cache -t $LOCAL_IMAGE .


# off topic
#echo "removing windows-style line-endings"
# for linux:
#sed -i 's/\r$//' ./build-image.sh
#sed -i 's/\r$//' ./Dockerfile

# for mac os:
#sed -i.bak $'s/\r//' ./build-image.sh
#sed -i.bak $'s/\r//' ./Dockerfile
