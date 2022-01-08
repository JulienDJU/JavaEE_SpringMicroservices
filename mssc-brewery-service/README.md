[![CircleCI](https://circleci.com/gh/springframeworkguru/mssc-beer-service.svg?style=svg)](https://circleci.com/gh/springframeworkguru/mssc-beer-service)
# MSSC Beer Service

Source code in this repository is to support my online courses.

Learn more about my courses below!
* [Spring Boot Microservices with Spring Cloud](https://www.udemy.com/spring-boot-microservices-with-spring-cloud-beginner-to-guru/?couponCode=GIT_HUB2)


# Default Port Mappings - For Single Host
| Service Name | Port | 
| --------| -----|
| Brewery Beer Service | 8080 |
| [Brewery Beer Order Service](https://github.com/springframeworkguru/mssc-beer-order-service) | 8081 |
| [Brewery Beer Inventory Service](https://github.com/springframeworkguru/mssc-beer-inventory-service) | 8082 |

# Start Artemis docker image

docker run -it --rm -p 8161:8161 -p 61616:61616 vromero/activemq-artemis

Console visible at http://0.0.0.0:8161/console
username: artemis
password: simetraehcapa
