package exercises.s7;

import java.math.BigDecimal;

// enkapsulacja - chroń dostępd do stanu obiektów
class BankTransfer {

    TransferStatus status = TransferStatus.PENDING;
    String from;
    String to;
    BigDecimal amount;
    TransferType transferType;

    BankTransfer(TransferStatus status, String from, String to, BigDecimal amount, TransferType transferType) {
        this.status = status;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.transferType = transferType;
    }
}

enum TransferStatus {
    PENDING, COMPLETE, INCOMPLETE, FAILED
}

enum TransferType {
    ELIXIR, STANDARD, BLIK, INTERNAL, TAX;
}
