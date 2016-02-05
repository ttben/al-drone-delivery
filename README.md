# al-drone-delivery

![Build status](https://travis-ci.org/ttben/al-drone-delivery.svg?branch=master)

# Team

Benjamin Benni (AL)
Nabil El Moussaid (IAM)
Tom Guillermin (AL)
Sébastien Pétillon (AL)
Etienne Strobbe (IAM)

# Installation

## Requirements 

The current software needs java 8 and maven 3 to be built and executed.
Every other dependencies are bundled into the project.

## Build

This is a full maven project.
Follow these steps to build the entire project:

- Clone the current repository
- Execute following command : `mvn clean install` on the project root folder
- This will download a certain amout of dependencies and will build the servers, the app and run the tests. It may take a while. As we embbed all our dependencies, building servers jars can take some seconds.

## Start it

There are 3 mains :

- `central > Central.Main`, that starts the central server
- `warehouse > Warehouse.Main`, that starts the warehouse server
- `app > App.main`, that starts the app in standalone/demo mode

## Try it

By executing the `Central.Main`, the central will generate tour from random/fake shipping location. It will serve the result of its processing on `http://localhost:8282/shipping-clustering.html`. By refreshing the page, you will generate another random set of shipping therefore another tour description.

By executing the `App.main`, it will display a Swing window. Its size is tiny as it suited better for the demonstration context (video project, low definition, etc..). The demo scenario is waiting for a console input : `'t', 'h', 'a' or 'b'` to simulate that respectively the Truck, the Shipper, the DroneA or DroneB has finished his current action.
The test file `app > test > AppTest.java` provides a complete example of what needs to be typed in this scenario.

The build history of this project is [available here](https://travis-ci.org/ttben/al-drone-delivery/builds)

Repository of AL's project : drone delivery -- Si5 / Polytech Nice Sophia
