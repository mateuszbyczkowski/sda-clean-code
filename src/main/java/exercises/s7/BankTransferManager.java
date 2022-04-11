package exercises.s7;

import pl.sda.refactorapp.annotation.Autowired;

import java.math.BigDecimal;

class BankTransferManager {
    @Autowired
    private BankTransferSender bankTransferSender;

    void sendBankTransfer(BankTransfer bankTransfer) {
        bankTransfer.setStatus(TransferStatus.INCOMPLETE);
        boolean result = bankTransferSender.send(bankTransfer);

        if (result) {
            bankTransfer.setStatus(TransferStatus.COMPLETE);
        } else {
            bankTransfer.setStatus(TransferStatus.FAILED);
        }
    }
}


class BankTransferController {
    @Autowired
    private BankTransferManager bankTransferManager;

    void send() {
        var transfer = new BankTransfer(TransferStatus.PENDING, "me", "you", BigDecimal.TEN, TransferType.BLIK);
        bankTransferManager.sendBankTransfer(transfer);
    }
}