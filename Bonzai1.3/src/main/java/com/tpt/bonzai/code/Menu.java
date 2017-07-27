/**
 * 
 */
package com.tpt.bonzai.code;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author suraj.kumar
 *
 */
public class Menu {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void menuDisplay() {
		String menu = "1.Perform EOD\n2.Show Profit/Loss for Commodities\n3.Show Profit/Loss for "
				+ "Counterpart\n4.Exit\nEnter your choice:";
		String subMenu = "1.Show Profit/Loss for a Counterpart\n2.Show Profit/Loss for all "
				+ "Counterparts\n3.Return to Main Menu";
		int countEOD = 0;
		do {
			System.out.println(menu);
			int choice = scanner.nextInt();
			if(choice==1) {
				countEOD++;
				if(countEOD==1) {
					EODCalculation.retrieveEODAttributes();
					EODCalculation.clearEODTable();
					EODCalculation.insertEODAttributesInEODTable();
	
					EODCalculationWithTransfer.retrieveEODAttributes();
					EODCalculationWithTransfer.insertEODAttributesInEODTable();
					
					FileWriteForEOD.writeEOD();
					
					System.out.println("EOD File created on Desktop.");
				}
				else {
					System.out.println("You are trying to run EOD for second time today...");
				}
			}
			else if(choice==2) {
				PnLCommodity.profitNLossForCommodities();
				System.out.println("PnL File for Commodities created on Desktop.");
			}
			else if(choice==3) {
				do {
					System.out.println(subMenu);
					int subChoice = scanner.nextInt();
					if(subChoice==1) {
						CompanyList.getListOfCompanies();
						boolean validateCounterpart = PnLCounterpart.profitNLossForCounterpartGivenDateRange();
						if(validateCounterpart)
							System.out.println("PnL File for given Counterpart created on Desktop.");
						else 
							System.out.println("Invalid Counterpart!");
					}
					else if(subChoice==2) {
						PnLCounterpart.profitNLossForCounterpart();
						System.out.println("PnL File for all Counterpart created on Desktop.");
					}
					else if(subChoice==3) {
						break;
					}
					else {
						System.out.println("Invalid choice!");
						continue;
					}
				}while(true);
			}
			else if(choice==4) {
				break;
			}
			else {
				System.out.println("Invalid choice!");
				continue;
			}
			
		}while(true);
		
		scanner.close();

	}

	

}
