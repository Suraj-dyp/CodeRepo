package com.tpt.bonzai;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		
		EOD.retrieveDataForEodCalculate();
		EOD.insertEodDataInDatabase();
		
		/*EODWithTransfer.retrieveDataForEodCalculate();
		EODWithTransfer.insertEodDataWithTransferInDatabase();*/
	
		//PnLCounterpart.profitNLossForCounterpart();
		//PnLCounterpart.profitNLossForCounterpartGivenDateRange();
		
		//PnLCommodity.profitNLossForCommodities();
		
		//FileWriteForEOD.writeEOD();
		
		/*switch() {
		
		}*/
	}

}
