package org.eclipse.epsilon.eol.ast2eol.test;

import static org.junit.Assert.*;

import org.eclipse.epsilon.eol.metamodel.AssignmentStatement;
import org.eclipse.epsilon.eol.metamodel.Block;
import org.eclipse.epsilon.eol.metamodel.EolElement;
import org.eclipse.epsilon.eol.metamodel.EolProgram;
import org.eclipse.epsilon.eol.metamodel.ExpressionStatement;
import org.eclipse.epsilon.eol.metamodel.IntegerExpression;
import org.eclipse.epsilon.eol.metamodel.Statement;
import org.eclipse.epsilon.eol.metamodel.VariableDeclarationExpression;
import org.eclipse.epsilon.eol.metamodel.impl.AssignmentStatementImpl;
import org.eclipse.epsilon.eol.metamodel.impl.EolProgramImpl;
import org.eclipse.epsilon.eol.metamodel.impl.ExpressionStatementImpl;
import org.eclipse.epsilon.eol.metamodel.impl.IntegerExpressionImpl;
import org.eclipse.epsilon.eol.metamodel.impl.IntegerTypeImpl;
import org.eclipse.epsilon.eol.metamodel.impl.VariableDeclarationExpressionImpl;
import org.eclipse.epsilon.eol.parse.Eol_EolParserRules.expressionStatement_return;
import org.junit.Test;

public class IntegerTypeCreatorTest {

	@Test
	public void test() {
		EolElement eolElement = AST2EolElementProducer.parseAST("var a = 10;");
		assertEquals(eolElement.getClass(), EolProgramImpl.class);
		
		EolProgram program = (EolProgram) eolElement;
		
		assertEquals(program.getBlock().getStatements().size(), 1);
		
		Block block = program.getBlock();
		
		Statement statement1 = block.getStatements().get(0);
		assertEquals(statement1.getClass(), AssignmentStatementImpl.class);
		AssignmentStatement assignmentStatement1 = (AssignmentStatement) statement1;
		assertEquals(assignmentStatement1.getLhs().getClass(), VariableDeclarationExpressionImpl.class);
		VariableDeclarationExpression lhs = (VariableDeclarationExpression) assignmentStatement1.getLhs();
		assertEquals(lhs.getName().getName(), "a");
		assertEquals(assignmentStatement1.getRhs().getClass(), IntegerExpressionImpl.class);
		IntegerExpression rhs = (IntegerExpression) assignmentStatement1.getRhs();
		assertEquals(rhs.getVal(), 10);
		
		assertEquals(rhs.getResolvedType().getClass(), IntegerTypeImpl.class);
	}
	
	@Test
	public void test1() {
		EolElement eolElement = AST2EolElementProducer.parseAST("var a : Integer;");
		assertEquals(eolElement.getClass(), EolProgramImpl.class);
		
		EolProgram program = (EolProgram) eolElement;
		
		assertEquals(program.getBlock().getStatements().size(), 1);
		
		Block block = program.getBlock();
		
		Statement statement = block.getStatements().get(0);
		assertEquals(statement.getClass(), ExpressionStatementImpl.class);
		
		ExpressionStatement expressionStatement = (ExpressionStatement) statement;
		
		assertEquals(expressionStatement.getExpression().getClass(), VariableDeclarationExpressionImpl.class);
		VariableDeclarationExpression variableDeclarationExpression = (VariableDeclarationExpression) expressionStatement.getExpression();
		
		assertEquals(variableDeclarationExpression.getName().getName(), "a");
		assertEquals(variableDeclarationExpression.getResolvedType().getClass(), IntegerTypeImpl.class);
	}

}
