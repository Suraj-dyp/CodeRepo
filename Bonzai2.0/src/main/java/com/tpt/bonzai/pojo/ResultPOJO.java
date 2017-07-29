package com.tpt.bonzai.pojo;

public class ResultPOJO {
	
	private int tradeId;
	private String commodity;
	private double quantity;
	private double tradePrice;
	private String tradePriceCurrency;
	private double marketPrice;
	private String marketPriceCurrency;
	private double profitAndLoss;
	private int transferId;
	private String warehouse;
	private double avgProfitAndLossForWarehouse;
	private String counterpart;
	private String eodDate; 
	
	
	
	public ResultPOJO(int tradeId, double quantity, double tradePrice, double marketPrice, double profitAndLoss, String eodDate) {
		super();
		this.tradeId = tradeId;
		this.quantity = quantity;
		this.tradePrice = tradePrice;
		this.marketPrice = marketPrice;
		this.profitAndLoss = profitAndLoss;
		this.eodDate = eodDate;
		 
	}
	public ResultPOJO(int tradeId, double quantity, double tradePrice, double marketPrice, double profitAndLoss, String eodDate, int transferId) {
		super();
		this.tradeId = tradeId;
		this.tradePrice = tradePrice;
		this.quantity = quantity;
		
		this.marketPrice = marketPrice;
		this.profitAndLoss = profitAndLoss;
		this.eodDate = eodDate;
		this.transferId = transferId;
	}
	
	public ResultPOJO(int tradeId, double quantity, double tradePrice, double marketPrice, double profitAndLoss, String eodDate, int transferId, String counterpart) {
		super();
		this.tradeId = tradeId;
		this.tradePrice = tradePrice;
		this.quantity = quantity;
		this.counterpart = counterpart;
		this.marketPrice = marketPrice;
		this.profitAndLoss = profitAndLoss;
		this.eodDate = eodDate;
		this.transferId = transferId;
	}
	
	
	public ResultPOJO(String commodity, double avgProfitAndLossForWarehouse) {
		super();
		this.commodity = commodity;
		this.avgProfitAndLossForWarehouse = avgProfitAndLossForWarehouse;
	}
	
	public int getTradeId() {
		return tradeId;
	}
	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
	
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getTradePrice() {
		return tradePrice;
	}
	public void setTradePrice(double tradePrice) {
		this.tradePrice = tradePrice;
	}
	public double getAvgProfitAndLossForWarehouse() {
		return avgProfitAndLossForWarehouse;
	}
	public void setAvgProfitAndLossForWarehouse(double avgProfitAndLossForWarehouse) {
		this.avgProfitAndLossForWarehouse = avgProfitAndLossForWarehouse;
	}
	
	public double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getCommodity() {
		return commodity;
	}
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	public String getTradePriceCurrency() {
		return tradePriceCurrency;
	}
	public void setTradePriceCurrency(String tradePriceCurrency) {
		this.tradePriceCurrency = tradePriceCurrency;
	}
	public String getMarketPriceCurrency() {
		return marketPriceCurrency;
	}
	public void setMarketPriceCurrency(String marketPriceCurrency) {
		this.marketPriceCurrency = marketPriceCurrency;
	}
	public double getProfitAndLoss() {
		return profitAndLoss;
	}
	public void setProfitAndLoss(double profitAndLoss) {
		this.profitAndLoss = profitAndLoss;
	}
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getCounterpart() {
		return counterpart;
	}
	public void setCounterpart(String counterpart) {
		this.counterpart = counterpart;
	}
	public String getEodDate() {
		return eodDate;
	}
	public void setEodDate(String eodDate) {
		this.eodDate = eodDate;
	}
	@Override
	public String toString() {
		return "eodDate=" + eodDate + ", tradeId=" + tradeId + ", quantity=" + quantity + ", tradePrice=" + tradePrice
				+ ", marketPrice=" + marketPrice + ", profitAndLoss=" + profitAndLoss;
	}
	
}
