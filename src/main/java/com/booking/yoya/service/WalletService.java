package com.booking.yoya.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.yoya.dao.WalletDao;
import com.booking.yoya.dto.WalletDto;
import com.booking.yoya.model.Wallet;
import com.booking.yoya.response.BaseMessageResponse;

@Service
public class WalletService {

	private static Logger logger = LogManager.getLogger();

	@Autowired
	private WalletDao walletDao;

	public BaseMessageResponse<Object> createNewWallet(WalletDto walletDto) {

		try {
			Wallet wallet = new Wallet();
			wallet.setWalletAmount(walletDto.getWalletAmount());
			wallet.setWalletCustomerId(walletDto.getWalletCustomerId());
			wallet.setWalletDateTime(walletDto.getWalletDateTime());
			wallet.setWalletDescription(walletDto.getWalletDescription());
			wallet.setWalletDriverId(walletDto.getWalletDriverId());
			wallet.setWalletStatus(walletDto.getWalletStatus());
			wallet.setWalletTransactionId(walletDto.getWalletTransactionId());
			wallet.setWalletTransactionType(walletDto.getWalletTransactionType());
			wallet.setWalletType(walletDto.getWalletType());
			wallet.setWalletUseFor(walletDto.getWalletUseFor());

			Wallet savedWallet = walletDao.save(wallet);
			return BaseMessageResponse.builder().message("success").status(true).data(savedWallet).build();
		} catch (Exception e) {
			e.printStackTrace();
			return BaseMessageResponse.builder().message("success").status(true).data(e.getMessage()).build();
		}

	}

