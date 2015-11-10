# Cookbook: REST Service Implementation

  * Author: [Sébastien Mosser](mosser@i3s.unice.fr)
  * Version: 0.9

## Technological environment

  * Build: Maven (3)
  * Language: Java (8)
  * Deployment: OSGi components on top of Apache Service Mix (6.0.0)
  * Test: SoapUI (5.2.0) / JUnit (4.12)

## Specifications

We provide here a way to create generators used to uniquely identify objects. We will expose a set of generators as the initial resource available in the system, and the user will be able to:

  * Available set of generators: `/generators`
    * `POST` is used to create a new generator
  * Given generator: `/generator/{name}`
    * `DELETE` is used to remove this generator
    * `GET` is used to retrieve a new generated value for this generator 

## Development

The first steps are dedicated to the setup of a Maven environment, and the implementation of the service.
 
### Step #1: Setting up the environment

We are using Maven to support the build. Here is the structure of a valid Maven project:

    azrael:rest mosser$ tree .
    .
    ├── pom.xml
    ├── src
    │   └── main
    │       ├── java
    │       └── fr.unice.polytech.al.drones.central.resources
    └── test


  * The `pom.xml` file describes the project
  * The `src/main/java` directory contains the source code of the service
  * The `src/main/fr.unice.polytech.al.drones.central.resources` directory contains additional fr.unice.polytech.al.drones.central.resources
  * The `test` directory contains the unit testing source code for the service

### Step #2: Filling the POM description model

The __Project Object Model__ (POM) describes the service, its dependencies and how to build it. The important attributes are:

  * `groupId` & `artifactId`: pair of unique names used to identify the service in the Maven dependency system;
  * `name`: the name ServiceMix will display when listing components
  * `packaging`: we are building an OSGi `bundle`, not a classical JAR file;
  * `dependencies`: we rely on 2 artefacts from CXF: 
    *  `org.apache.servicemix.specs.jsr339-api-2.0` to access to the JAX-RS specification (e.g., REST for java)
    *  `org.apache.servicemix.bundles.commons-httpclient` to work with the HTTP protocol
    *  We also import the classical `org.json` library to build JSON representations.
  * `build` / `maven-bundle-plugin`: this plugin adapts the build process to create an OSGi bundle. The important attributes are the following:
    * `Bundle-SymbolicName`: the name that ServiceMix will uses to identify the component;
    * `Export-Package`: the Java packages exported by the components and available for others OSGi components.
    * `Embed-Dependency` and `Embed-Transitive` will include in our bundle all the dependencies used by the project. This is a quick and dirty hack to import `org.json`, it is preferable to use a library available as an OSGi dependency.

### Step #3: Implementing the Web Service


We provide here a way to create generators used to uniquely identify objects. We will expose a set of generators as the initial resource available in the system, and the user will be able to:

  * Available set of generators: `/generators`
    * `POST` is used to create a new generator
    * `GET`  is used to retrieve the existing generators
  * Given generator: `/generator/{name}`
    * `DELETE` is used to remove this generator
    * `GET` is used to retrieve a new generated value for this generator 

**In this course, we are not interested by the contents of the operations. Mocks will always be preferred to complex implementations.**

#### Data storage and generator model

We consider a Generator as a public name and a private counter, which will generate identifiers like `{name}{cpt}` and incrementing cpt after each generation.

We create a quick'n dirty `Storage` class that will act as a storage backend for the very purpose of this cookbook. It contains a static hashmap that will be the storage layer, and 3 methods (`create`, `read` and `delete`) of the classical CRUD pattern. We also add a `findAll` method to retrieve all the elements in the database at once. Using a `static` block, we add a generator in the system for demonstration purpose.

#### Service implementation

Each entry in the public API described previously is implemented as a method in the `GeneratorService` class. The important annotations are:

  * `@Path` to identify where a resource will be exposed
  * `@Consumes` to specify which kind of input is expected (a media type)
  * `@Produces` to specify which kind of output is produced (a media type)
  * `@GET`, `@POST` and `@DELETE` to expose methods as operation available through the uniform HTTP API.


## Deployment

Considering the implemented service, we need to make it compliant with the OSGi standard, and then package it before deploying it on top of our ESB.

