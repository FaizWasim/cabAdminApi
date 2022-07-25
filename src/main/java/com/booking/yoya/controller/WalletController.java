package com.booking.yoya.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.yoya.dto.WalletDto;
import com.booking.yoya.response.BaseMessageResponse;
import com.booking.yoya.response.ServiceResponse;
import com.booking.yoya.service.WalletService;

@RestController
@RequestMapping("api/v1/wallet")
public class WalletController {
	private static Logger logger = LogManager.getLogger();
	@Autowired
	private WalletService walletService;

	@PostMapping(value = "create")
	public ServiceResponse<?> createNewWallet(@RequestBody WalletDto walletDto) {
		return new ServiceResponse<BaseMessageResponse>(walletService.createNewWallet(walletDto));
	}

	@GetMapping(value = "list")
//	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> countryList(@RequestParam(name = "walletId", required = false) Optional<Long> walletId) {
		logger.info("city list in controller");
		try {
			if (!walletId.isPresent())

				return new ServiceResponse<BaseMessageResponse>(walletService.getAllWallets());
			else {

				if (walletId.get() != null) {

					return new ServiceResponse<BaseMessageResponse>(walletService.walletById(walletId.get()));
				}
			}
		} catch (Exception e) {
			logger.error("exeption= " + e);
			e.printStackTrace();
			return new ServiceResponse<BaseMessageResponse>(
					BaseMessageResponse.builder().message("success").status(false).data(e.getMessage()).build());
		}

		return new ServiceResponse<BaseMessageResponse>(walletService.getAllWallets());
	}

	@DeleteMapping(value = "{walletId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> deleteCity(@PathVariable Long walletId) {
		return new ServiceResponse<BaseMessageResponse>(walletService.deleteWallet(walletId));
	}

	@GetMapping(value = "{customerId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> walletByCustomerId(@PathVariable Long customerId) {
		return new ServiceResponse<BaseMessageResponse>(walletService.walletByCustomerId(customerId));
	}

	@GetMapping(value = "{driverId}")
	@PreAuthorize("hasRole('Admin')")
	public ServiceResponse<?> walletByDriverId(@PathVariable Long driverId) {
		return new ServiceResponse<BaseMessageResponse>(walletService.walletByDriverId(driverId));
	}

}
