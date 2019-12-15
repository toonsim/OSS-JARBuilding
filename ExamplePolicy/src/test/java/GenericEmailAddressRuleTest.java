import org.apache.commons.httpclient.URI;
import org.junit.Before;
import org.junit.Test;
import org.parosproxy.paros.network.HttpMessage;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GenericEmailAddressRuleTest {

    private static final String POLICY_NAME = "ExamplePolicy";
    private GenericEmailAddressRule genericEmailAddressRule;
    private static final String GENERIC_EMAIL="administrator@";

    @Before
    public void setUp() throws Exception {
        genericEmailAddressRule = new GenericEmailAddressRule(POLICY_NAME);
    }
    @Test
    public void isFlaggedBy_MessageWithoutGenericEmail_ShouldReturnFalse(){
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));

        } catch (Exception e) {
            fail();
        }
        assertFalse(genericEmailAddressRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_MessageWithGenericEmailInRequestHeader_ShouldReturnTrue(){
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI(GENERIC_EMAIL,true));
        } catch (Exception e) {
            fail();
        }
        assertTrue(genericEmailAddressRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_MessageWithGenericEmailInResponseHeader_ShouldReturnTrue(){
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));
            message.getResponseHeader().setReasonPhrase(GENERIC_EMAIL);
        } catch (Exception e) {
            fail();
        }
        assertTrue(genericEmailAddressRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_MessageWithGenericEmailInRequestBody_ShouldReturnTrue(){
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));
            message.getRequestBody().append(GENERIC_EMAIL);
        } catch (Exception e) {
            fail();
        }
        assertTrue(genericEmailAddressRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_MessageWithGenericEmailInResponseBody_ShouldReturnTrue(){
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));
            message.getResponseBody().append(GENERIC_EMAIL);
        } catch (Exception e) {
            fail();
        }
        assertTrue(genericEmailAddressRule.isFlaggedBy(message));
    }
}
