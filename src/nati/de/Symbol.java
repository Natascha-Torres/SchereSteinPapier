/**
 * 
 */
package nati.de;

/**
 * @version 1.0.0.0
 * @datum 2012-03-03
 * @author Natascha Torres Ripoll
 */
public class Symbol {
	private String name = null;
	
	public Symbol(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		Symbol s = (Symbol) o;
		return name.equalsIgnoreCase(s.getName());
	}
	
}
