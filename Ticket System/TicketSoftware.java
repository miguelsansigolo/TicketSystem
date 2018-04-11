//JTabbedPane();
public class TicketSoftware{
	
	public static void main(String[]args){
		Login v = new Login();
		TicketModel m = new TicketModel();
		TicketController ticket = new TicketController(m,v);
	}



}
