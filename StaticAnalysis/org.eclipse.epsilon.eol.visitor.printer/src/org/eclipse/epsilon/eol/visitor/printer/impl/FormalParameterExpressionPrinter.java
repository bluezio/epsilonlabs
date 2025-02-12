package org.eclipse.epsilon.eol.visitor.printer.impl;

import org.eclipse.epsilon.eol.metamodel.AnyType;
import org.eclipse.epsilon.eol.metamodel.FormalParameterExpression;
import org.eclipse.epsilon.eol.metamodel.Type;
import org.eclipse.epsilon.eol.metamodel.visitor.EolVisitorController;
import org.eclipse.epsilon.eol.metamodel.visitor.FormalParameterExpressionVisitor;
import org.eclipse.epsilon.eol.visitor.printer.context.PrinterContext;

public class FormalParameterExpressionPrinter extends FormalParameterExpressionVisitor<PrinterContext, Object>{

	@Override
	public Object visit(FormalParameterExpression formalParameterExpression,
			PrinterContext context,
			EolVisitorController<PrinterContext, Object> controller) {
		String result = "";
		result += controller.visit(formalParameterExpression.getName(), context);
		Type resolvedType = formalParameterExpression.getResolvedType();
		if(resolvedType != null)
		{
			if (resolvedType instanceof AnyType) {
				if (((AnyType)resolvedType).isDeclared()) {
					result += " : ";
				}
			}
			else {
				result += " : ";
			}
			result += controller.visit(formalParameterExpression.getResolvedType(), context); 
		}
		return result;
	}

}
