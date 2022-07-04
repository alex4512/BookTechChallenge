# Introduction


The goal of the exercise is to create a web API that will allow consumers to search for books, get details
for a specific book and review a book. To achieve this, you will be using a 3rd-party api for book details
while the rating system will be implemented from scratch.

----------------------------------
Prerequisite : Java 8, Maven

Packaging : 'mvn clean compile package -P docker' on root

API: Gutendex (https://gutendex.com/) is a free web API that provides book details from the collection of
Project Gutenberg books. The API itself is very simple and it does not require any authentication or
complex requests. In the link of the API there are some instructions and examples for you to use.



![img_1.png](img_1.png)
Note: Gutendex API returns more details for each book, but we are only interested in those showing in
the screenshot.

1. Part 1: Searching for books
   
   [Requirement] : 
    For this part, you will need to create an endpoint that will require a search term (a book title) and it will
    return a response similar to the following based on the result of Gutendex API


```
{
   sample: payload
}

```


2. Part 2: Reviewing a book
For this part of the exercise, you will have to create an endpoint that we can use to rate and review a
specific book. The payload of the request will include the book id as it is returned from part 1, a rating
(0-5) and a freetext review. For example, a sample payload may be like this:

![img_4.png](img_4.png)

The schema of the payload can be changed according to your needs. The ratings and the reviews will be
persisted in a database of your choice (though Sqlite is proposed for simplicity). Validations in the
request payload are needed in order to keep the database safe and clean!


note: as it is returned from part 1 - lookup exists first
  1) exists
   1) validate payload correct
   2) insert





3. Part 3: Getting details of a specific book
In this final part, you will combine data from Gutendex and from the local database with the reviews.
Given a book id, you will have to create an endpoint that returns book details, the average rating and a
list of the reviews submitted for the book. An example response will be similar to the following:


# Acceptance Criteria
● An API consumer can search for books given a title. (Part 1)
● An API consumer can post a review and a rating for a specific book. (Part 2)
● An API consumer can get the details and the average rating of a specific book. (Part 3)
● The services should gracefully handle API errors.
● There is NO need to implement authentication or user management.

---------

# Plan


Analysis
--------------------


1) Break into Steps
2) Create API Definitions
3) Validate  API



    entities
    ----
    book.
    
    author(s)
    
    review
    
    
    
    services
    -------------
    
    GutendexReviews
    
    LocalReviews

    
# Test cases

-- unit testing for RESTful
-- unit testing for gutendex service



1 - Service available   
2 - Exception handling (example)

    //	400 example	
    curl --location -request GET 'http://localhost:8080/v1/books/byName'
    {
    "status": 400,
    "message": "Required request parameter 'name' for method parameter type String is not present",
    "timeStamp": 1656749101319
    }
3 - Response status code
4 - Response payload on success #TODO

    

@Requirements - 

maven
java 11
an internet connection

~ Useful links
https://inloop.github.io/sqlite-viewer/