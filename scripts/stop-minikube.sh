#! /bin/sh

kubectl delete deployment postgres-deployment
kubectl delete deployment payments-demo-quarkus-app
minikube stop
#minikube delete --all
