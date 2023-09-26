# Instructions
1. Clone the repo
2. Run `mvn clean install`
3. Run `mvn clean spring-boot:run` - the server will start on localhost:8080
4. Try out the various routes with curl or postman
- GET `/contacts`
- POST `/contacts` (see sample request body below)
- GET `/Contacts/${id}`
- PUT `/contacts/{id}` (see sample request body below)
- DELETE `/contacts/${id}`
- GET `/call-list`

Sample request body:
```
{
    "name": {
        "first": "abe",
        "middle": "honest",
        "last": "lincoln"
    },
    "address": {
        "street": "main",
        "city": "washington",
        "state": "IL",
        "zip": "11111"
    },
    "phone": {
        "number": "867-53909",
        "type": "home"
    },
    "email": "abe@honest.com"
}
```