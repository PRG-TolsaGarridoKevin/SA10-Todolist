package es.progcipfpbatoi.todolistspring.model.entities;

public enum TipusPrioritat {
	ALTA, MITJANA, BAIXA;
	
	public static TipusPrioritat fromText(String prioritat) {
		switch (prioritat.toUpperCase()) {
        case "ALTA":
            return TipusPrioritat.ALTA;
        case "MITJANA":
            return TipusPrioritat.MITJANA;
        case "BAIXA":
            return TipusPrioritat.BAIXA;
        default:
            throw new IllegalArgumentException("TipusPrioritat no existent: " + prioritat);
		}
	}
}
