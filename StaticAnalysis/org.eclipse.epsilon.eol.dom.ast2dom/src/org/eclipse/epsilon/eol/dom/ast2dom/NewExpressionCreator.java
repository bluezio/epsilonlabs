package org.eclipse.epsilon.eol.dom.ast2dom;

import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.dom.DomElement;
import org.eclipse.epsilon.eol.dom.Expression;
import org.eclipse.epsilon.eol.dom.NewExpression;
import org.eclipse.epsilon.eol.dom.Type;
import org.eclipse.epsilon.eol.parse.EolParser;

public class NewExpressionCreator extends ExpressionCreator{

	@Override
	public boolean appliesTo(AST ast) {
		return false;
		/*if(ast.getType() == EolParser.VAR && ast.getText().equals("new"))
		{
			return true;
		}
		else {
			return false;
		}*/
	}

	@Override
	public DomElement create(AST ast, DomElement container,
			Ast2DomContext context) {
		
		NewExpression expression = context.getEolFactory().createNewExpression(); //create a new expression
		this.setAssets(ast, expression, container); //set assets
		
		AST nameAst = ast.getChild(0);
		
		
		AST typeAst = ast.getChild(1); //must have a type
		expression.setResolvedType((Type) context.getEolElementCreatorFactory().createDomElement(typeAst, expression, context)); //set resolved type to be the type
		
		
		if(ast.getNumberOfChildren() > 1) //if there are any siblings, means there are parameters
		{
			AST paramListAst = ast.getChild(1); //fetch parameter ast
			for(AST parameterAst : paramListAst.getChildren()) //process parameters
			{
				expression.getParameters().add((Expression) context.getEolElementCreatorFactory().createDomElement(parameterAst, expression, context));
			}
		}	
		return expression;
	}

}
