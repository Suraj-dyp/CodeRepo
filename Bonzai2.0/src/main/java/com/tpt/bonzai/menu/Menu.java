package com.tpt.bonzai.menu;

import java.util.HashSet;
import java.util.Scanner;

import com.tpt.bonzai.eod.COB;
import com.tpt.bonzai.eod.CobPOJOCreation;
import com.tpt.bonzai.pojo.CobPOJO;
import com.tpt.bonzai.pojo.ResultPOJO;
import com.tpt.bonzai.report.Report;
import com.tpt.bonzai.report.ResultUsingCounterpartPOJOCreation;
import com.tpt.bonzai.report.ResultUsingTradeAndTransferPOJOCreation;
import com.tpt.bonzai.report.ResultUsingTradeIdPOJOCreation;
import com.tpt.bonzai.report.ResultUsingWarehousePOJOCreation;

public class Menu {
	
	private Scanner scanner = new Scanner(System.in);
	private String menu = "1.Run EOD\n2.Show Reports\n3.Exit\nEnter your choice:";
	private String subMenu = "1.Show Profit/Loss using Trade ID\n2.Show Profit/Loss using Trade ID and Transfer ID"
			+ "\n3.Show Profit/Loss for a Counterpart\n4.Show Profit/Loss for a Warehouse(Group by Commodity)\n5.Return to Main Menu";
	
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
						//Report using trade
						ResultUsingTradeIdPOJOCreation resultUsingTradeIdPOJOCCreation = new ResultUsingTradeIdPOJOCreation();
						resultUsingTradeIdPOJOCCreation.setResultPOJO();
						HashSet<ResultPOJO> listResultPOJO = resultUsingTradeIdPOJOCCreation.getListResultPOJO();
						Report report = new Report();
						report.generateReportUsingTrade(listResultPOJO);
					}
					else if(subChoice==2) {
						//Report using trade and transfer
						ResultUsingTradeAndTransferPOJOCreation resultUsingTradeAndTransferPOJOCreation = new ResultUsingTradeAndTransferPOJOCreation();
						resultUsingTradeAndTransferPOJOCreation.setResultPOJO();
						HashSet<ResultPOJO> listResultPOJO = resultUsingTradeAndTransferPOJOCreation.getListResultPOJO();
						Report report = new Report();
						report.generateReportUsingTradeAndTransfer(listResultPOJO);
					}
					else if(subChoice==3) {
						//Report using counterpart
						ResultUsingCounterpartPOJOCreation resultUsingCounterpartPOJOCreation = new ResultUsingCounterpartPOJOCreation();
						resultUsingCounterpartPOJOCreation.setResultPOJO();
						HashSet<ResultPOJO> listResultPOJO = resultUsingCounterpartPOJOCreation.getListResultPOJO();
						Report report = new Report();
						report.generateReportUsingCounterpart(listResultPOJO);
						
					}
					else if(subChoice==4) {
						//Report using warehouse
						ResultUsingWarehousePOJOCreation resultUsingWarehousePOJOCreation = new ResultUsingWarehousePOJOCreation();
						resultUsingWarehousePOJOCreation.setResultPOJO();
						HashSet<ResultPOJO> listResultPOJO = resultUsingWarehousePOJOCreation.getListResultPOJO();
						Report report = new Report();
						report.generateReportUsingWarehouse(listResultPOJO);
					}
					else if(subChoice==5) {
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
		CobPOJOCreation cobPOJOCreation = new CobPOJOCreation();
		COB cob = new COB();
		
		cobPOJOCreation.setPOJOForEODCalculation();
		HashSet<CobPOJO> listCobPOJO = cobPOJOCreation.getListCObPOJO();
		cob.runEOD(listCobPOJO);
		
		cobPOJOCreation.setPOJOForEODCalculation(true);
		HashSet<CobPOJO> listCobPOJO1 = cobPOJOCreation.getListCObPOJO();
		cob.runEOD(listCobPOJO, true);
		
	}

}
