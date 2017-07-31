package com.tpt.bonzai.pojo;

public class TransferPOJO {
	
	private int tradeId;
	private int transferId;
	private String transferType;
	private String counterpart;
	private String internalCompany;
	private String commodity;
	private double quantity;
	private String uom;
	private double tradePrice;
	private String tradePriceCurrency;
	private double marketPrice;
	private String marketPriceCurrency;
	private String storage;
	
	public TransferPOJO(int tradeId, int transferId, String transferType, String counterpart, String internalCompany,
			String commodity, double quantity, String uom, double tradePrice, String tradePriceCurrency,
			double marketPrice, String marketPriceCurrency, String storage) {
		super();
		this.tradeId = tradeId;
		this.transferId = transferId;
		this.transferType = transferType;
		this.counterpart = counterpart;
		this.internalCompany = internalCompany;
		this.commodity = commodity;
		this.quantity = quantity;
		this.uom = uom;
		this.tradePrice = tradePrice;
		this.tradePriceCurrency = tradePriceCurrency;
		this.marketPrice = marketPrice;
		this.marketPriceCurrency = marketPriceCurrency;
		this.storage = storage;
	}

	@Override
	public String toString() {
		return "TransferPOJO [tradeId=" + tradeId + ", transferId=" + transferId + ", transferType=" + transferType
				+ ", counterpart=" + counterpart + ", internalCompany=" + internalCompany + ", commodity=" + commodity
				+ ", quantity=" + quantity + ", uom=" + uom + ", tradePrice=" + tradePrice + ", tradePriceCurrency="
				+ tradePriceCurrency + ", marketPrice=" + marketPrice + ", marketPriceCurrency=" + marketPriceCurrency
				+ "]";
	}

	public int getTradeId() {
		return tradeId;
	}

	public int getTransferId() {
		return transferId;
	}

	public String getTransferType() {
		return transferType;
	}

	public String getStorage() {
		return storage;
	}

	public String getCounterpart() {
		return counterpart;
	}

	public String getInternalCompany() {
		return internalCompany;
	}

	public String getCommodity() {
		return commodity;
	}

	public double getQuantity() {
		return quantity;
	}

	public String getUom() {
		return uom;
	}

	public double getTradePrice() {
		return tradePrice;
	}

	public String getTradePriceCurrency() {
		return tradePriceCurrency;
	}

	public double getMarketPrice() {
		return marketPrice;
	}

	public String getMarketPriceCurrency() {
		return marketPriceCurrency;
	}
	
	
	

}
