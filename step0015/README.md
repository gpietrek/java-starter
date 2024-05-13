# Java Starter #

## Step 15

Build mit Maven

### Prerequisites
- Java 21 JDK
- Maven

Prüfung mit:
```shell
mvn -version
```

Erwartetes Ergebnis (Beispiel):
```shell
pache Maven 3.9.6 (bc0240f3c744dd6b6ec2920b3cd08dcc295161ae)
Maven home: /Users/georg/jee/seu/maven
Java version: 21.0.2, vendor: Eclipse Adoptium, runtime: /Library/Java/JavaVirtualMachines/temurin-21.jdk/Contents/Home
Default locale: de_DE, platform encoding: UTF-8
OS name: "mac os x", version: "13.6.3", arch: "aarch64", family: "mac"
```

### Build and run

```shell
mvn clean package
java -classpath target/starter-1.0-SNAPSHOT.jar de.conciso.starter.HelloWorld
```
