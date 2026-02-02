# Java weather app backend

## Introduction

This is the backend for a weather app PoC, the front end code repository can be found at:

https://github.com/JoeCaswall/WeatherAppFrontend

## Setup

### Prereqs

- Java 21 installed
- Maven installed

- Sign up for free at weatherbit.io and get your API key and set this as an env variable in IntelliJ
run > edit configurations > environment variables
```shell
WEATHERBIT_API_KEY=<apiKey>
```
- Set up postgres (Mac)
```shell
brew install postgresql # If not installed already

psql -U postgres # Launches postgres with username postgres
# CLI will launch
CREATE DATABASE weatherapp; # Creates db
\c weatherapp # Connects to new db
```

### Starting backend
```shell
mvn clean install -DskipTests -e
```
Then hit the run button (green play button at the top)

See front end README for front end set up

## Future improvements

- geolocation for default location instead of manually setting it
- Separate Location table so multiple people having the same favourite location doesn't need infinitely scaling rows