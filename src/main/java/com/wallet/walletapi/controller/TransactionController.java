package com.wallet.walletapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.walletapi.dto.CommonResponse;
import com.wallet.walletapi.exception.NotFoundException;
import com.wallet.walletapi.interfaces.IAccount;
import com.wallet.walletapi.interfaces.ITransaction;
import com.wallet.walletapi.model.Transaction;

/**
 * @RIFQIABRORY web developer
 * enigma batch II
 */
@RestController
@CrossOrigin
public class TransactionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    public static final String URL_REQUEST_TRANSACTION_LIST = "transactions/{accountNumber}";
    public static final String URL_REQUEST_TRANSACTION_BY_ID = "transaction/{id}";
    public static final String URL_REQUEST_TRANSACTION_TOPUP = "transaction/topup";
    public static final String URL_REQUEST_TRANSACTION_TRANSFER = "transaction/transfer";
    public static final String URL_REQUEST_TRANSACTION_WITHDRAW = "transaction/withdraw";

    @Autowired
    private ITransaction it;

    @Autowired
    private IAccount ia;

    @CrossOrigin
    @GetMapping(value = URL_REQUEST_TRANSACTION_LIST)
    public CommonResponse<List<Transaction>> getTransactionList(@PathVariable(name = "accountNumber") int accountNumber) throws NotFoundException {
        List<Transaction> transactions = it.getListTransaction1(accountNumber);
        CommonResponse<List<Transaction>> respon = new CommonResponse<>();

        if (!transactions.isEmpty()) {
            respon.setData(transactions);
        } else {
            throw new NotFoundException("444", "Transaction list doesn't exist!");
        }
        return respon;
    }

    @CrossOrigin
    @PostMapping(value = URL_REQUEST_TRANSACTION_TOPUP)
    public CommonResponse<Transaction> createTopup(@RequestBody Transaction transaction) {
        CommonResponse<Transaction> cust = new CommonResponse<>();
        double balance = ia.getBalance(transaction.getAnCredit());
        double result = balance + transaction.getAmount();
        Transaction cst = it.topUp(transaction);
        cust.setData(cst);
        System.out.println("Data : " + cust);
        ia.updateBalance(transaction.getAnCredit(), result);
        return cust;
    }

    @CrossOrigin
    @PostMapping(value = URL_REQUEST_TRANSACTION_TRANSFER)
    public CommonResponse<Transaction> createTransfer(@RequestBody Transaction transaction) throws NotFoundException {
        CommonResponse<Transaction> cust = new CommonResponse<>();
        double balanceDebit = ia.getBalance(transaction.getAnDebit());
        double balanceCredit = ia.getBalance(transaction.getAnCredit());
        if (balanceDebit < transaction.getAmount()) {
            LOGGER.error(String.format("Balance %d not enough", transaction.getAnDebit()));
            throw new NotFoundException("100", String.format("Balance  %d not enough", transaction.getAnDebit()));
        } else if (transaction.getAnDebit() == transaction.getAnCredit()) {
            LOGGER.error(String.format("Balance %d not enough", transaction.getAnDebit()));
            throw new NotFoundException("100", String.format("not permitted because of your own account"));
        } else {

            double resultCredit = balanceCredit + transaction.getAmount();

            double resultDebit = balanceDebit - transaction.getAmount();
            Transaction cst = it.transfer(transaction);
            cust.setData(cst);
            ia.updateBalance(transaction.getAnCredit(), resultCredit);
            ia.updateBalance(transaction.getAnDebit(), resultDebit);
        }

        return cust;
    }

    @PostMapping(value = URL_REQUEST_TRANSACTION_WITHDRAW)
    public CommonResponse<Transaction> createWithdraw(@RequestBody Transaction transaction) throws NotFoundException {
        CommonResponse<Transaction> cust = new CommonResponse<>();
        double balance = ia.getBalance(transaction.getAnDebit());
        if (balance < transaction.getAmount()) {
            LOGGER.error(String.format("Balance %d not enough", transaction.getAnDebit()));
            throw new NotFoundException("100", String.format("Balance  %d not enough", transaction.getAnDebit()));
        } else {
            double result = balance - transaction.getAmount();
            Transaction cst = it.widthraw(transaction);
            cust.setData(cst);
            ia.updateBalance(transaction.getAnDebit(), result);
        }
        return cust;
    }

    @CrossOrigin
    @GetMapping(URL_REQUEST_TRANSACTION_BY_ID)
    public CommonResponse<Transaction> getCustomerEntity(@PathVariable(name = "id") int account_number) throws NotFoundException {

        Transaction transaction = it.getById(account_number);
        CommonResponse<Transaction> resp = new CommonResponse<>();
        if (transaction == null) {
            LOGGER.error(String.format("Customer ID %d not found", account_number));
            throw new NotFoundException("101", String.format("Customer ID %d not found", account_number));
        } else {
            resp.setData(transaction);
        }
        return resp;
    }
}
