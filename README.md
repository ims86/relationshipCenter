Projeto desenvolvido com Java 17 e Spring Boot 3

H2-CONSOLE
http://localhost:8080/h2-console/

REST

GET ATTENDANTS
localhost:8080/attendants
localhost:8080/attendants/1

POST ATTENDANTS
localhost:8080/attendants

Exemplo JSON
{
    "name": "Teste - POST",
    "calledType": "OTHERS"
}

GET CALLEDS
localhost:8080/requests
localhost:8080/requests/called/1

POST CALLED
localhost:8080/requests

Exemplo JSON
{
    "type": "CARDS",
    "title": "Teste - POST",
    "description": "Descrição teste - POST"
}

PUT CALLEDS
localhost:8080/requests/called/1

Exemplo JSON
{
    "attendant": {
        "id": 1
    },
    "type": "CARDS",
    "status": "FINISHED"
}
