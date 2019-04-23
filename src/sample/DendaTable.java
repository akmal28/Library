package sample;

public class DendaTable {
    private int id, memberId, txId, total;
    private String status;

    public DendaTable() {
        this.id = 0;
        this.memberId = 0;
        this.txId = 0;
        this.total = 0;
        this.status = "";
    }

    public DendaTable(int id, int memberId, int txId, int total, String status) {
        this.id = id;
        this.memberId = memberId;
        this.txId = txId;
        this.total = total;
        this.status = status;
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

    public int getTxId() {
        return txId;
    }

    public void setTxId(int txId) {
        this.txId = txId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
