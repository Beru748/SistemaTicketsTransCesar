package Service;

import DAO.TicketDao;
import Model.Ticket;
import java.util.List;

public class TicketService {
    private final TicketDao ticketDAO;
    private List<Ticket> tickets;
}
