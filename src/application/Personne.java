package application;

public class Personne {

	private String nom;
	private String prenom;
	private int age;
	private String situation;
	private int photo;
	//private String photo;
	
	public Personne(String nom, String prenom, int age, String situation, int photo) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.situation = situation;
		this.photo = photo;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the situation
	 */
	public String getSituation() {
		return situation;
	}
	/**
	 * @param situation the situation to set
	 */
	public void setSituation(String situation) {
		this.situation = situation;
	}
	/**
	 * @return the photo
	 */
	public int getPhoto() {
		return photo;
	}
	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(int photo) {
		this.photo = photo;
	}
	
	
	
}
