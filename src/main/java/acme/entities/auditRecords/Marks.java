
package acme.entities.auditRecords;

public enum Marks {

	A_PLUS("A+"), A("A"), B("B"), C("C"), F("F"), F_MINUS("F-");


	private String value;


	Marks(final String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
