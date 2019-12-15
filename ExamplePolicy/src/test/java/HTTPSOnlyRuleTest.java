import org.apache.commons.httpclient.URI;
import org.junit.Before;
import org.junit.Test;
import org.parosproxy.paros.network.HttpMessage;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** Unit test for {@link HTTPSOnlyRule}. */
public class HTTPSOnlyRuleTest {
    private static final String POLICY_NAME = "ExamplePolicy";
    private HTTPSOnlyRule httpsOnlyRule;

    @Before
    public void setUp() throws Exception {
        httpsOnlyRule = new HTTPSOnlyRule(POLICY_NAME);
    }

    @Test
    public void isFlaggedBy_HTTPSRequest_ShouldReturnFalse() {
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("https://www.google.com", true));
        } catch (Exception e) {
            fail();
        }
        assertFalse(httpsOnlyRule.isFlaggedBy(message));
    }

    @Test
    public void isFlaggedBy_HTTPRequest_ShouldReturnTrue() {
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://www.google.com", true));
        } catch (Exception e) {
            fail();
        }
        assertTrue(httpsOnlyRule.isFlaggedBy(message));
    }
}
