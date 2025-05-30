#!/bin/bash

# Navigate to the project directory
cd "$(dirname "$0")"

# Clean and package the project
mvn clean package

# Check if the build was successful
if [ $? -eq 0 ]; then
    echo "Build successful! WAR file created in target directory."
else
    echo "Build failed. Please check the error messages above."
fi 