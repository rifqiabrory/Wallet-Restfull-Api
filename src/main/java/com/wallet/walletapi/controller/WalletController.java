package com.wallet.walletapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.walletapi.dto.CommonResponse;
import com.wallet.walletapi.exception.NotFoundException;
import com.wallet.walletapi.exception.UserException;
import com.wallet.walletapi.interfaces.IWallet;
import com.wallet.walletapi.model.Wallet;

/**
 * @RIFQIABRORY web developer
 * enigma batch II
 */
@RestController
@CrossOrigin
public class WalletController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WalletController.class);

    public static final String URl_REQUEST_WALLET = "wallet"; //create
    public static final String URl_REQUEST_WALLET_LIST = "wallets";//read
    public static final String URl_REQUEST_WALLETS_BY = "wallets/{accountNumber}";//read
    public static final String URl_REQUEST_WALLET_BY_ID = "wallet/{id_wallet}";

    @Autowired
    private IWallet iw;

    @CrossOrigin
    @GetMapping(value = URl_REQUEST_WALLET_LIST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse<List<Wallet>> getWalletsList() throws NotFoundException {
        List<Wallet> wallets = iw.getListWallet();
        CommonResponse<List<Wallet>> respon = new CommonResponse<>();
        if (!wallets.isEmpty()) {
            respon.setData(wallets);
        } else {
            throw new NotFoundException("444", "Wallets doesn't exist!");
        }
        return respon;
    }

    @CrossOrigin
    @GetMapping(value = URl_REQUEST_WALLETS_BY)
    public CommonResponse<List<Wallet>> getWalletList1(@PathVariable(name = "accountNumber") int accountNumber) throws NotFoundException {
        List<Wallet> wallets = iw.getListWallet1(accountNumber);
        CommonResponse<List<Wallet>> respon = new CommonResponse<>();
        if (!wallets.isEmpty()) {
            respon.setData(wallets);
        } else {
            throw new NotFoundException("444", "Wallets doesn't exist!");
        }
        return respon;
    }

    @CrossOrigin
    @PostMapping(value = URl_REQUEST_WALLET)
    public CommonResponse<Wallet> createWallet(@RequestBody Wallet wallet) {
        CommonResponse<Wallet> cust = new CommonResponse<>();
        Wallet cst = iw.save(wallet);
        cust.setData(cst);
        return cust;
    }

    @CrossOrigin
    @GetMapping(value = URl_REQUEST_WALLET_BY_ID)
    public CommonResponse<Wallet> getWalletById(@PathVariable(name = "id_wallet") int id_wallet) throws UserException, NotFoundException {

        Wallet wallet = iw.getById(id_wallet);
        CommonResponse<Wallet> resp = new CommonResponse<>();
        if (wallet == null) {
            LOGGER.error(String.format("Wallet ID %d not found", id_wallet));
            throw new NotFoundException("101", String.format("Wallet ID %d not found", id_wallet));
        } else {
            resp.setData(wallet);
        }
        return resp;
    }

    @CrossOrigin
    @DeleteMapping(value = URl_REQUEST_WALLET_BY_ID)
    public CommonResponse<Wallet> deleteWallet(@PathVariable(name = "id_wallet") int id_wallet) throws NotFoundException {
        Wallet check = iw.getById(id_wallet);
        CommonResponse<Wallet> resp = new CommonResponse<>();
        if (check == null) {
            throw new NotFoundException("444", String.format("Wallet ID %d not found", id_wallet));
        } else {
            resp.setData(iw.delete(check));
        }
        return resp;
    }

    @CrossOrigin
    @PutMapping(value = URl_REQUEST_WALLET_BY_ID)
    public CommonResponse<Wallet> updateCustomerBy(@RequestBody Wallet wallet) throws NotFoundException {
        Wallet check = iw.getById(wallet.getIdWallet());
        CommonResponse<Wallet> resp = new CommonResponse<>();
        if (check == null) {
            throw new NotFoundException("444", "Wallets doesn't updated!");
        } else {
            iw.update(wallet);
            resp.setData(wallet);
        }
        return resp;
    }
}
