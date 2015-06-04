package fixed2free;

import java.util.Comparator;

public class SymbolComparator implements Comparator<Symbol> {

	public int compare(Symbol o1, Symbol o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
