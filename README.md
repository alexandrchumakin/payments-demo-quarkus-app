# payments-demo-quarkus-app

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Technical stack

- [Java 17](https://docs.oracle.com/en/java/javase/17/install/overview-jdk-installation.html)
- [Maven 3.9](https://maven.apache.org/install.html)
- [Docker 24](https://docs.docker.com/desktop/)
- [Kubectl 1.28](https://docs.aws.amazon.com/eks/latest/userguide/install-kubectl.html)
- [Minikube 1.31](https://minikube.sigs.k8s.io/docs/start/)

## App prerequisites
To run app locally it's required to start a postgres instance separately, e.g. with docker container:
```shell
docker run --name my-postgres-container -e POSTGRES_USER=myuser -e POSTGRES_PASSWORD=mypassword -e POSTGRES_DB=payments -d -p 5432:5432 postgres:15.4
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Access application
All endpoints require basic authorization. 
For testing purpose we have a few users automatically pre-configured. 

### Create new payment
```shell
curl --location 'http://localhost:8080/payments' \
--header 'Authorization: Basic am9objpqb2hu' \
--header 'Content-Type: application/json' \
--data '{
    "amount": 11.13, 
    "currency": "USD", 
    "name": "Test Consumer"
  }'
```
### Get all payments
```shell
curl  --header 'Authorization: Basic am9objpqb2hu' http://localhost:8080/payments
```

### Delete payment by id
```shell
curl --head --header 'Authorization: Basic am9objpqb2hu' -X DELETE http://localhost:8080/payments/{id}
```

## Running in Kubernetes cluster
For a testing purpose, there are shell [scripts](scripts) to work with the app in a kubernetes cluster using `minikube`.

Please, refer to requirements for installation guidance. 

### Run minikube cluster
`chmod +x scripts/*` (required only once)

```shell
./scripts/start-minikube.sh
```

### Access pod via localhost
First, find application's pod name with:
`kubectl get pods`

Then, forward its port via:
`kubectl port-forward ${container-name} 8080:8080`

### Access app via ingress
After minikube cluster is started, just run in any terminal (and leave it running while you want to use ingress)
```shell
minikube tunnel 
```
Then, you can access service via [http://payments-app.com](http://payments-app.com),
e.g. 
```shell
curl --header 'Authorization: Basic am9objpqb2hu' http://payments-app.com/payments
```

### Stop minikube cluster
```shell
./scripts/stop-minikube.sh
```

### Re-deploy new version of app
```shell
./scripts/re-deploy.sh
```

### Troubleshoot

You can check app logs in minikube with: `kubectl logs deployments/payments-demo-quarkus-app`


## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/payments-demo-quarkus-app-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- Hibernate ORM ([guide](https://quarkus.io/guides/hibernate-orm)): Define your persistent model with Hibernate ORM and Jakarta Persistence
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC
- RESTEasy Classic JSON-B ([guide](https://quarkus.io/guides/rest-json)): JSON-B serialization support for RESTEasy Classic

## Provided Code

### Hibernate ORM

Create your first JPA entity

[Related guide section...](https://quarkus.io/guides/hibernate-orm)



### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
