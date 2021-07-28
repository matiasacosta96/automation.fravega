package automation.fravega.tests;

import org.junit.Assert;
import org.junit.Test;

import automation.fravega.TestBase;

public class BusquedaHeladera extends TestBase {

	@Test
	public void busqueda() {
		driver.get(home.getUrl());
		home.closeModal();
		home.buscarTexto("Heladera");
		home.clickFilterHeladera();
		home.clickFilterSamsung();
		Assert.assertTrue(home.validarTitulos("Samsung"));
		Assert.assertTrue(home.validarCantidadElementos());
		//String breadcrumb = "Heladeras con Freezer";
		String breadcrumb = "Heladeras, Freezers y Cavas";
		Assert.assertTrue(home.validarBreadcrumb(breadcrumb));
	}
}
