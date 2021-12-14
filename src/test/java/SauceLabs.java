import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SauceLabs {

    WebDriver driver;


    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
                .concat("\\src\\test\\resources\\drivers\\chromedriver.exe"));
        /**
         *Precondicion: Se debe contar con acceso a la pagina web
         */
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        /**
         * Postcondiciones, acciones necesarias para dejar la aplicacion en el estado original
         * o de acciones de limpieza post prueba
         */
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void deberiainiciarsesion(String usuario, String password){

        WebElement Buttonregistro = driver.findElement(By.xpath("//a[@class='login']"));
        Buttonregistro.click();

        WebElement textview_correo = driver.findElement(By.id("email"));
        textview_correo.sendKeys("araujomanuelsaxo@hotmail.com ");

        WebElement textview_contrasena = driver.findElement(By.id("passwd"));
        textview_contrasena.sendKeys("95082011200");

        WebElement button_iniciar = driver.findElement(By.id("SubmitLogin"));
        button_iniciar.click();
    }

    @Test
        public void deberiaregistrarelusuario() throws InterruptedException{

        WebElement ButtonInicioSesion = driver.findElement(By.xpath("//a[@class='login']"));
        ButtonInicioSesion.click();


        WebElement IngresarEmail = driver.findElement(By.id("email_create"));
        IngresarEmail.sendKeys("canuelsaxo@gmail.com");

        WebElement ButtonCrearCuenta = driver.findElement(By.id("SubmitCreate"));
        ButtonCrearCuenta.click();

            Thread.sleep(5000);

        WebElement TipoGenero = driver.findElement(By.id("id_gender1"));
        TipoGenero.click();

        WebElement Ingresar_Firstname = driver.findElement(By.id("customer_firstname"));
        Ingresar_Firstname.sendKeys("Manuel");

        WebElement Ingresar_Last_name = driver.findElement(By.id("customer_lastname"));
        Ingresar_Last_name.sendKeys("Araujo");

        WebElement Ingresar_password = driver.findElement(By.id("passwd"));
        Ingresar_password.sendKeys("M@nuel123");

        WebElement Ingresar_day = driver.findElement(By.id("days"));
        Ingresar_day.sendKeys("11");

        WebElement Ingresar_months = driver.findElement(By.id("months"));
        Ingresar_months.sendKeys("April");

        WebElement Ingresar_year = driver.findElement(By.id("years"));
        Ingresar_year.sendKeys("1995");

        WebElement Ingresar_company = driver.findElement(By.id("company"));
        Ingresar_company.sendKeys("INDRA");

        WebElement Ingresar_address = driver.findElement(By.id("address1"));
        Ingresar_address.sendKeys("KR 6 #34 45");

        WebElement Ingresar_address2 = driver.findElement(By.id("address2"));
        Ingresar_address2.sendKeys("CALL 45 # 32 45");

        WebElement Ingresar_city = driver.findElement(By.id("city"));
        Ingresar_city.sendKeys("Manizales");

        Select Ingresar_state = new Select(driver.findElement(By.id("id_state")));
        Ingresar_state.selectByVisibleText("Colorado");

        WebElement Ingresar_codepostal = driver.findElement(By.id("postcode"));
        Ingresar_codepostal.sendKeys("24002");

        Select Ingresar_Pais = new Select(driver.findElement(By.id("id_country")));
        Ingresar_Pais.selectByVisibleText("United States");

        WebElement Ingresar_Additionalinformation = driver.findElement(By.id("other"));
        Ingresar_Additionalinformation.sendKeys("No hay informacion");

        WebElement Ingresar_telefonofijo = driver.findElement(By.id("phone"));
        Ingresar_telefonofijo.sendKeys("8544345");

        WebElement Ingresar_numerocelular = driver.findElement(By.id("phone_mobile"));
        Ingresar_numerocelular.sendKeys("3104994417");

        WebElement Ingresar_adicionardireccion = driver.findElement(By.id("alias"));
        Ingresar_adicionardireccion.sendKeys("CALL 39 sur # 56 43");

        WebElement Buttonregistrar = driver.findElement(By.id("submitAccount"));
        Buttonregistrar.click();


        WebElement NombreUser = driver.findElement(By.xpath("//span[contains(.,'Manuel Araujo')]"));
            Assertions.assertEquals("Manuel Araujo", NombreUser.getText());
        }

    @Test
    public void agregarAlCarrito (){

        //Iniciar sesión
        deberiainiciarsesion("araujomanuelsaxo@hotmail.com","95082011200");

        //Entrar a vestidos
        WebElement dresses = driver.findElement(By.xpath("(//a[contains(.,'Dresses')])[5]"));
        dresses.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        agregarAlCarritoYContinuarComprando("Printed Dress");
        agregarAlCarritoYContinuarComprando("Printed Chiffon Dress");
        
        //Acciones
        /*Actions accion = new Actions(driver);

        WebElement vestidoSeleccionado = driver.findElement(By.xpath("//li[contains(@class,'ajax_block_product')][1]"));
        accion.moveToElement(vestidoSeleccionado).perform();

        WebElement addToCart = driver.findElement(By.xpath("(//span[contains(.,'Add to cart')])[1]"));
        accion.moveToElement(addToCart).perform();
        addToCart.click();

        //Esperar a carga
        WebElement productSuccessfullyAdded = new WebDriverWait(driver, 40)
                .until(ExpectedConditions.
                        visibilityOfElementLocated(By.xpath("//h2[contains(.,'Product successfully added to your shopping cart')]")));

        //Seguir comprando
        WebElement continuarComprando = driver.findElement(By.xpath("(//span[contains(.,'Continue shopping')])[2]"));
        continuarComprando.click();
        */
        //Validacion

        WebElement numeroDeItemsComprados = driver.findElement(By.xpath("//span[@class='ajax_cart_quantity unvisible']"));

        //MatcherAssert.assertThat("El item deberia ser 1",numeroDeItemsComprados.getText(),
        //        Matchers.equalTo("1"));

        MatcherAssert.assertThat("La cantidad de items deberian ser 2",numeroDeItemsComprados.getText(),
                Matchers.equalTo("2"));
    }

    public void agregarArticuloAlCarrito(String nombreDelArticulo){
        WebElement articulo = driver.findElement(By.xpath("//div[@class='product-container']//h5[contains(.,'"+nombreDelArticulo+"')]"));
        WebElement boton = driver.findElement(By.xpath("(//span[contains(.,'Add to cart')])[1]"));
        Actions mouseActions = new Actions(driver);
        mouseActions.moveToElement(articulo).build().perform();
        boton.click();
    }

    public void  agregarAlCarritoYContinuarComprando (String nombreDelArticulo){

        agregarArticuloAlCarrito(nombreDelArticulo);

        //Esperar a carga
        WebElement productSuccessfullyAdded = new WebDriverWait(driver, 40)
                .until(ExpectedConditions.
                        visibilityOfElementLocated(By.xpath("//h2[contains(.,'Product successfully added to your shopping cart')]")));

        //Seguir comprando
        WebElement continuarComprando = driver.findElement(By.xpath("(//span[contains(.,'Continue shopping')])[2]"));
        continuarComprando.click();
    }


}
