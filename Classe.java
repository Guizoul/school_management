




public class Classe {
	int idClasse ;
	int Capacity ;
	boolean iscc;
	
	
	
	Classe(int id , int capacity , boolean isCc){
		this.idClasse= id;
		this.Capacity =capacity ;
		this.iscc =isCc;
	}
	
	int getClasseId() {
		return this.idClasse;
	}
	
	boolean isCc() {
		return this.iscc;
	}
	
	int getCapcity() {
		return this.Capacity;
	}
	@Override
	public String toString() {
		return "classe d'id : " + this.getClasseId() + " capacity : "+ this.getCapcity()+" cc:"+this.isCc();
	}

}