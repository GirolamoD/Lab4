package it.polito.tdp.lab04.model;

public class Corso {
	
	private String cod ;
	private int crediti ;
	private String nome ;
	private int pd;
	
	public Corso(String cod, int crediti, String nome, int pd) {
		this.cod = cod;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public int getCrediti() {
		return crediti;
	}

	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPd() {
		return pd;
	}

	public void setDp(int pd) {
		this.pd = pd;
	}

	@Override
	public String toString() {
		return this.nome+"\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod == null) ? 0 : cod.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Corso))
			return false;
		Corso other = (Corso) obj;
		if (cod == null) {
			if (other.cod != null)
				return false;
		} else if (!cod.equals(other.cod))
			return false;
		return true;
	}


	
	
	
	

}
