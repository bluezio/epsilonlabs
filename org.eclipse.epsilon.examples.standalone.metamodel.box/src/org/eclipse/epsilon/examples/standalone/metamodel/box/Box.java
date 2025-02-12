/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.epsilon.examples.standalone.metamodel.box;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Box</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.epsilon.examples.standalone.metamodel.box.Box#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.epsilon.examples.standalone.metamodel.box.Box#getThings <em>Things</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.epsilon.examples.standalone.metamodel.box.BoxPackage#getBox()
 * @model
 * @generated
 */
public interface Box extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.epsilon.examples.standalone.metamodel.box.BoxPackage#getBox_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.examples.standalone.metamodel.box.Box#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Things</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.examples.standalone.metamodel.box.Thing}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Things</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Things</em>' containment reference list.
	 * @see org.eclipse.epsilon.examples.standalone.metamodel.box.BoxPackage#getBox_Things()
	 * @model containment="true"
	 * @generated
	 */
	EList<Thing> getThings();

} // Box
