

This is version 1.0 of the api, therefore the relative url for the running microservices is v1
e.g. curl --location --request GET 'http://localhost:8080/v1'
assuming that the application is running on your local machine on port 8080

The availability of the API can be verified by quering the root url in /v1
i.e. by running the command curl --location --request GET 'http://localhost:8080/v1/'

The response would be an HTTP  200 with a response body "UP"


The following resources are exposed via the API

- Review
- Book
-------
The API generates the following HTTP Status Codes

# HTTP Status Codes

- 200-OK "The requested was fulfilled/The response was as expected"
- 201-Created "The server succeeded and created the resource"
- 404-Not Found "The server didn't find the requested resource, or will not disclose that one exists"
- 400-Generic Exception "The server cannot or will not process the request"



---
The following two endpoints are avaible for the [BookChallengeAPI]

Unless otherwise specified, there's no requirement for a request body



# Book
    relative url:  /book
    e.g. http://localhost:8080/v1/book
    
    endpoints :
        HTTP Method GET
    e.g. #request
    function: - Retrieves a list of book from the Gutendex api 
    e.g.
    curl --location --request GET \
    'http://localhost:8080/v1/books/byName?name=$your_search_term_here$
    
Sample result payload

    {
        "count": 567,
        "next": "https://gutendex.com/books/?page=2&search=love",
        "previous": null,
        "results": [
        {
            "id": 68283,
            "title": "The call of Cthulhu",
            "languages": ["en"],
            "authors": [
                {
                "name": "Lovecraft, H. P. (Howard Phillips)",
                "birth-year": null,
                "death-year": null
                }
            ],
        "downlad_count": null
        }]
    }
    

     

# Review
    relative url:  /review
    
    HTTP Method POST
    function: - Creation of a  
    e.g. #request
    curl --location --request POST 'http://localhost:8080/v1/review' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "id": 68283,
    "rating" : 4,
    "review": "A classic!"
    }'