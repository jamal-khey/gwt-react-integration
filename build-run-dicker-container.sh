#
# Build the WAR file first
cd gwt-app
mvn clean package

# Go to the root of your project (where the Dockerfile is)
cd ..

# Build the Docker image
docker build -t my-gwt-react-app .

# Run the container
docker run -p 8080:8080 my-gwt-react-app