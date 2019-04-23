package sample;

public class BookTable {
    private int id;
    private String title;
    private int year;
    private int publisherId;
    private String status;

    public BookTable(){
        this.id = 0;
        this.title = "";
        this.year = 0;
        this.publisherId = 0;
        this.status = "";
    }

    public BookTable(int id, String title, int year, int publisherId, String status){
        this.id = id;
        this.title = title;
        this.year = year;
        this.publisherId = publisherId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
