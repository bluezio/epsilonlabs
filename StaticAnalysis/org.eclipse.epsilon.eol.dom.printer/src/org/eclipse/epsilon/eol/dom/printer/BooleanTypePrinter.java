package org.eclipse.epsilon.eol.dom.printer;

import org.eclipse.epsilon.eol.metamodel.*;

public class BooleanTypePrinter extends PrimitiveTypePrinter{

	@Override
	public String print(EolElement e, EolElementPrinterFactory f) {
		return "Boolean";
	}

	@Override
	public boolean appliesTo(EolElement dom) {
		// TODO Auto-generated method stub
		return dom instanceof BooleanType;
	}

}
