### Using keyclock to authenticate users

https://www.keycloak.org/documentation

Docker install:
https://www.keycloak.org/getting-started/getting-started-docker

Command:
sudo docker run -p 8181:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:23.0.4 start-dev

Configuration:
issuer: http://localhost:8181/realms/estudou-realm

Tutorial on self registration:
https://www.baeldung.com/keycloak-user-registration