#! /bin/sh

minikube start
eval $(minikube -p minikube docker-env)
./mvnw clean install -Dquarkus.container-image.build=true -DskipTests=true
kubectl apply -f src/main/resources/postgres-k8s.yaml
kubectl apply -f target/kubernetes/minikube.yml

sleep 5
kubectl get deployments

minikube addons enable ingress
minikube addons enable ingress-dns

kubectl wait --for=condition=ready pod -l "app.kubernetes.io/name=payments-demo-quarkus-app" --timeout 240s
kubectl apply -f src/main/resources/app-ingress.yaml

sleep 5
kubectl get pods -n ingress-nginx
