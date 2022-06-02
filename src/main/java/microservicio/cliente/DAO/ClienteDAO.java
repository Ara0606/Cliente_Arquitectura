package microservicio.cliente.DAO;
import microservicio.cliente.DTO.Saldo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
@Component
public class ClienteDAO {
    private  DataSource dataSource;
    @Autowired
    public ClienteDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String Deposito(Double cant, int id){
        Saldo saldo=new Saldo();
        saldo.setClient_id(id);
        int mon= ConsultaSaldo(saldo);
        int canti= (int) (mon+cant);
        System.out.println("llegamos a crear");
        String sql = "UPDATE consulta_saldo  "+
                "SET saldo = (?) "+
                "WHERE cliente_client_id = (?)";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement param = conn.prepareStatement(sql);
        )
        {
            param.setInt(1, canti);
            param.setInt(2,id);

            param.executeUpdate();

        } catch (Exception e) {
            //TODO: handle exception

        }
        return sql;
    }
    public String NewCliente(String first_name, String last_name, String gender, int age,String address, int card_id){

        System.out.println("llegamos a crear");
        String sql = "INSERT INTO cliente ( first_name, last_name, gender, age, address, card_card_id) VALUES ( ?, ?, ?, ?, ?, ?)";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement param = conn.prepareStatement(sql);
        )
        {
            System.out.print(first_name+last_name+gender+age+address+card_id);
            param.setString(1, first_name);
            param.setString(2,last_name);
            param.setString(3, gender);
            param.setInt(4, age);
            param.setString(5,address);
            param.setInt(6, card_id);
            param.executeUpdate();

        } catch (Exception e) {
            //TODO: handle exception

        }
        System.out.println(sql);
        return sql;
    }
    public Integer ConsultaSaldo(Saldo saldo){
        Integer data = 0;

        String sql = "SELECT saldo FROM public.consulta_saldo WHERE cliente_client_id = (?)";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement param = conn.prepareStatement(sql);
        )
        {
            param.setInt(1,saldo.getClient_id());
            ResultSet rs = param.executeQuery();
            while (rs.next()){
                data =  data + rs.getInt("saldo");
            }
            rs.close();
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Error no existe el registro");
        }
        return data;
    }
    public String Retiro(int monto2, int id ) {
        System.out.println("Entrando a update");
        String mensaje;
        Saldo saldo=new Saldo();
        saldo.setClient_id(id);
        int monto=ConsultaSaldo(saldo);
        if (monto-monto2>0){


            String sql = "UPDATE consulta_saldo "+
                    "SET saldo = (?) "+
                    "WHERE cliente_client_id = (?)";
            try (
                    Connection conn = dataSource.getConnection();
                    PreparedStatement param = conn.prepareStatement(sql);
            )
            {
                param.setDouble(1, monto-monto2);
                param.setInt(2,id);

                param.executeUpdate();

            } catch (Exception e) {
                //TODO: handle exception
                System.out.println(e);

            }
            mensaje="si";
        }
        else {
            mensaje="no";
        }
        return mensaje;
    }
    public String Deposito(int monto2, int id ) {
        String mensaje;
        Saldo saldo=new Saldo();
        saldo.setClient_id(id);
        int monto=ConsultaSaldo(saldo);
        String sql = "UPDATE consulta_saldo "+
                "SET saldo = (?) "+
                "WHERE cliente_client_id = (?)";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement param = conn.prepareStatement(sql);
        )
        {
            param.setDouble(1, monto+monto2);
            param.setInt(2,id);

            param.executeUpdate();

        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
        }
        mensaje="se actualizo";
        return mensaje;
    }
}
