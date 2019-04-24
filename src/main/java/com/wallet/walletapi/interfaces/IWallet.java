package com.wallet.walletapi.interfaces;

import java.util.List;

import com.wallet.walletapi.exception.NotFoundException;
import com.wallet.walletapi.model.Wallet;

/**
 *   @RIFQIABRORY
 *   web developer
 *   enigma batch II
 * */
public interface IWallet {
	List<Wallet> getListWallet();
	List<Wallet> getListWallet1(int idWallet);
	Wallet getById(int idWallet) throws NotFoundException;
	Wallet save(Wallet wallet);
	Wallet update(Wallet wallet);
	Wallet delete(Wallet idWallet);
}