	public BaseMessageResponse<Object> getAllWallets() {
		try {

			logger.info("wallet list " + walletDao.findAll());

			List<Wallet> walletList = walletDao.findAll();
			if (walletList.isEmpty()) {
				return BaseMessageResponse.builder().message("success").status(false).data(walletList).build();
			} else {
				List<WalletDto> walletDtoList = walletList.stream().map(new Function<Wallet, WalletDto>() {
					@Override
					public WalletDto apply(Wallet wallet) {
						WalletDto wDto = new WalletDto();
						wDto.setId(wallet.getId());
						wDto.setWalletAmount(wallet.getWalletAmount());
						wDto.setWalletCustomerId(wallet.getWalletCustomerId());
						wDto.setWalletDateTime(wallet.getWalletDateTime());
						wDto.setWalletDescription(wallet.getWalletDescription());
						wDto.setWalletDriverId(wallet.getWalletDriverId());
						wDto.setWalletStatus(wallet.getWalletStatus());
						wDto.setWalletTransactionId(wallet.getWalletTransactionId());
						wDto.setWalletTransactionType(wallet.getWalletTransactionType());
						wDto.setWalletType(wallet.getWalletType());
						wDto.setWalletUseFor(wallet.getWalletUseFor());

						return wDto;
					}
				}).collect(Collectors.toList());

				logger.info("wallet list to be send " + walletDtoList);

				return BaseMessageResponse.builder().message("success").status(true).data(walletDtoList).build();

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exception " + e);
		}
		return BaseMessageResponse.builder().message("Something weny wrong").status(false).build();
	}

	public BaseMessageResponse<Object> deleteWallet(Long walletId) {
		Optional<Wallet> wallet = walletDao.findById(walletId);

		if (!wallet.isPresent()) {
			return BaseMessageResponse.builder().message("wallet not present").status(false).data(wallet).build();

		} else {
			walletDao.deleteById(wallet.get().getId());
			return BaseMessageResponse.builder().message("success").status(true).data(wallet).build();
		}
	}

	public BaseMessageResponse<Object> updateWallet(WalletDto wallet) {

		try {
			Wallet oldWallet = walletDao.findById(wallet.getId()).get();
			if (oldWallet != null) {
				oldWallet.setId(wallet.getId());
				if (wallet.getWalletAmount() == 0)
					oldWallet.setWalletAmount(oldWallet.getWalletAmount());
				else
					oldWallet.setWalletAmount(wallet.getWalletAmount());

				if (wallet.getWalletCustomerId().equals(null))
					oldWallet.setWalletCustomerId(oldWallet.getWalletCustomerId());
				else
					oldWallet.setWalletCustomerId(wallet.getWalletCustomerId());

				if (wallet.getWalletDateTime().isEmpty())
					oldWallet.setWalletDateTime(oldWallet.getWalletDateTime());
				else
					oldWallet.setWalletDateTime(wallet.getWalletDateTime());

				if (wallet.getWalletDescription().isEmpty())
					oldWallet.setWalletDescription(oldWallet.getWalletDescription());
				else
					oldWallet.setWalletDescription(wallet.getWalletDescription());
				if (wallet.getWalletDriverId().equals(null))
					oldWallet.setWalletDriverId(oldWallet.getWalletDriverId());
				else
					oldWallet.setWalletDriverId(wallet.getWalletDriverId());

				if (wallet.getWalletStatus().isEmpty())
					oldWallet.setWalletStatus(oldWallet.getWalletStatus());
				else
					oldWallet.setWalletStatus(wallet.getWalletStatus());
				if (wallet.getWalletTransactionId().equals(null))
					oldWallet.setWalletTransactionId(oldWallet.getWalletTransactionId());
				else
					oldWallet.setWalletTransactionId(wallet.getWalletTransactionId());
				if (wallet.getWalletTransactionType().isEmpty())
					oldWallet.setWalletTransactionType(oldWallet.getWalletTransactionType());
				else
					oldWallet.setWalletTransactionType(wallet.getWalletTransactionType());
				if (wallet.getWalletType().isEmpty())
					oldWallet.setWalletType(oldWallet.getWalletType());
				else
					oldWallet.setWalletType(wallet.getWalletType());
				if (wallet.getWalletUseFor().isEmpty())
					oldWallet.setWalletUseFor(oldWallet.getWalletUseFor());
				else
					oldWallet.setWalletUseFor(wallet.getWalletUseFor());

//				if (wallet.getIsActive() == NumbersConstant.ONE.value)
//					oldWallet.setIsActive(true);
//				else
//					oldWallet.setIsActive(false);

				Wallet savedWallet = walletDao.save(oldWallet);
				return BaseMessageResponse.builder().message("success").status(true).data(savedWallet).build();
			} else {
				return BaseMessageResponse.builder().message("wallet not present").status(false).data(wallet).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return BaseMessageResponse.builder().message("success").status(true).data(e.getMessage()).build();
		}

	}

	public BaseMessageResponse<Object> walletById(Long walletId) {
		Optional<Wallet> wallet = walletDao.findById(walletId);

		if (!wallet.isPresent()) {
			return BaseMessageResponse.builder().message("wallet not present").status(false).data(wallet).build();

		} else {
			WalletDto wDto = new WalletDto();
			wDto.setId(wallet.get().getId());
			wDto.setWalletAmount(wallet.get().getWalletAmount());
			wDto.setWalletCustomerId(wallet.get().getWalletCustomerId());
			wDto.setWalletDateTime(wallet.get().getWalletDateTime());
			wDto.setWalletDescription(wallet.get().getWalletDescription());
			wDto.setWalletDriverId(wallet.get().getWalletDriverId());
			wDto.setWalletStatus(wallet.get().getWalletStatus());
			wDto.setWalletTransactionId(wallet.get().getWalletTransactionId());
			wDto.setWalletTransactionType(wallet.get().getWalletTransactionType());
			wDto.setWalletType(wallet.get().getWalletType());
			wDto.setWalletUseFor(wallet.get().getWalletUseFor());

			return BaseMessageResponse.builder().message("success").status(true).data(wDto).build();
		}
	}

	public BaseMessageResponse<Object> walletByCustomerId(Long customerId) {
		List<Wallet> walletList = walletDao.findByCustomerId(customerId);

		if (walletList.isEmpty()) {
			return BaseMessageResponse.builder().message("wallet not present").status(false).data(walletList).build();

		} else {
			List<WalletDto> walletDtoList = walletList.stream().map(new Function<Wallet, WalletDto>() {
				@Override
				public WalletDto apply(Wallet wallet) {
					WalletDto wDto = new WalletDto();
					wDto.setId(wallet.getId());
					wDto.setWalletAmount(wallet.getWalletAmount());
					wDto.setWalletCustomerId(wallet.getWalletCustomerId());
					wDto.setWalletDateTime(wallet.getWalletDateTime());
					wDto.setWalletDescription(wallet.getWalletDescription());
					wDto.setWalletDriverId(wallet.getWalletDriverId());
					wDto.setWalletStatus(wallet.getWalletStatus());
					wDto.setWalletTransactionId(wallet.getWalletTransactionId());
					wDto.setWalletTransactionType(wallet.getWalletTransactionType());
					wDto.setWalletType(wallet.getWalletType());
					wDto.setWalletUseFor(wallet.getWalletUseFor());

					return wDto;
				}
			}).collect(Collectors.toList());

			return BaseMessageResponse.builder().message("success").status(true).data(walletDtoList).build();
		}
	}

	public BaseMessageResponse<Object> walletByDriverId(Long driverId) {
		List<Wallet> walletList = walletDao.findByDriverId(driverId);

		if (walletList.isEmpty()) {
			return BaseMessageResponse.builder().message("wallet not present").status(false).data(walletList).build();

		} else {
			List<WalletDto> walletDtoList = walletList.stream().map(new Function<Wallet, WalletDto>() {
				@Override
				public WalletDto apply(Wallet wallet) {
					WalletDto wDto = new WalletDto();
					wDto.setId(wallet.getId());
					wDto.setWalletAmount(wallet.getWalletAmount());
					wDto.setWalletCustomerId(wallet.getWalletCustomerId());
					wDto.setWalletDateTime(wallet.getWalletDateTime());
					wDto.setWalletDescription(wallet.getWalletDescription());
					wDto.setWalletDriverId(wallet.getWalletDriverId());
					wDto.setWalletStatus(wallet.getWalletStatus());
					wDto.setWalletTransactionId(wallet.getWalletTransactionId());
					wDto.setWalletTransactionType(wallet.getWalletTransactionType());
					wDto.setWalletType(wallet.getWalletType());
					wDto.setWalletUseFor(wallet.getWalletUseFor());

					return wDto;
				}
			}).collect(Collectors.toList());

			return BaseMessageResponse.builder().message("success").status(true).data(walletDtoList).build();
		}
	}

}
