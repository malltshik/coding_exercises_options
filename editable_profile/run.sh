#!/usr/bin/env bash


exitfn () {
    trap SIGINT
    kill $ATTRIBUTE_SERVER_PID
    exit
}

trap "exitfn" INT            # Set up SIGINT trap to call function.
python3 server/server.py &
ATTRIBUTE_SERVER_PID=$!
java -jar target/coding-exercises-options-0.0.1-SNAPSHOT.jar
trap SIGINT