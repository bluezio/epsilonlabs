/**
 * Generated with Acceleo
 */
package ewe.parts.forms;

// Start of user code for imports
import org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.parts.CompositePropertiesEditionPart;
import org.eclipse.emf.eef.runtime.ui.parts.PartComposer;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.BindingCompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionSequence;
import org.eclipse.emf.eef.runtime.ui.parts.sequence.CompositionStep;
import org.eclipse.emf.eef.runtime.ui.utils.EditingUtils;
import org.eclipse.emf.eef.runtime.ui.widgets.FormUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import ewe.parts.EweViewsRepository;
import ewe.parts.UsesNestedElementPropertiesEditionPart;
import ewe.providers.EweMessages;


// End of user code

/**
 * 
 * 
 */
public class UsesNestedElementPropertiesEditionPartForm extends CompositePropertiesEditionPart implements IFormPropertiesEditionPart, UsesNestedElementPropertiesEditionPart {

	protected Text ref;
	protected Text as;
	protected Text optional;



	/**
	 * Default constructor
	 * @param editionComponent the {@link IPropertiesEditionComponent} that manage this part
	 * 
	 */
	public UsesNestedElementPropertiesEditionPartForm(IPropertiesEditionComponent editionComponent) {
		super(editionComponent);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart#
	 *  createFigure(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit)
	 * 
	 */
	public Composite createFigure(final Composite parent, final FormToolkit widgetFactory) {
		ScrolledForm scrolledForm = widgetFactory.createScrolledForm(parent);
		Form form = scrolledForm.getForm();
		view = form.getBody();
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		view.setLayout(layout);
		createControls(widgetFactory, view);
		return scrolledForm;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.parts.IFormPropertiesEditionPart#
	 *  createControls(org.eclipse.ui.forms.widgets.FormToolkit, org.eclipse.swt.widgets.Composite)
	 * 
	 */
	public void createControls(final FormToolkit widgetFactory, Composite view) {
		CompositionSequence usesNestedElementStep = new BindingCompositionSequence(propertiesEditionComponent);
		CompositionStep propertiesStep = usesNestedElementStep.addStep(EweViewsRepository.UsesNestedElement.Properties.class);
		propertiesStep.addStep(EweViewsRepository.UsesNestedElement.Properties.ref);
		propertiesStep.addStep(EweViewsRepository.UsesNestedElement.Properties.as);
		propertiesStep.addStep(EweViewsRepository.UsesNestedElement.Properties.optional);
		
		
		composer = new PartComposer(usesNestedElementStep) {

			@Override
			public Composite addToPart(Composite parent, Object key) {
				if (key == EweViewsRepository.UsesNestedElement.Properties.class) {
					return createPropertiesGroup(widgetFactory, parent);
				}
				if (key == EweViewsRepository.UsesNestedElement.Properties.ref) {
					return 		createRefText(widgetFactory, parent);
				}
				if (key == EweViewsRepository.UsesNestedElement.Properties.as) {
					return 		createAsText(widgetFactory, parent);
				}
				if (key == EweViewsRepository.UsesNestedElement.Properties.optional) {
					return 		createOptionalText(widgetFactory, parent);
				}
				return parent;
			}
		};
		composer.compose(view);
	}
	/**
	 * 
	 */
	protected Composite createPropertiesGroup(FormToolkit widgetFactory, final Composite parent) {
		Section propertiesSection = widgetFactory.createSection(parent, Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		propertiesSection.setText(EweMessages.UsesNestedElementPropertiesEditionPart_PropertiesGroupLabel);
		GridData propertiesSectionData = new GridData(GridData.FILL_HORIZONTAL);
		propertiesSectionData.horizontalSpan = 3;
		propertiesSection.setLayoutData(propertiesSectionData);
		Composite propertiesGroup = widgetFactory.createComposite(propertiesSection);
		GridLayout propertiesGroupLayout = new GridLayout();
		propertiesGroupLayout.numColumns = 3;
		propertiesGroup.setLayout(propertiesGroupLayout);
		propertiesSection.setClient(propertiesGroup);
		return propertiesGroup;
	}

	
	protected Composite createRefText(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, EweMessages.UsesNestedElementPropertiesEditionPart_RefLabel, propertiesEditionComponent.isRequired(EweViewsRepository.UsesNestedElement.Properties.ref, EweViewsRepository.FORM_KIND));
		ref = widgetFactory.createText(parent, ""); //$NON-NLS-1$
		ref.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		widgetFactory.paintBordersFor(parent);
		GridData refData = new GridData(GridData.FILL_HORIZONTAL);
		ref.setLayoutData(refData);
		ref.addFocusListener(new FocusAdapter() {
			/**
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(UsesNestedElementPropertiesEditionPartForm.this, EweViewsRepository.UsesNestedElement.Properties.ref, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, ref.getText()));
			}
		});
		ref.addKeyListener(new KeyAdapter() {
			/**
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(UsesNestedElementPropertiesEditionPartForm.this, EweViewsRepository.UsesNestedElement.Properties.ref, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, ref.getText()));
				}
			}
		});
		EditingUtils.setID(ref, EweViewsRepository.UsesNestedElement.Properties.ref);
		EditingUtils.setEEFtype(ref, "eef::Text"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(EweViewsRepository.UsesNestedElement.Properties.ref, EweViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createAsText(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, EweMessages.UsesNestedElementPropertiesEditionPart_AsLabel, propertiesEditionComponent.isRequired(EweViewsRepository.UsesNestedElement.Properties.as, EweViewsRepository.FORM_KIND));
		as = widgetFactory.createText(parent, ""); //$NON-NLS-1$
		as.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		widgetFactory.paintBordersFor(parent);
		GridData asData = new GridData(GridData.FILL_HORIZONTAL);
		as.setLayoutData(asData);
		as.addFocusListener(new FocusAdapter() {
			/**
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(UsesNestedElementPropertiesEditionPartForm.this, EweViewsRepository.UsesNestedElement.Properties.as, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, as.getText()));
			}
		});
		as.addKeyListener(new KeyAdapter() {
			/**
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(UsesNestedElementPropertiesEditionPartForm.this, EweViewsRepository.UsesNestedElement.Properties.as, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, as.getText()));
				}
			}
		});
		EditingUtils.setID(as, EweViewsRepository.UsesNestedElement.Properties.as);
		EditingUtils.setEEFtype(as, "eef::Text"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(EweViewsRepository.UsesNestedElement.Properties.as, EweViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}

	
	protected Composite createOptionalText(FormToolkit widgetFactory, Composite parent) {
		FormUtils.createPartLabel(widgetFactory, parent, EweMessages.UsesNestedElementPropertiesEditionPart_OptionalLabel, propertiesEditionComponent.isRequired(EweViewsRepository.UsesNestedElement.Properties.optional, EweViewsRepository.FORM_KIND));
		optional = widgetFactory.createText(parent, ""); //$NON-NLS-1$
		optional.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		widgetFactory.paintBordersFor(parent);
		GridData optionalData = new GridData(GridData.FILL_HORIZONTAL);
		optional.setLayoutData(optionalData);
		optional.addFocusListener(new FocusAdapter() {
			/**
			 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void focusLost(FocusEvent e) {
				if (propertiesEditionComponent != null)
					propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(UsesNestedElementPropertiesEditionPartForm.this, EweViewsRepository.UsesNestedElement.Properties.optional, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, optional.getText()));
			}
		});
		optional.addKeyListener(new KeyAdapter() {
			/**
			 * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
			 * 
			 */
			@Override
			@SuppressWarnings("synthetic-access")
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR) {
					if (propertiesEditionComponent != null)
						propertiesEditionComponent.firePropertiesChanged(new PropertiesEditionEvent(UsesNestedElementPropertiesEditionPartForm.this, EweViewsRepository.UsesNestedElement.Properties.optional, PropertiesEditionEvent.COMMIT, PropertiesEditionEvent.SET, null, optional.getText()));
				}
			}
		});
		EditingUtils.setID(optional, EweViewsRepository.UsesNestedElement.Properties.optional);
		EditingUtils.setEEFtype(optional, "eef::Text"); //$NON-NLS-1$
		FormUtils.createHelpButton(widgetFactory, parent, propertiesEditionComponent.getHelpContent(EweViewsRepository.UsesNestedElement.Properties.optional, EweViewsRepository.FORM_KIND), null); //$NON-NLS-1$
		return parent;
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionListener#firePropertiesChanged(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
	 * 
	 */
	public void firePropertiesChanged(IPropertiesEditionEvent event) {
		// Start of user code for tab synchronization
		
		// End of user code
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.UsesNestedElementPropertiesEditionPart#getRef()
	 * 
	 */
	public String getRef() {
		return ref.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.UsesNestedElementPropertiesEditionPart#setRef(String newValue)
	 * 
	 */
	public void setRef(String newValue) {
		if (newValue != null) {
			ref.setText(newValue);
		} else {
			ref.setText(""); //$NON-NLS-1$
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.UsesNestedElementPropertiesEditionPart#getAs()
	 * 
	 */
	public String getAs() {
		return as.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.UsesNestedElementPropertiesEditionPart#setAs(String newValue)
	 * 
	 */
	public void setAs(String newValue) {
		if (newValue != null) {
			as.setText(newValue);
		} else {
			as.setText(""); //$NON-NLS-1$
		}
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.UsesNestedElementPropertiesEditionPart#getOptional()
	 * 
	 */
	public String getOptional() {
		return optional.getText();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ewe.parts.UsesNestedElementPropertiesEditionPart#setOptional(String newValue)
	 * 
	 */
	public void setOptional(String newValue) {
		if (newValue != null) {
			optional.setText(newValue);
		} else {
			optional.setText(""); //$NON-NLS-1$
		}
	}




	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.emf.eef.runtime.api.parts.IPropertiesEditionPart#getTitle()
	 * 
	 */
	public String getTitle() {
		return EweMessages.UsesNestedElement_Part_Title;
	}

	// Start of user code additional methods
	
	// End of user code


}
