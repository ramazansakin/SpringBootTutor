package com.sakinramazan.springboot.util;

// Hata mesajlarını merkezi olarak yönetebiliriz.
// Burada class ımız daha da geliştirilebilir.
// Error ID , error description , hangi class tan geldiği gibi daha specific 
// değerler tutup bir yerlerde kaydını tutuabilirz.
public class ErrorMessage {

	private String errorMessage;

	public ErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
