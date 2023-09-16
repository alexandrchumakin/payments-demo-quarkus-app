#! /bin/sh

minikube start
eval $(minikube -p minikube docker-env)
./mvnw clean install -Dquarkus.container-image.build=true -DskipTests=true
kubectl apply -f src/main/resources/postgres-k8s.yaml
kubectl apply -f target/kubernetes/minikube.yml

sleep 5
kubectl get deployments

