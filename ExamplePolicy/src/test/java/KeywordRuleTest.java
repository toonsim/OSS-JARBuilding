import org.apache.commons.httpclient.URI;
import org.junit.Before;
import org.junit.Test;
import org.parosproxy.paros.network.HttpMessage;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** Unit test for {@link KeywordRule}. */
public class KeywordRuleTest {
    private static final String POLICY_NAME = "ExamplePolicy";
    private KeywordRule keywordRule;

    @Before
    public void setUp() throws Exception {
        keywordRule = new KeywordRule(POLICY_NAME);
    }
    @Test
    public void isFlaggedBy_MessageWithoutKeyword_ShouldReturnFalse(){
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));
            //System.out.println(message.getRequestHeader().getHostName());
        } catch (Exception e) {
            fail();
        }
        assertFalse(keywordRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_MessageWithKeywordInRequestHeader_ShouldReturnTrue(){
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://www.spitfireaudio.com",true));

        } catch (Exception e) {
            fail();
        }
        assertTrue(keywordRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_MessageWithKeywordInResponseHeader_ShouldReturnTrue(){
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));
            message.getResponseHeader().setReasonPhrase("spitfire");
        } catch (Exception e) {
            fail();
        }
        assertTrue(keywordRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_MessageWithKeywordInRequestBody_ShouldReturnTrue(){
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));
            message.getRequestBody().append("spitfire");
        } catch (Exception e) {
            fail();
        }
        assertTrue(keywordRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_MessageWithKeywordInResponseBody_ShouldReturnTrue(){
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));
            message.getResponseBody().append("spitfire");
        } catch (Exception e) {
            fail();
        }
        assertTrue(keywordRule.isFlaggedBy(message));
    }
}
