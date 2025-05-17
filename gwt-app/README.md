# GWT Hello Sample with Maven Support

This project has been configured to use Maven for building and managing dependencies.

## Prerequisites

- Java JDK 1.6 or higher
- Maven 3.x (or use the included Maven wrapper scripts)

## Project Structure

- `src/` - Source code directory
- `war/` - Web application directory
- `pom.xml` - Maven project configuration

## Building the Project

To build the project using Maven:

```
mvn clean package
```

If you don't have Maven installed, you can use the wrapper scripts:

- On Windows: `mvnw.cmd clean package`
- On Unix/Linux: `./mvnw clean package`

## Running in Development Mode

To run the application in GWT development mode:

```
mvn gwt:run
```

Or with the wrapper:

- On Windows: `mvnw.cmd gwt:run`
- On Unix/Linux: `./mvnw gwt:run`

## Creating a WAR file

To create a deployable WAR file:

```
mvn clean package
```

The WAR file will be created in the `target/` directory.


## GWT Module

The GWT module is defined in `src/com/google/gwt/sample/hello/Hello.gwt.xml`
