package microservicio.cliente.DTO;

public class Cliente {
    private int id;
    private String first_name;
    private String last_name;
    private String gender;
    private int age;
    private String address;
    private int card_card_id;


    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCard_card_id() {
        return card_card_id;
    }

    public void setCard_card_id(int card_card_id) {
        this.card_card_id = card_card_id;
    }
}
