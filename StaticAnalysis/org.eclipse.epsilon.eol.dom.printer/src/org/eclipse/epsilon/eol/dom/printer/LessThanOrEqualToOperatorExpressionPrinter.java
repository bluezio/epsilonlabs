package org.eclipse.epsilon.eol.dom.printer;

import org.eclipse.epsilon.eol.metamodel.*;

public class LessThanOrEqualToOperatorExpressionPrinter extends BinaryOperatorExpressionPrinter {

	@Override
	protected String getOperatorSymbol() {
		return "<=";
	}

	@Override
	public boolean appliesTo(EolElement dom) {
		// TODO Auto-generated method stub
		return dom instanceof LessThanOrEqualToOperatorExpression;
	}

}
