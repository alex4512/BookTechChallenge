


# BookChallengeAPI




### Intro


The goal of the exercise is to create a web API that will allow consumers to search for books, get details
for a specific book and review a book.


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


### System Requirements

- maven
- java 8
- an internet connection
---------
The API generates the following HTTP Status Codes

### HTTP Status Codes


- **200** - OK "The requested was fulfilled/The response was as expected"
- **201** - Created "The server succeeded and created the resource"
- **404** - Not Found "The server didn't find the requested resource, or will not disclose that one exists"
- **400** - Generic Exception "The server cannot or will not process the request"



---
**Unless otherwise specified, there's no requirement for a request body**

The following two endpoints are avaible for the BookChallengeAPI


## 1. Book

**relative url for resource**:  /book    
e.g.    
<sub>http://localhost:8080/v1/book
</sub>


1. ##### HTTP Method GET
   ###### relative_url : /byName?name=<your_search_terms>
   ###### function: Retrieves a list (size 32) of book results from the Gutendex api
   e.g.


    curl --location --request GET \
    'http://localhost:8080/v1/books/byName?name=love

Sample response body

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


## 2. Review

**relative url for resource**:  /review    
e.g.    
<sub>http://localhost:8080/v1/review
</sub>
1. ###### HTTP Method GET
###### relative_url : /{bookId}
###### function: if the bookId exists in the Gutendex api **AND** reviews have been created for that book, details of the book will be returned weaved with an aggregated list of reviews and the average rating for that book


e.g.

    curl --location --request GET 'http://localhost:8080/v1/review/68283'

Sample response body

    {
        "book": {
            "id": 68283,
            "title": "The call of Cthulhu",
            "languages": [
                "en"
            ],
            "authors": [
                {
                    "name": "Lovecraft, H. P. (Howard Phillips)",
                    "birth-year": null,
                    "death-year": null
                }
            ],
            "downlad_count": null
        },
        "reviews": [
            "From a legacy perspective, amazing. From a sleeping perspective, detrimental!",
            "A classic!",
            "might be good, wouldn't know"
        ],
        "rating": 4.0
    }


2. ###### HTTP Method POST
###### relative_url :
###### function: if the bookId exists in the Gutendex api and the rating is in the range of 1-5 (inclusive), a review will be created for the book

e.g.

    curl --location --request POST 'http://localhost:8080/v1/review' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "id": 68283,
    "rating" : 4,
    "review": "A classic!"
    }'

Sample response body

      {
      "message": "created",
      "reviewId": 5
      }

 
### Useful links
https://inloop.github.io/sqlite-viewer/