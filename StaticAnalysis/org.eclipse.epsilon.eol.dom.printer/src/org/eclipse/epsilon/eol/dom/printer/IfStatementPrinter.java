package org.eclipse.epsilon.eol.dom.printer;

import org.eclipse.epsilon.eol.dom.*;



public class IfStatementPrinter implements DomElementPrinter {

	public String print(DomElement e, DomElementPrinterFactory f) {
		IfStatement s = (IfStatement) e;
		String r = "if (" + f.print((DomElement) s.getCondition()) + ") {" + f.newline()
			+ f.indent() + f.print(s.getIfBody()) + f.outdent() + f.newline() + 
			f.whitespace() + "}";
		if (s.getElseBody().size() > 0) {
			r = r + f.newline() + f.whitespace() + "else { " + f.newline() + f.indent() + f.print(s.getElseBody()) 
			+ f.newline() + f.outdent() + f.whitespace() + "}"; 
		}
		return r;
	}

}
