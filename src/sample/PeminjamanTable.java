package sample;

import java.sql.Timestamp;

public class PeminjamanTable {
    private int id;
    private int memberId;
    private int bookId;
    private String tanggalPinjam;
    private String tanggalKembali;
    private String status;

    public PeminjamanTable() {
        this.id = 0;
        this.memberId = 0;
        this.bookId = 0;
        this.tanggalPinjam = "";
        this.tanggalKembali = "";
        this.status = "";
    }

    public PeminjamanTable(int id, int memberId, String tanggalPinjam, String tanggalKembali, String status) {
        this.id = id;
        this.memberId = memberId;
        //this.bookId = bookId;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
        this.status = status;
    }

    public PeminjamanTable(int id, int bookId){
        this.id = id;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public String getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
