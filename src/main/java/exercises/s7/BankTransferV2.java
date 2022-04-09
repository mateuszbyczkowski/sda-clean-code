package exercises.s7;

import java.math.BigDecimal;

// enkapsulacja - chroń dostępd do stanu obiektów. Czy jest to dobrze zhermetyzowana klasa?
class BankTransferV2 {

    private TransferStatus status = TransferStatus.PENDING;
    private String from;
    private String to;
    private BigDecimal amount;
    private TransferType transferType;

    BankTransferV2(String from, String to, BigDecimal amount, TransferType transferType) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.transferType = transferType;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }
}
