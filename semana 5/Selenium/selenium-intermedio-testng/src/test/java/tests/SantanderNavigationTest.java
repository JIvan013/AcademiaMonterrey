package tests;

import base.BaseTest;
import java.util.Set;
import java.util.function.Consumer;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.SantanderHomePage;

public class SantanderNavigationTest extends BaseTest {

    private void validateNavigationFlow(
            SoftAssert softAssertions,
            String scenarioName,
            Consumer<SantanderHomePage> navigationAction) {

        try {
            returnToHomePage();
            SantanderHomePage santanderHome = new SantanderHomePage(browser);
            Set<String> windowHandlesBeforeAction = captureOpenWindows();

            navigationAction.accept(santanderHome);
            focusWindowAfterNavigation(windowHandlesBeforeAction);

            System.out.println("[OK] " + scenarioName + " - Llegamos a: " + browser.getTitle());

        } catch (Exception exception) {
            System.err.println(
                "[ERROR] " 
                + scenarioName 
                + " - " 
                + exception.getClass().getSimpleName() 
                + ": " 
                + exception.getMessage()
            );

            softAssertions.fail(
                scenarioName 
                + " falló -> " 
                + exception.getClass().getSimpleName() 
                + ": " 
                + exception.getMessage()
            );
        }
    }

    @Test(priority = 1)
    public void shouldNavigateThroughPersonasCreditOptions() {
        SoftAssert softAssertions = new SoftAssert();

        validateNavigationFlow(softAssertions, "Personas - Tarjetas de Crédito", SantanderHomePage::navigateToTarjetasCredito);
        validateNavigationFlow(softAssertions, "Personas - Crédito Personal", SantanderHomePage::navigateToCreditoPersonal);
        validateNavigationFlow(softAssertions, "Personas - Crédito Hipotecario", SantanderHomePage::navigateToCreditoHipotecario);
        validateNavigationFlow(softAssertions, "Personas - Simulador Hipoteca", SantanderHomePage::navigateToSimuladorHipoteca);
        validateNavigationFlow(softAssertions, "Personas - Crédito Automotriz", SantanderHomePage::navigateToCreditoAuto);
        validateNavigationFlow(softAssertions, "Personas - Buró de Crédito", SantanderHomePage::navigateToBuroCredito);

        softAssertions.assertAll();
    }

    @Test(priority = 2)
    public void shouldNavigateThroughPersonasDigitalChannels() {
        SoftAssert softAssertions = new SoftAssert();

        validateNavigationFlow(softAssertions, "Personas - Santander Digital", SantanderHomePage::navigateToSantanderDigital);
        validateNavigationFlow(softAssertions, "Personas - App Santander", SantanderHomePage::navigateToAppSantander);
        validateNavigationFlow(softAssertions, "Personas - Santander Web", SantanderHomePage::navigateToSantanderWeb);
        validateNavigationFlow(softAssertions, "Personas - Límite por Transacción", SantanderHomePage::navigateToLimiteTransaccion);

        softAssertions.assertAll();
    }

    @Test(priority = 3)
    public void shouldNavigateThroughPersonasAccountOptions() {
        SoftAssert softAssertions = new SoftAssert();

        validateNavigationFlow(softAssertions, "Personas - Cuentas", SantanderHomePage::navigateToCuentas);
        validateNavigationFlow(softAssertions, "Personas - Cuenta Básica", SantanderHomePage::navigateToCuentaBasica);
        validateNavigationFlow(softAssertions, "Personas - Cuenta Nómina", SantanderHomePage::navigateToCuentaNomina);
        validateNavigationFlow(softAssertions, "Personas - Cuenta Cheques", SantanderHomePage::navigateToCuentaCheques);
        validateNavigationFlow(softAssertions, "Personas - Portabilidad Nómina", SantanderHomePage::navigateToPortabilidadNomina);

        softAssertions.assertAll();
    }

    @Test(priority = 4)
    public void shouldNavigateThroughPersonasInvestmentsAndInsurance() {
        SoftAssert softAssertions = new SoftAssert();

        validateNavigationFlow(softAssertions, "Personas - Fondos de Inversión", SantanderHomePage::navigateToFondosInversion);
        validateNavigationFlow(softAssertions, "Personas - Inversiones a Plazo", SantanderHomePage::navigateToInversionesPlazo);
        validateNavigationFlow(softAssertions, "Personas - Notas Estructuradas", SantanderHomePage::navigateToNotasEstructuradas);

        validateNavigationFlow(softAssertions, "Personas - Seguro Auto", SantanderHomePage::navigateToSeguroAuto);
        validateNavigationFlow(softAssertions, "Personas - Seguro Vida", SantanderHomePage::navigateToSeguroVida);
        validateNavigationFlow(softAssertions, "Personas - Seguro Hogar", SantanderHomePage::navigateToSeguroHogar);
        validateNavigationFlow(softAssertions, "Personas - Seguro Ahorro", SantanderHomePage::navigateToSeguroAhorro);
        validateNavigationFlow(softAssertions, "Personas - Seguro Gastos Médicos", SantanderHomePage::navigateToSeguroGastosMedicos);
        validateNavigationFlow(softAssertions, "Personas - Seguro Pertenencias", SantanderHomePage::navigateToSeguroPertenencias);

        softAssertions.assertAll();
    }

