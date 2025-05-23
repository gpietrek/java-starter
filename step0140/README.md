# Java Starter #

## Step 140

receive message from message queue

### Prerequisites
- Java 21 JDK
- Maven
- Docker
- Postman

### Build and run

```shell
mvn clean install docker:run
```

Open in browser: 
- http://localhost:8080/createPerson.html
- http://localhost:8080/findPerson.html
- use Postman!

### Keycloak configuration

- Realm: `java-starter-realm`
- Client ID: `java-starter-client`
- Role: `user`
- Username: `java-starter-user`
- Password: `passKeycloakUser`

#### Keycloak admin user

- Username: `admin`
- Password: `passKeycloakAdmin`
- Admin console: http://localhost:8081/admin/master/console/#/java-starter-realm
