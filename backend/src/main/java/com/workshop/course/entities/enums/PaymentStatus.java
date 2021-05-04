package com.workshop.course.entities.enums;

public enum PaymentStatus {

		PENDENTE(1),
		QUITADO(2),
		CANCELADO(3);
	
	private int code;

	private PaymentStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static PaymentStatus toEnum(Integer code) {
		
		if (code == null) {
			return null;
		}
		for (PaymentStatus x : PaymentStatus.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid Id: " + code);
	}
}
