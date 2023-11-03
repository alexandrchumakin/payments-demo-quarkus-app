eval $(minikube -p minikube docker-env)
mvn versions:set -DnextSnapshot -DgenerateBackupPoms=false
mvn versions:set -DremoveSnapshot -DgenerateBackupPoms=false
./mvnw clean install -Dquarkus.container-image.build=true -DskipTests=true
kubectl delete deployments payments-demo-quarkus-app
kubectl apply -f target/kubernetes/minikube.yml
