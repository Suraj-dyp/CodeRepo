package com.tpt.bonzai.code;

public class EODAttributes {
	
	private int tradeId;
	private String commodity;
	private double quantity;
	private double tradePrice;
	private String tradePriceCurrency;
	private double marketPrice;
	private String marketPriceCurrency;
	private double profitNLoss;
	private int transferId;
	
	public EODAttributes(int tradeId, String commodity, double quantity, double tradePrice,
			String tradePriceCurrency, double marketPrice, String marketPriceCurrency) {
		super();
		this.tradeId = tradeId;
		this.commodity = commodity;
		this.quantity = quantity;
		this.tradePrice = tradePrice;
		this.tradePriceCurrency = tradePriceCurrency;
		this.marketPrice = marketPrice;
		this.marketPriceCurrency = marketPriceCurrency;
	}
	
	public EODAttributes(int transferId, int tradeId, String commodity, double quantity, double tradePrice, String tradePriceCurrency,
			double marketPrice, String marketPriceCurrency) {
		super();
		this.transferId = transferId;
		this.tradeId = tradeId;
		this.commodity = commodity;
		this.quantity = quantity;
		this.tradePrice = tradePrice;
		this.tradePriceCurrency = tradePriceCurrency;
		this.marketPrice = marketPrice;
		this.marketPriceCurrency = marketPriceCurrency;
	}

	public double getProfitNLoss() {
		return profitNLoss;
	}

	public void setProfitNLoss(double profitNLoss) {
		this.profitNLoss = profitNLoss;
	}

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
		
	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public void setTradePrice(double tradePrice) {
		this.tradePrice = tradePrice;
	}
	public void setTradePriceCurrency(String tradePriceCurrency) {
		this.tradePriceCurrency = tradePriceCurrency;
	}
	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public void setMarketPriceCurrency(String marketPriceCurrency) {
		this.marketPriceCurrency = marketPriceCurrency;
	}
	public int getTradeId() {
		return tradeId;
	}
	public String getCommodity() {
		return commodity;
	}
	public double getQuantity() {
		return quantity;
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

	@Override
	public String toString() {
		return "EODAttributes [tradeId=" + tradeId + ", commodity=" + commodity + ", quantity=" + quantity
				+ ", tradePrice=" + tradePrice + ", tradePriceCurrency=" + tradePriceCurrency + ", marketPrice="
				+ marketPrice + ", marketPriceCurrency=" + marketPriceCurrency + "]";
	}
	
	

}
