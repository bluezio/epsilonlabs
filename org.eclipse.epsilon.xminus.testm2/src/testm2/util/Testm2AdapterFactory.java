/**
 */
package testm2.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import testm2.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see testm2.Testm2Package
 * @generated
 */
public class Testm2AdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Testm2Package modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Testm2AdapterFactory() {
		if (modelPackage == null) {
			modelPackage = Testm2Package.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Testm2Switch<Adapter> modelSwitch =
		new Testm2Switch<Adapter>() {
			@Override
			public Adapter caseProject(Project object) {
				return createProjectAdapter();
			}
			@Override
			public Adapter caseWP(WP object) {
				return createWPAdapter();
			}
			@Override
			public Adapter caseThing(Thing object) {
				return createThingAdapter();
			}
			@Override
			public Adapter caseThingWithNoId(ThingWithNoId object) {
				return createThingWithNoIdAdapter();
			}
			@Override
			public Adapter caseConcreteThing(ConcreteThing object) {
				return createConcreteThingAdapter();
			}
			@Override
			public Adapter caseConcreteThing2(ConcreteThing2 object) {
				return createConcreteThing2Adapter();
			}
			@Override
			public Adapter caseTask(Task object) {
				return createTaskAdapter();
			}
			@Override
			public Adapter caseDeliverable(Deliverable object) {
				return createDeliverableAdapter();
			}
			@Override
			public Adapter caseEffort(Effort object) {
				return createEffortAdapter();
			}
			@Override
			public Adapter casePartner(Partner object) {
				return createPartnerAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link testm2.Project <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see testm2.Project
	 * @generated
	 */
	public Adapter createProjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link testm2.WP <em>WP</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see testm2.WP
	 * @generated
	 */
	public Adapter createWPAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link testm2.Thing <em>Thing</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see testm2.Thing
	 * @generated
	 */
	public Adapter createThingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link testm2.ThingWithNoId <em>Thing With No Id</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see testm2.ThingWithNoId
	 * @generated
	 */
	public Adapter createThingWithNoIdAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link testm2.ConcreteThing <em>Concrete Thing</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see testm2.ConcreteThing
	 * @generated
	 */
	public Adapter createConcreteThingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link testm2.ConcreteThing2 <em>Concrete Thing2</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see testm2.ConcreteThing2
	 * @generated
	 */
	public Adapter createConcreteThing2Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link testm2.Task <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see testm2.Task
	 * @generated
	 */
	public Adapter createTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link testm2.Deliverable <em>Deliverable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see testm2.Deliverable
	 * @generated
	 */
	public Adapter createDeliverableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link testm2.Effort <em>Effort</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see testm2.Effort
	 * @generated
	 */
	public Adapter createEffortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link testm2.Partner <em>Partner</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see testm2.Partner
	 * @generated
	 */
	public Adapter createPartnerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //Testm2AdapterFactory
