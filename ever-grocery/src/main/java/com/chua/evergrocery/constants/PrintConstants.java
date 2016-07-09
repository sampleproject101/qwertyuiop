package com.chua.evergrocery.constants;

/**
 * @author Kevin Roy K. Chua
 *
 * @version 1.0, Jul 9, 2016
 * @since 1.0, Jul 9, 2016
 *
 */
public class PrintConstants
{
	public static final String LOCAL_PRINTER_SERVICE = "EPSON TM-T88IV Receipt";
	
	public static final byte [] KICK_DRAWER_CODE = {27, 112, 48, 55, 121};
	
	public static final byte [] CUTTER_CODE = {27, 109};
	
	public static final String LOCAL_HOST = "127.0.0.1";
	
	/** The root element for the printer XML configuration file */
	public static final String ROOT = "PrinterConfiguration";
	
	/** The terminal element for the printer XML configuration file */
	public static final String TERMINAL = "Terminal";
	
	/** The IP address element for the printer XML configuration file */
	public static final String IP_ADDRESS = "IpAddress";
	
	/** The computer name element for the printer XML configuration file */
	public static final String COMPUTER_NAME = "ComputerName";
	
	/** The print service element for the printer XML configuration file */
	public static final String PRINT_SERVICE = "PrintService";
	
	/** The cashier id element for the printer XML configuration file */
	public static final String CASHIER_ID = "CashierId";
	
	/** The counter number element for the printer XML configuration file */
	public static final String COUNTER_NUMBER = "CounterNumber";
	
	/** The file name for the printer XML configuration file */
	public static final String PRINTER_CONFIG = "printer-config.xml";
}