    @Test(priority = 5)
    public void shouldNavigateThroughPersonasHelpAndBenefits() {
        SoftAssert softAssertions = new SoftAssert();

        validateNavigationFlow(softAssertions, "Personas - SuperLínea", SantanderHomePage::navigateToSuperlinea);
        validateNavigationFlow(softAssertions, "Personas - Sucursales", SantanderHomePage::navigateToSucursales);
        validateNavigationFlow(softAssertions, "Personas - Cajeros", SantanderHomePage::navigateToCajeros);
        validateNavigationFlow(softAssertions, "Personas - Canales Alternos", SantanderHomePage::navigateToCanalesAlternos);
        validateNavigationFlow(softAssertions, "Personas - Centro de Ayuda", SantanderHomePage::navigateToCentroAyuda);
        validateNavigationFlow(softAssertions, "Personas - Centro de Seguridad", SantanderHomePage::navigateToCentroSeguridad);
        validateNavigationFlow(softAssertions, "Personas - Tutoriales", SantanderHomePage::navigateToTutoriales);
        validateNavigationFlow(softAssertions, "Personas - Términos y Condiciones", SantanderHomePage::navigateToTerminosCondiciones);
        validateNavigationFlow(softAssertions, "Personas - Regulación", SantanderHomePage::navigateToRegulacion);

        validateNavigationFlow(softAssertions, "Personas - Select", SantanderHomePage::navigateToSelect);
        validateNavigationFlow(softAssertions, "Personas - Promociones", SantanderHomePage::navigateToPromociones);
        validateNavigationFlow(softAssertions, "Personas - Unique Rewards", SantanderHomePage::navigateToUniqueRewards);
        validateNavigationFlow(softAssertions, "Personas - Colectivos", SantanderHomePage::navigateToColectivos);
        validateNavigationFlow(softAssertions, "Personas - Mundo Hogar", SantanderHomePage::navigateToMundoHogar);
        validateNavigationFlow(softAssertions, "Personas - Cashback", SantanderHomePage::navigateToCashback);

        softAssertions.assertAll();
    }

    @Test(priority = 6)
    public void shouldNavigateThroughEmpresasOptions() {
        SoftAssert softAssertions = new SoftAssert();

        validateNavigationFlow(softAssertions, "Empresas - Empresas y Gobierno", SantanderHomePage::navigateToEmpresasYGobierno);
        validateNavigationFlow(softAssertions, "Empresas - Multinacionales", SantanderHomePage::navigateToMultinacionales);

        softAssertions.assertAll();
    }

    @Test(priority = 7)
    public void shouldNavigateThroughPymesOptions() {
        SoftAssert softAssertions = new SoftAssert();

        validateNavigationFlow(softAssertions, "PyMes - Santander Pyme", SantanderHomePage::navigateToSantanderPyme);
        validateNavigationFlow(softAssertions, "PyMes - Divisas y Coberturas", SantanderHomePage::navigateToDivisasCoberturas);
        validateNavigationFlow(softAssertions, "PyMes - Cuentas", SantanderHomePage::navigateToPymeCuentas);
        validateNavigationFlow(softAssertions, "PyMes - Negocio Internacional", SantanderHomePage::navigateToNegocioInternacional);
        validateNavigationFlow(softAssertions, "PyMes - Paquetes PyMes", SantanderHomePage::navigateToPaquetesPymes);
        validateNavigationFlow(softAssertions, "PyMes - Créditos", SantanderHomePage::navigateToPymeCreditos);
        validateNavigationFlow(softAssertions, "PyMes - Seguros", SantanderHomePage::navigateToPymeSeguros);
        validateNavigationFlow(softAssertions, "PyMes - Alianzas", SantanderHomePage::navigateToAlianzas);
        validateNavigationFlow(softAssertions, "PyMes - Negocio Transaccional", SantanderHomePage::navigateToNegocioTransaccional);
        validateNavigationFlow(softAssertions, "PyMes - Ecosistema Financiero", SantanderHomePage::navigateToEcosistemaFinanciero);
        validateNavigationFlow(softAssertions, "PyMes - Inversiones", SantanderHomePage::navigateToPymeInversiones);

        softAssertions.assertAll();
    }

    @Test(priority = 8)
    public void shouldNavigateToBancaPrivadaOption() {
        SoftAssert softAssertions = new SoftAssert();

        validateNavigationFlow(softAssertions, "Banca Privada", SantanderHomePage::navigateToBancaPrivadaHome);

        softAssertions.assertAll();
    }

    @Test(priority = 9)
    public void shouldNavigateThroughAcercaDelBancoOptions() {
        SoftAssert softAssertions = new SoftAssert();

        validateNavigationFlow(softAssertions, "Acerca - Fundación Santander", SantanderHomePage::navigateToFundacionSantander);
        validateNavigationFlow(softAssertions, "Acerca - Blog", SantanderHomePage::navigateToBlog);
        validateNavigationFlow(softAssertions, "Acerca - Sostenibilidad", SantanderHomePage::navigateToSostenibilidad);
        validateNavigationFlow(softAssertions, "Acerca - Educación Financiera", SantanderHomePage::navigateToEducacionFinanciera);
        validateNavigationFlow(softAssertions, "Acerca - Inversionistas", SantanderHomePage::navigateToInversionistas);
        validateNavigationFlow(softAssertions, "Acerca - Sala de Comunicación", SantanderHomePage::navigateToSalaComunicacion);
        validateNavigationFlow(softAssertions, "Acerca - Bolsa de Trabajo", SantanderHomePage::navigateToBolsaTrabajo);

        softAssertions.assertAll();
    }
}
