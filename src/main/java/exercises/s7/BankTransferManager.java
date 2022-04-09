package exercises.s7;

import pl.sda.refactorapp.annotation.Autowired;

class BankTransferManager {
    @Autowired
    private BankTransferSender bankTransferSender;

    void sendBankTransfer(BankTransfer bankTransfer) {
        bankTransfer.status = TransferStatus.INCOMPLETE;
        boolean result = bankTransferSender.send(bankTransfer);

        if (result) {
            bankTransfer.status = TransferStatus.COMPLETE;
        } else {
            bankTransfer.status = TransferStatus.FAILED;
        }
    }
}
