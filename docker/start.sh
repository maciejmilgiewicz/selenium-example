#!/usr/bin/env bash

docker container run -d \
  --name selenium-chrome \
  --rm \
  -p 5900:5900 \
  -p 4444:4444 \
  -v /dev/shm:/dev/shm \
  selenium/standalone-chrome:latest
