package in.co.gorest.model.cucumber;

import cucumber.api.CucumberOptions;
import in.co.gorest.model.testbase.TestBase;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/resources/feature")
public class CucumberRunner extends TestBase{


}
