package com.tpt.bonzai.menu;

import java.sql.Date;
import java.util.Scanner;

import com.tpt.bonzai.eod.EOD;
import com.tpt.bonzai.eod.TradeRetrieval;
import com.tpt.bonzai.eod.TransferRetrieval;
import com.tpt.bonzai.report.EODReport;
import com.tpt.bonzai.report.ReportByCommodity;
import com.tpt.bonzai.report.ReportByCounterpart;
import com.tpt.bonzai.report.ReportByInternalCompany;
import com.tpt.bonzai.report.ReportByStorage;
import com.tpt.bonzai.report.ReportByTradeId;
import com.tpt.bonzai.report.ReportUtility;


public class Menu {
	
	private ReportUtility reportUtility = new ReportUtility();
	private Scanner scanner = new Scanner(System.in);
	private String menu = "1.Run EOD\n2.Show Reports\n3.Exit\nEnter your choice:";
	private String subMenu = "1.Show Profit/Loss by Trade ID\n2.Show Profit/Loss by Commodity"
			+ "\n3.Show Profit/Loss by Counterpart\n4.Show Profit/Loss by Interanl Company\n5.Show Profit/Loss by Warehouse\n6.Return to Main Menu";
	
	public void menuDisplay() {
		do {
			System.out.println(menu);
			int choice = scanner.nextInt();
			if(choice==1) {
				runEOD();
			}
			else if(choice==2) {
				do {
					System.out.println(subMenu);
					int subChoice = scanner.nextInt();
					if(subChoice==1) {
						//Report using tradeId
						ReportByTradeId reportByTradeId = new ReportByTradeId();
						reportByTradeId.generateReport(reportUtility.getTradeId());
					}
					else if(subChoice==2) {
						//Report using commodity						
						ReportByCommodity reportByCommodity = new ReportByCommodity();
						reportByCommodity.generateReport();
					}
					else if(subChoice==3) {
						//Report using counterpart
						ReportByCounterpart reportbyCounterpart = new ReportByCounterpart();
						reportbyCounterpart.generateReport();
					}
					else if(subChoice==4) {
						//Report using internal company
						ReportByInternalCompany reportByInternalCompany = new ReportByInternalCompany();
						reportByInternalCompany.generateReport();
					}
					else if(subChoice==5) {
						//Report using warehouse
						ReportByStorage reportByStorage = new ReportByStorage();
						reportByStorage.generateReport();
					}
					else if(subChoice==6) {
						break;
					}
					else {
						System.out.println("Invalid choice!");
						continue;
					}
				}while(true);
			}
			else if(choice==3) {
				break;
			}
			else {
				System.out.println("Invalid choice!");
				continue;
			}
			
		}while(true);
		
		scanner.close();

	}

	private void runEOD() {
		
		Date date = reportUtility.getSQLDate(reportUtility.getDate());
		
		TradeRetrieval tradeRetrieval = new TradeRetrieval();
		tradeRetrieval.readTrade();
		tradeRetrieval.storeTrade();
		//tradeRetrieval.displayListTrades();
		
		EOD eod = new EOD();
		eod.setForex(date);
		eod.storeEOD(tradeRetrieval.getListTrades(), date);
		
		TransferRetrieval transferRetrieval = new TransferRetrieval();
		transferRetrieval.readTransfer();
		transferRetrieval.storeTransfer();
		//transferRetrieval.displayListTransfers();
		
		eod.storeEOD(transferRetrieval.getListTransfers(), date, true);
		
		EODReport  eodReport = new EODReport();
		eodReport.generateReport(date);
	}

}
