package exercises.s7;

import java.math.BigDecimal;

// enkapsulacja - chroń dostępd do stanu obiektów
class BankTransfer {
    private TransferStatus status = TransferStatus.PENDING;
    private final String from;
    private final String to;
    private final BigDecimal amount;
    private final TransferType transferType;

    BankTransfer(TransferStatus status, String from, String to, BigDecimal amount, TransferType transferType) {
        this.status = status;
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

    public String getTo() {
        return to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransferType getTransferType() {
        return transferType;
    }
}

enum TransferStatus {
    PENDING, COMPLETE, INCOMPLETE, FAILED
}

enum TransferType {
    ELIXIR, STANDARD, BLIK, INTERNAL, TAX
}
