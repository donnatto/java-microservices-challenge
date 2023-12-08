# Java Challenge

## Requirements

- You will need a **Java 17** jdk to build the microservices jars
- You will need to have **Docker** installed to create the images and run the containers


## Building jars

### Customers
From the source folder run:
```bash
cd ./customers && ./mvnw clean install -DskipTests
```
### Transactions
From the source folder run:
```bash
cd ./transactions && ./mvnw clean install -DskipTests
```
Then you will have both jars in their corresponding target folder

## Building images

### Customers
From the source folder run:
```shell
cd ./customers && docker build . -t donnatto/customers:0.0.1-SNAPSHOT --platform=linux/amd64
```

### Transactions
From the source folder run:
```shell
cd ./transactions && docker build . -t donnatto/transactions:0.0.1-SNAPSHOT --platform=linux/amd64
```
Now you will have both microservice images created

## Running the microservices

To run the microservices, you only need to use the `docker-compose.yml` file. From the source folder run:

```bash
docker-compose up
```
Now you will have the **Customers** microservice running on port **9001** and the **Transactions** microservice on port **9002**