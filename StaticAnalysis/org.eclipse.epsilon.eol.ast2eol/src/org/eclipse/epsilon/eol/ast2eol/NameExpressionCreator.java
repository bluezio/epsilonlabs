package org.eclipse.epsilon.eol.ast2eol;

import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.metamodel.*;
import org.eclipse.epsilon.eol.parse.EolParser;

public class NameExpressionCreator extends ExpressionCreator{

	@Override
	public boolean appliesTo(AST ast) {
		if(
				((ast.getType() == EolParser.FEATURECALL || ast.getType() == EolParser.NAME) && ast.getNumberOfChildren() == 0) 
				||
				(ast.getType() == EolParser.NAME && 
				ast.getNumberOfChildren() == 1 && 
				ast.getChild(0).getText().equals(ast.getText().split("!")[0])))
				//&& !ast.getText().contains("!")) <---this was used in the previous version to parse A!B as a ModelElementType, but ModelElementType is no omitted
				//&& (Character.isLowerCase(ast.getText().charAt(0)) || ast.getText().charAt(0) == '_')) <--- this was used to parse names that begins with lower case letters, this is temproraily omitted
		{
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public EolElement create(AST ast, EolElement container,
			Ast2EolContext context) {
		
		NameExpression expression = (NameExpression) context.getEolFactory().createNameExpression(); //create a NameExpression
		this.setAssets(ast, expression, container);
		
		String name = ast.getText();
			
		if (ast.getFirstChild()!=null) {
			if(!ast.getFirstChild().getText().equals("PARAMETERS") && !ast.getFirstChild().getText().equals("PARAMLIST") && !name.contains("!"))
			{
				while(ast.getFirstChild()!=null)
				{
					ast = ast.getFirstChild();
					name += "(" + ast.getText() + ")";
				}
			}
		}
		
		expression.setName(name);
		//expression.setResolvedType((Type) context.getEolElementCreatorFactory().createDomElement(ast, expression, context));
		
		return expression;
	}

}
