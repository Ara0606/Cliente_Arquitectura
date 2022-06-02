package microservicio.cliente.BL;

import microservicio.cliente.DAO.ClienteDAO;
import microservicio.cliente.DTO.Saldo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ClienteBL {
    public final ClienteDAO clienteDAO;
    @Autowired
    public ClienteBL(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }
    public String newClienteBL (String first_name, String last_name, String gender, int age,String address, int card_id){
        clienteDAO.NewCliente(first_name, last_name, gender, age, address, card_id);
        return "ok";
    }
    public int ConsultaSaldo(int id){
        Saldo saldo =new Saldo();
        saldo.setClient_id(id);
        return clienteDAO.ConsultaSaldo(saldo);
    }
    public String Retiro(int monto, int id){
        return clienteDAO.Retiro(monto,id);
    }
    public String Deposito(int monto, int id){
        return clienteDAO.Deposito(monto,id);
    }
}