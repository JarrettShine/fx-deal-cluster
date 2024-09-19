# Makefile for Spring Boot application with Docker

# Variables
IMAGE_NAME = fx-deal-cluster
COMPOSE_FILE = docker-compose.yml

# Commands
build:
    @docker build -t $(IMAGE_NAME) .

run:
    @docker-compose -f $(COMPOSE_FILE) up

stop:
    @docker-compose -f $(COMPOSE_FILE) down

restart: stop run

.PHONY: build run stop restart
