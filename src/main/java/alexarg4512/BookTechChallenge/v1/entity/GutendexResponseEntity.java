package alexarg4512.BookTechChallenge.v1.entity;

import java.util.List;

public class GutendexResponseEntity {

    Long count;
    String next;
    String previous;
    List<Book> results;


    public GutendexResponseEntity() {
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Book> getResults() {
        return results;
    }

    public void setResults(List<Book> results) {
        this.results = results;
    }



    @Override
    public String toString() {
        return "GutendexResponseEntity{" +
                "count=" + count +
                ", next='" + next + '\'' +
                ", previous='" + previous + '\'' +
                '}';
    }


}
