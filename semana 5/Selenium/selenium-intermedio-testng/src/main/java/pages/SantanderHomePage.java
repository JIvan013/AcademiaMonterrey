package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SantanderHomePage extends BasePage {

    private static final By MENU_TRIGGER_PERSONAS = By.id("firstLevel-mainItem-0-menu-button");
    private static final By MENU_TRIGGER_EMPRESAS = By.id("firstLevel-mainItem-1-menu-button");
    private static final By MENU_TRIGGER_PYMES = By.id("firstLevel-mainItem-2-menu-button");
    private static final By MENU_LINK_BANCA_PRIVADA = By.cssSelector("a[href*='/bp/home/']");
    private static final By MENU_TRIGGER_ACERCA = By.id("firstLevel-mainItem-4-menu-button");

    private static final By MENU_LINK_TARJETAS_CREDITO = By.cssSelector("a[href*='tarjetas-de-credito']");
    private static final By MENU_LINK_CREDITO_PERSONAL = By.cssSelector("a[href*='creditos-personales']");
    private static final By MENU_LINK_CREDITO_HIPOTECARIO = By.cssSelector("a[href$='creditos-hipotecarios/']");
    private static final By MENU_LINK_SIMULADOR_HIPOTECA = By.cssSelector("a[href*='simulador-hipotecario']");
    private static final By MENU_LINK_CREDITO_AUTO = By.cssSelector("a[href*='credito-automotriz']");
    private static final By MENU_LINK_BURO_CREDITO = By.cssSelector("a[href*='buro-de-credito']");

    private static final By MENU_LINK_SANTANDER_DIGITAL = By.cssSelector("a[href$='santander-digital/']");
    private static final By MENU_LINK_APP_SANTANDER = By.cssSelector("a[href*='app-santander']");
    private static final By MENU_LINK_SANTANDER_WEB = By.cssSelector("a[href*='santander-web']");
    private static final By MENU_LINK_LIMITE_TRANSACCION = By.cssSelector("a[href*='limite-por-transaccion']");

    private static final By MENU_LINK_CUENTAS = By.cssSelector("a[href$='personas/cuentas/']");
    private static final By MENU_LINK_CUENTA_BASICA = By.cssSelector("a[href*='cuentas/basica/']");
    private static final By MENU_LINK_CUENTA_NOMINA = By.cssSelector("a[href*='basica-nomina']");
    private static final By MENU_LINK_CUENTA_CHEQUES = By.cssSelector("a[href*='cheque-saldo-promedio']");
    private static final By MENU_LINK_PORTABILIDAD_NOMINA = By.cssSelector("a[href*='portabilidad-de-nomina']");

    private static final By MENU_LINK_FONDOS_INVERSION = By.cssSelector("a[href*='#fondos-de-inversion']");
    private static final By MENU_LINK_INVERSIONES_PLAZO = By.cssSelector("a[href*='#inversiones-a-plazo']");
    private static final By MENU_LINK_NOTAS_ESTRUCTURADAS = By.cssSelector("a[href*='#notas-estructuradas']");

    private static final By MENU_LINK_SEGURO_AUTO = By.cssSelector("a[href*='seguros.html#auto']");
    private static final By MENU_LINK_SEGURO_VIDA = By.cssSelector("a[href*='seguros.html#vida']");
    private static final By MENU_LINK_SEGURO_HOGAR = By.cssSelector("a[href*='seguros.html#hogar']");
    private static final By MENU_LINK_SEGURO_AHORRO = By.cssSelector("a[href*='seguros.html#ahorro']");
    private static final By MENU_LINK_SEGURO_GASTOS_MEDICOS = By.cssSelector("a[href*='seguros.html#gastos-medicos']");
    private static final By MENU_LINK_SEGURO_PERTENENCIAS = By.cssSelector("a[href*='seguros.html#pertenencias']");

    private static final By MENU_LINK_SUPERLINEA = By.cssSelector("a[href*='superlinea.html']");
    private static final By MENU_LINK_SUCURSALES = By.cssSelector("a[href*='sucursales.html']");
    private static final By MENU_LINK_CAJEROS = By.cssSelector("a[href*='cajeros-automaticos.html']");
    private static final By MENU_LINK_CANALES_ALTERNOS = By.cssSelector("a[href*='operaciones-canales-alternos']");
    private static final By MENU_LINK_CENTRO_AYUDA = By.cssSelector("a[href*='centro-de-ayuda.html']");
    private static final By MENU_LINK_CENTRO_SEGURIDAD = By.cssSelector("a[href*='centro-de-seguridad']");
    private static final By MENU_LINK_TUTORIALES = By.cssSelector("a[href*='tutoriales.html']");
    private static final By MENU_LINK_TERMINOS_CONDICIONES = By.cssSelector("a[href*='terminos-y-condiciones.html']");
    private static final By MENU_LINK_REGULACION = By.cssSelector("a[href*='tramite-por-defuncion.html']");

    private static final By MENU_LINK_SELECT = By.cssSelector("a[href*='select.html']");
    private static final By MENU_LINK_PROMOCIONES = By.cssSelector("a[href$='promociones/']");
    private static final By MENU_LINK_UNIQUE_REWARDS = By.cssSelector("a[href*='uniquerewards']");
    private static final By MENU_LINK_COLECTIVOS = By.cssSelector("a[href*='colectivos.html']");
    private static final By MENU_LINK_MUNDO_HOGAR = By.cssSelector("a[href*='mundohogar']");
    private static final By MENU_LINK_CASHBACK = By.cssSelector("a[href*='cashback.html']");

    private static final By MENU_LINK_EMPRESAS_GOBIERNO = By.cssSelector("a[href*='bei/home.html']");
    private static final By MENU_LINK_MULTINACIONALES = By.cssSelector("a[href*='multinacionales.html']");

    private static final By MENU_LINK_SANTANDER_PYME = By.cssSelector("a[href$='/pyme/']");
    private static final By MENU_LINK_DIVISAS_COBERTURAS = By.cssSelector("a[href*='coberturas-y-cambios.html']");
    private static final By MENU_LINK_PYME_CUENTAS = By.cssSelector("a[href*='pyme/cuentas.html']");
    private static final By MENU_LINK_NEGOCIO_INTERNACIONAL = By.cssSelector("a[href*='negocio-internacional.html']");
    private static final By MENU_LINK_PAQUETES_PYMES = By.cssSelector("a[href*='paquetes-pymes.html']");
    private static final By MENU_LINK_PYME_CREDITOS = By.cssSelector("a[href*='pyme/creditos.html']");
    private static final By MENU_LINK_PYME_SEGUROS = By.cssSelector("a[href*='pyme/seguros.html']");
    private static final By MENU_LINK_ALIANZAS = By.cssSelector("a[href*='alianzas.html']");
    private static final By MENU_LINK_NEGOCIO_TRANSACCIONAL = By.cssSelector("a[href*='negocio-transaccional.html']");
    private static final By MENU_LINK_ECOSISTEMA_FINANCIERO = By.cssSelector("a[href*='ecosistemas-pyme.html']");
    private static final By MENU_LINK_PYME_INVERSIONES = By.cssSelector("a[href*='pyme/inversiones.html']");

    private static final By MENU_LINK_FUNDACION_SANTANDER = By.cssSelector("a[href*='fundacion-santander.html']");
    private static final By MENU_LINK_BLOG = By.cssSelector("a[href*='blog.html']");
    private static final By MENU_LINK_SOSTENIBILIDAD = By.cssSelector("a[href*='responsabilidad-social.html']");
    private static final By MENU_LINK_EDUCACION_FINANCIERA = By.cssSelector("a[href*='educacion-financiera']");
    private static final By MENU_LINK_INVERSIONISTAS = By.cssSelector("a[href*='ir/home/']");
    private static final By MENU_LINK_SALA_COMUNICACION = By.cssSelector("a[href*='sala_prensa']");
    private static final By MENU_LINK_BOLSA_TRABAJO = By.cssSelector("a[href*='bolsa-de-trabajo.html']");

    public SantanderHomePage(WebDriver browser) {
        super(browser);
    }

    private void openPersonasDropdown() {
        
    	click(MENU_TRIGGER_PERSONAS);
    }

    private void openEmpresasDropdown() {
        click(MENU_TRIGGER_EMPRESAS);
    }

    private void openPymesDropdown() {
        click(MENU_TRIGGER_PYMES);
    }

    private void openAcercaDropdown() {
        click(MENU_TRIGGER_ACERCA);
    }

    public void navigateToTarjetasCredito() {
        openPersonasDropdown();
        click(MENU_LINK_TARJETAS_CREDITO);
    }

    public void navigateToCreditoPersonal() {
        openPersonasDropdown();
        click(MENU_LINK_CREDITO_PERSONAL);
    }

    public void navigateToCreditoHipotecario() {
        openPersonasDropdown();
        click(MENU_LINK_CREDITO_HIPOTECARIO);
    }

    public void navigateToSimuladorHipoteca() {
        openPersonasDropdown();
        click(MENU_LINK_SIMULADOR_HIPOTECA);
    }

    public void navigateToCreditoAuto() {
        openPersonasDropdown();
        click(MENU_LINK_CREDITO_AUTO);
    }

    public void navigateToBuroCredito() {
        openPersonasDropdown();
        click(MENU_LINK_BURO_CREDITO);
    }

    public void navigateToSantanderDigital() {
        openPersonasDropdown();
        click(MENU_LINK_SANTANDER_DIGITAL);
    }

    public void navigateToAppSantander() {
        openPersonasDropdown();
        click(MENU_LINK_APP_SANTANDER);
    }

    public void navigateToSantanderWeb() {
        openPersonasDropdown();
        click(MENU_LINK_SANTANDER_WEB);
    }

    public void navigateToLimiteTransaccion() {
        openPersonasDropdown();
        click(MENU_LINK_LIMITE_TRANSACCION);
    }

    public void navigateToCuentas() {
        openPersonasDropdown();
        click(MENU_LINK_CUENTAS);
    }

    public void navigateToCuentaBasica() {
        openPersonasDropdown();
        click(MENU_LINK_CUENTA_BASICA);
    }

    public void navigateToCuentaNomina() {
        openPersonasDropdown();
        click(MENU_LINK_CUENTA_NOMINA);
    }

    public void navigateToCuentaCheques() {
        openPersonasDropdown();
        click(MENU_LINK_CUENTA_CHEQUES);
    }

    public void navigateToPortabilidadNomina() {
        openPersonasDropdown();
        click(MENU_LINK_PORTABILIDAD_NOMINA);
    }

    public void navigateToFondosInversion() {
        openPersonasDropdown();
        click(MENU_LINK_FONDOS_INVERSION);
    }

    public void navigateToInversionesPlazo() {
        openPersonasDropdown();
        click(MENU_LINK_INVERSIONES_PLAZO);
    }

    public void navigateToNotasEstructuradas() {
        openPersonasDropdown();
        click(MENU_LINK_NOTAS_ESTRUCTURADAS);
    }

    public void navigateToSeguroAuto() {
        openPersonasDropdown();
        click(MENU_LINK_SEGURO_AUTO);
    }

    public void navigateToSeguroVida() {
        openPersonasDropdown();
        click(MENU_LINK_SEGURO_VIDA);
    }

    public void navigateToSeguroHogar() {
        openPersonasDropdown();
        click(MENU_LINK_SEGURO_HOGAR);
    }

    public void navigateToSeguroAhorro() {
        openPersonasDropdown();
        click(MENU_LINK_SEGURO_AHORRO);
    }

    public void navigateToSeguroGastosMedicos() {
        openPersonasDropdown();
        click(MENU_LINK_SEGURO_GASTOS_MEDICOS);
    }

    public void navigateToSeguroPertenencias() {
        openPersonasDropdown();
        click(MENU_LINK_SEGURO_PERTENENCIAS);
    }

    public void navigateToSuperlinea() {
        openPersonasDropdown();
        click(MENU_LINK_SUPERLINEA);
    }

    public void navigateToSucursales() {
        openPersonasDropdown();
        click(MENU_LINK_SUCURSALES);
    }

    public void navigateToCajeros() {
        openPersonasDropdown();
        click(MENU_LINK_CAJEROS);
    }

    public void navigateToCanalesAlternos() {
        openPersonasDropdown();
        click(MENU_LINK_CANALES_ALTERNOS);
    }

    public void navigateToCentroAyuda() {
        openPersonasDropdown();
        click(MENU_LINK_CENTRO_AYUDA);
    }

    public void navigateToCentroSeguridad() {
        openPersonasDropdown();
        click(MENU_LINK_CENTRO_SEGURIDAD);
    }

    public void navigateToTutoriales() {
        openPersonasDropdown();
        click(MENU_LINK_TUTORIALES);
    }

    public void navigateToTerminosCondiciones() {
        openPersonasDropdown();
        click(MENU_LINK_TERMINOS_CONDICIONES);
    }

    public void navigateToRegulacion() {
        openPersonasDropdown();
        click(MENU_LINK_REGULACION);
    }

    public void navigateToSelect() {
        openPersonasDropdown();
        click(MENU_LINK_SELECT);
    }

    public void navigateToPromociones() {
        openPersonasDropdown();
        click(MENU_LINK_PROMOCIONES);
    }

    public void navigateToUniqueRewards() {
        openPersonasDropdown();
        click(MENU_LINK_UNIQUE_REWARDS);
    }

    public void navigateToColectivos() {
        openPersonasDropdown();
        click(MENU_LINK_COLECTIVOS);
    }

    public void navigateToMundoHogar() {
        openPersonasDropdown();
        click(MENU_LINK_MUNDO_HOGAR);
    }

    public void navigateToCashback() {
        openPersonasDropdown();
        click(MENU_LINK_CASHBACK);
    }

    public void navigateToEmpresasYGobierno() {
        openEmpresasDropdown();
        click(MENU_LINK_EMPRESAS_GOBIERNO);
    }

    public void navigateToMultinacionales() {
        openEmpresasDropdown();
        click(MENU_LINK_MULTINACIONALES);
    }

    public void navigateToSantanderPyme() {
        openPymesDropdown();
        click(MENU_LINK_SANTANDER_PYME);
    }

    public void navigateToDivisasCoberturas() {
        openPymesDropdown();
        click(MENU_LINK_DIVISAS_COBERTURAS);
    }

    public void navigateToPymeCuentas() {
        openPymesDropdown();
        click(MENU_LINK_PYME_CUENTAS);
    }

    public void navigateToNegocioInternacional() {
        openPymesDropdown();
        click(MENU_LINK_NEGOCIO_INTERNACIONAL);
    }

    public void navigateToPaquetesPymes() {
        openPymesDropdown();
        click(MENU_LINK_PAQUETES_PYMES);
    }

    public void navigateToPymeCreditos() {
        openPymesDropdown();
        click(MENU_LINK_PYME_CREDITOS);
    }

    public void navigateToPymeSeguros() {
        openPymesDropdown();
        click(MENU_LINK_PYME_SEGUROS);
    }

    public void navigateToAlianzas() {
        openPymesDropdown();
        click(MENU_LINK_ALIANZAS);
    }

    public void navigateToNegocioTransaccional() {
        openPymesDropdown();
        click(MENU_LINK_NEGOCIO_TRANSACCIONAL);
    }

    public void navigateToEcosistemaFinanciero() {
        openPymesDropdown();
        click(MENU_LINK_ECOSISTEMA_FINANCIERO);
    }

    public void navigateToPymeInversiones() {
        openPymesDropdown();
        click(MENU_LINK_PYME_INVERSIONES);
    }

    public void navigateToBancaPrivadaHome() {
        click(MENU_LINK_BANCA_PRIVADA);
    }

    public void navigateToFundacionSantander() {
        openAcercaDropdown();
        click(MENU_LINK_FUNDACION_SANTANDER);
    }

    public void navigateToBlog() {
        openAcercaDropdown();
        click(MENU_LINK_BLOG);
    }

    public void navigateToSostenibilidad() {
        openAcercaDropdown();
    }

    public void navigateToEducacionFinanciera() {
        openAcercaDropdown();
        click(MENU_LINK_EDUCACION_FINANCIERA);
    }

    public void navigateToInversionistas() {
        openAcercaDropdown();
        click(MENU_LINK_INVERSIONISTAS);
    }

    public void navigateToSalaComunicacion() {
        openAcercaDropdown();
        click(MENU_LINK_SALA_COMUNICACION);
    }

    public void navigateToBolsaTrabajo() {
        openAcercaDropdown();
        click(MENU_LINK_BOLSA_TRABAJO);
    }
}
