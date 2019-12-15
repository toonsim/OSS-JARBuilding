import org.apache.commons.httpclient.URI;
import org.junit.Before;
import org.junit.Test;
import org.parosproxy.paros.network.HttpMessage;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** Unit test for {@link DomainRule}. */
public class DomainRuleTest {
    private static final String POLICY_NAME = "ExamplePolicy";
    private DomainRule domainRule;

    @Before
    public void setUp() throws Exception {
        domainRule = new DomainRule(POLICY_NAME);
    }
    @Test
    public void isFlaggedBy_RequestToADomainNotInTheList_ShouldReturnFalse(){
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));
        } catch (Exception e) {
            fail();
        }
        assertFalse(domainRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_RequestToADomainInTheList_ShouldReturnTrue(){
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://twitter.com",true));
        } catch (Exception e) {
            fail();
        }
        assertTrue(domainRule.isFlaggedBy(message));
    }
}
