package Log4JPack;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeSuite;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {    
    public static RequestSpecification httpRequest;
    public static Response response;
    public Logger logger;
    
    @BeforeSuite
    public void setup()
    {
        logger=Logger.getLogger("ReqresRestAPI");
        PropertyConfigurator.configure("Log4j.properties");
        logger.setLevel(Level.DEBUG);
    }
}
