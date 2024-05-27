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

Create users in keycloak
https://www.youtube.com/watch?v=3nq75mFi8Tg

Playlist using keycloak from scratch to god
https://www.youtube.com/playlist?list=PLHXvj3cRjbzs8TaT-RX1qJYYK2MjRro-P

Configurations

The base endpoints for keycloak are four, follow (More informations available in the postman):

- Token
- Refresh Token
- Instrospect Token
- Logout

Token

Generate the first access token using the base url for openid in keycloak, like the one above, but with a /token at the end and the grant type as password

http://keycloak:8080/realms/estudou-realm/protocol/openid-connect/token

- client_id
- client_secret
- grant_type=password
- username
- password

Refresh Token

Generate the refresh token after the first one expired, the endpoint is the same, the only thing who is changed, is the grant_type, and instead of sending password, is necessary to send a refresh token

http://keycloak:8080/realms/estudou-realm/protocol/openid-connect/token

- client_id
- client_secret
- grant_type=refresh_token
- username
- refresh_token

Instrospect Token

Endpoint to check the current state of the token

http://keycloak:8080/realms/estudou-realm/protocol/openid-connect/introspect

- client_id
- client_secret
- token

Logout

Endpoint to logout the use and kill the session in keycloak

http://keycloak:8080/realms/estudou-realm/protocol/openid-connect/logout

- client_id
- client_secret
- refresh_token

The way to handle user roles

- Go to Keycloak platform
- Go to Clients -> Choose your client -> client scopes -> choose correct scope -> add a mapper -> By configuration -> User realm role

To configure a user as admin of the system
- Go to Users -> Select the user -> Role mapping -> Assign roles -> Filter by clients -> Select the realm admin

PS: information origin:
Answers about the users list
https://stackoverflow.com/questions/48583361/how-can-i-read-all-users-using-keycloak-and-spring

https://www.youtube.com/watch?v=Cltc2nhakSw