### Step #4: Describing the OSGi component

In the `fr.unice.polytech.al.drones.central.resources` directory, we rely on a file named `OSGI-INF/blueprint/blueprint.xml` that describes the service as an OSGi component. This file describes where the service will be deployed (here under a `demo` URL prefix), and bind this exposition to the implementation class through the definition of a bean.

```xml
<jaxrs:server id="genService" address="/demo">
    <jaxrs:serviceBeans>
        <ref component-id="genSvc"/>
    </jaxrs:serviceBeans>
</jaxrs:server>

<bean id="genSvc" class="GeneratorService"/>
```

### Step #5: Packaging the bundle

Let's rely on maven black magic: `mvn clean package`. The command produces in the `target` directory a JAR file that contains our bundle. The name of the file is based on the `artifactId` and `version` attributes,  here `ws-rest-1.0.jar`.

### Step #6: Deploying on top of ServiceMix

We consider here an up and running instance of ServiceMix (if not, install ServiceMix on your computer, and start it by running the `bin/servicemix` script.

```
azrael:bin mosser$ ./servicemix
Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=256M; support was removed in 8.0
Please wait while Apache ServiceMix is starting...
100% [========================================================================]

Karaf started in 10s. Bundle stats: 233 active, 233 total
 ____                  _          __  __ _      
/ ___|  ___ _ ____   _(_) ___ ___|  \/  (_)_  __
\___ \ / _ \ '__\ \ / / |/ __/ _ \ |\/| | \ \/ /
 ___) |  __/ |   \ V /| | (_|  __/ |  | | |>  < 
|____/ \___|_|    \_/ |_|\___\___|_|  |_|_/_/\_\

  Apache ServiceMix (6.0.0)

Hit '<tab>' for a list of available commands
and '[cmd] --help' for help on a specific command.
Hit '<ctrl-d>' or 'osgi:shutdown' to shutdown ServiceMix.

karaf@root>
``` 

To deploy the component, copy-paste the JAR file into the `deploy` directory of your local service mix instance. It triggers the hot deployment process available on the bus (look at the log file `data/log/servicemix.log`). 

The `bundle:list` command of the ServiceMix shell lists all the bundles known by the bus, including our new one at the bottom of the list:

```
karaf@root>bundle:list
START LEVEL 100 , List Threshold: 50
 ID | State  | Lvl | Version                            | Name                                      
----------------------------------------------------------------------------------------------------
...
238 | Active |  80 | 1.0                                | SOA1 :: REST-based implementation         
```

This commands tells us that our bundle is deployed under the id `234`. This key will be used by other commands to control its lifecycle:

  * `bundle:stop 238`: to stop the bundle
  * `bundle:start 238`: to start the bundle again
  * `bundle:uninstall 238`: to uninstall the bundle (removing it completely from ServiceMix). This can also be done by removing the jar file from the `deploy` directory.

Remark: an uninstalled bundle cannot be re-installed with the hot-deployment feature as is. Recreating the JAR is the most simple way to benefit from the hot deployment again,

### Step #7: Accessing the web service

Web services deployed on top of ServiceMix are available under the `cxf` url prefix, through an HTTP server deployed on port 8181. 

We exposed the service using the `demo` URL prefix, and the initial resource is `generators`. Thus, our service is available at the following URL: 

    azrael:rest mosser$ curl -w "\n" http://localhost:8181/cxf/demo/generators/
    ["demogen"]

 
## Testing

### Step #8: Unit testing

Service can (**must?**) be unit tested as classical code, from an internal point of view. Maven expects the JUnit test code to be under the `test/java` directory. Actually, unit testing should have been done **before** the deployment phase.

### Step #9: Functional testing with SoapUI

We consider here an up and running installation of SoapUI (Open-source version). Create a new _REST Project_, select _Import WADL...` option and give the url of the WADL contract of your service (http://localhost:8181/cxf/demo?_wadl).

**Remark**: WADL was supposed to be the REST equivalent of WSDL/SOAP. Actually no one really use it anymore (or never used it at all), contrarily to WSDL that is a de facto standard on the SOAP universe. 

SoapUI generates a set of test request for each operation exposed in the service, and you can use it to interact with your system.
