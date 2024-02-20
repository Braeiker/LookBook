package Database;

public class Vendite {
    private int id;
    private int idDress;
    private int idUser;

    public Vendite(int id, int idDress, int idUser) {
        this.id = id;
        this.idDress = idDress;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDress() {
        return idDress;
    }

    public void setIdDress(int idDress) {
        this.idDress = idDress;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
