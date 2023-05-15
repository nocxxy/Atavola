package Tables;

public class Table {
	private int id;
	private int numero;
	private int nbPlaces;
	private String etat;
	private String reservation;
	
	public Table(int id, int numero, int nbPlaces, String etat, String reservation) {
		this.id = id;
		this.numero = numero;
		this.nbPlaces = nbPlaces;
		this.etat = etat;
		this.reservation = reservation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getReservation() {
		return reservation;
	}

	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	
	

}
