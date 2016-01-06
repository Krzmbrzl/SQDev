/*
 * generated by Xtext
 */
package raven.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

import raven.ui.internal.SQFActivator;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class SQFExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return SQFActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return SQFActivator.getInstance().getInjector(SQFActivator.RAVEN_SQF);
	}
	
}