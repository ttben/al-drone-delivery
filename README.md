# SOA1: Integration & ESB Cookbook

  * Author: [Sébastien Mosser](mosser@i3s.unice.fr)
  * Reviewer: [Mireille Blay-Fornarino](blay@i3s.unice.fr)
  * Version: 2.0 (2015)

This repository is a set or _recipes_ to be used as a support for the SOA-1 course. The goal of a _recipe_ is to describe an up and running system, and how one can reproduce it step by step.

**This is not a lab session. Students are not expected to reproduce this system as is. The point is to adapt these recipes to your own problem.**

## Technological Environment

The first version of this course (2012) was using Petals ESB. It then evolves to Mule (2013, 2014). Based on the feedback received from the students and according to the 8 weeks time box of the course, we finally decided to re-implement it (again...) using the open source suite developed by Apache. Thus, the technological stack is the following:

  * Enterprise Service Bus: [Apache Service Mix](http://servicemix.apache.org/) (6.0.0)
  * Routing: [Apache Camel](http://camel.apache.org/) (2.15.2)
  * Service Definition: [Apache CXF](http://cxf.apache.org/)
    * SOAP: JAX-WS specification
    * REST: JAX-RS specification

## Use Case

The integration scenario developed in this cookbook binds a _Tax Paying System_ (TPS) to a _Tax Payer Information System_ (TAIS). The TAIS contains personal information about each taxpayer in the country. The TPS implementation and hosting are outsourced. Taxes can be computed using the "simple" or "advanced" method, according to a threshold based on raw income (changing each year). The goal of the integration is to support:

  * The automation of a mailing campaign, sending to each tax payer the tax form with the computed amount of taxes;
  * The definition of a web-based system where a tax payer can consult the amount of taxes to be payed.

## Repository Architecture

The file system is organized as the following:

  * [`webservices/soap`](https://github.com/polytechnice-si/5A-2015-SOA-1/tree/develop/webservices/soap): example of SOAP-based web services
  * [`webservices/rest`](https://github.com/polytechnice-si/5A-2015-SOA-1/tree/develop/webservices/rest): example of REST-based web services
  * [`flows`](https://github.com/polytechnice-si/5A-2015-SOA-1/tree/develop/flows): integration flows binding TPS and TAIS together.

## Building and deploying the examples

Use maven to build the examples from the root directory:

    azrael:5A-2015-SOA-1 mosser$ mvn clean package

The compilation process should end wit a ``BUILD SUCESS`` information message. It produces 3 JARs artefacts:

    azrael:5A-2015-SOA-1 mosser$ find . -name '*.jar'
      ./flows/target/ws-flows-1.0.jar
      ./webservices/rest/target/ws-rest-1.0.jar
      ./webservices/soap/target/ws-soap-1.0.jar

To deploy the system inside the ESB, simply copy these artefacts to the `deploy`  directory of ServiceMix (here installed in the `servicemix` directory):

    azrael:5A-2015-SOA-1 mosser$ find . -name '*.jar' \
                                   -exec cp {} servicemix/deploy/. \; 
    
When starting Service mix, the artefacts are shown in the list of deployed components:

```
azrael:servicemix mosser$ ./bin/servicemix
Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=256M; support was removed in 8.0
Please wait while Apache ServiceMix is starting...
100% [========================================================================]

Karaf started in 12s. Bundle stats: 242 active, 243 total
 ____                  _          __  __ _      
/ ___|  ___ _ ____   _(_) ___ ___|  \/  (_)_  __
\___ \ / _ \ '__\ \ / / |/ __/ _ \ |\/| | \ \/ /
 ___) |  __/ |   \ V /| | (_|  __/ |  | | |>  < 
|____/ \___|_|    \_/ |_|\___\___|_|  |_|_/_/\_\

  Apache ServiceMix (6.0.0)

karaf@root>bundle:list | grep SOA1
278 | Active | 80 | 1.0 | SOA1 :: Integration Flows with Apache Camel                            
281 | Active | 80 | 1.0 | SOA1 :: REST-based implementation          
282 | Active | 80 | 1.0 | SOA1 :: SOAP-based implementation          
``` 

### ServiceMix dependencies

If not already deployed, you'll have to install the following dependencies in your installation of ServiceMix:

    karaf@root> feature:install camel-csv camel-http camel-saxon camel-spring-ws camel-servlet
    
Reboot your service mix shell to avoid KarafExceptions thrown in the console.
 
## Running the examples

### Exposed Web Services

  * RPC implementation using SOAP:
    *  Tax Computation Service: [http://localhost:8181/cxf/TaxComputation?wsdl](http://localhost:8181/cxf/TaxComputation?wsdl) (built as a JAX-WS service)
    *  Tax Form consultation service: [http://localhost:8181/cxf/TaxAccessService?wsdl](http://localhost:8181/cxf/TaxAccessService?wsdl) (built as an integration flow using Camel)
  * Resource-based implementation:
    * UUID generator: [http://localhost:8181/cxf/demo?_wadl](http://localhost:8181/cxf/demo?_wadl) (built as a JAX-RS service)
    * Tax Form consultation service: [http://localhost:8181/camel/rest/taxForm/{person_uid}](http://localhost:8181/camel/rest/taxForm/{person_uid}) (built as an integration flow using Camel)

### Demonstration Scenario

  1. Start the ServiceMix ESB using the following command: `./bin/servicemix`
  2. Copy one of the integration flow [datasets](https://github.com/polytechnice-si/5A-2015-SOA-1/tree/develop/flows/datasets) into the directory named `./camel/input` (create it if not existing)
  3. Look at the file generated in the directory named `./camel/output` (create it if not existing)
  4. Consult the Tax form service:
    * As a resource, with for example the following URL: [Kevin's tax form](http://localhost:8181/camel/rest/taxForm/2B4A2849-4D5D-FC55-96AF-F88022CDB64D) 
    * Using a SOAP client like SoapUI: [http://localhost:8181/cxf/TaxAccessService?wsdl](http://localhost:8181/cxf/TaxAccessService?wsdl)

    