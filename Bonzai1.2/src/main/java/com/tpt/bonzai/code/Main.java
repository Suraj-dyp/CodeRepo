package com.tpt.bonzai.code;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author suraj.kumar
 *
 */
public class Main {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException {
		
		String menu = "1.Perform EOD\n2.Show Profit/Loss for a Commodity\n3.Show Profit/Loss for "
				+ "Counterpart\n4.Exit\nEnter your choice:";
		String subMenu = "1.Show Profit/Loss for a Counterpart\n2.Show Profit/Loss for all "
				+ "Counterparts\n3.Return to Main Menu";
		do {
			System.out.println(menu);
			int choice = scanner.nextInt();
			if(choice==1) {
				EODCalculation.retrieveEODAttributes();
				EODCalculation.insertEODAttributesInEODTable();

				EODCalculationWithTransfer.retrieveEODAttributes();
				EODCalculationWithTransfer.insertEODAttributesInEODTable();
				
				FileWriteForEOD.writeEOD();
			}
			else if(choice==2) {
				PnLCommodity.profitNLossForCommodities();
			}
			else if(choice==3) {
				do {
					System.out.println(subMenu);
					int subChoice = scanner.nextInt();
					if(subChoice==1) {
						PnLCounterpart.profitNLossForCounterpartGivenDateRange();
					}
					else if(subChoice==2) {
						PnLCounterpart.profitNLossForCounterpart();
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

		

		
		
		
		
	}

}
