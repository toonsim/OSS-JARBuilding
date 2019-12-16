
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.URI;
import org.junit.Before;
import org.junit.Test;
import org.parosproxy.paros.network.HttpMessage;
import org.parosproxy.paros.network.HttpResponseHeader;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/** Unit test for {@link CookieAttributeRule}. */
public class CookieAttributeRuleTest {
    private static final String POLICY_NAME = "ExamplePolicy";
    private CookieAttributeRule CookieRule;



    @Before
    public void setUp() throws Exception {
        CookieRule = new CookieAttributeRule(POLICY_NAME);

    }
    @Test
    public void isFlaggedBy_MessageWithoutCookied_ShouldReturnFalse(){
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));
        } catch (Exception e) {
            fail();
        }
        assertFalse(CookieRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_MessageWithSecureAndHTTPOnlyCookie_FromTheSameDomain_ShouldReturnTrue(){
        HttpCookie cook = new HttpCookie("test","test");
        cook.setDomain("www.google.com");
        cook.setSecure(true);
        cook.setHttpOnly(true);
        List<HttpCookie> cookies = new ArrayList<HttpCookie>();
        cookies.add(cook);
        HttpResponseHeader respHeadr= mock(HttpResponseHeader.class);
        when(respHeadr.getHttpCookies()).thenReturn(cookies);
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));
            message.setResponseHeader(respHeadr);
        } catch (Exception e) {
            fail();
        }
        assertFalse(CookieRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_MessageWithSecureAndHTTPOnlyCookie_FromADifferentDomain_ShouldReturnTrue(){
        HttpCookie cook = new HttpCookie("test","test");
        cook.setDomain("www.kuleuven.be");
        cook.setSecure(true);
        cook.setHttpOnly(true);
        List<HttpCookie> cookies = new ArrayList<HttpCookie>();
        cookies.add(cook);
        HttpResponseHeader respHeadr= mock(HttpResponseHeader.class);
        when(respHeadr.getHttpCookies()).thenReturn(cookies);
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));
            message.setResponseHeader(respHeadr);
        } catch (Exception e) {
            fail();
        }
        assertTrue(CookieRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_MessageWithNoSecureAndHTTPOnlyCookie_FromTheSameDomain_ShouldReturnTrue(){
        HttpCookie cook = new HttpCookie("test","test");
        cook.setDomain("www.google.com");
        cook.setHttpOnly(true);
        List<HttpCookie> cookies = new ArrayList<HttpCookie>();
        cookies.add(cook);
        HttpResponseHeader respHeadr= mock(HttpResponseHeader.class);
        when(respHeadr.getHttpCookies()).thenReturn(cookies);
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));
            message.setResponseHeader(respHeadr);
        } catch (Exception e) {
            fail();
        }
        assertTrue(CookieRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_MessageWithSecureAndNoHTTPOnlyCookie_FromTheSameDomain_ShouldReturnTrue(){
        HttpCookie cook = new HttpCookie("test","test");
        cook.setDomain("www.google.com");
        cook.setSecure(true);
        List<HttpCookie> cookies = new ArrayList<HttpCookie>();
        cookies.add(cook);
        HttpResponseHeader respHeadr= mock(HttpResponseHeader.class);
        when(respHeadr.getHttpCookies()).thenReturn(cookies);
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));
            message.setResponseHeader(respHeadr);
        } catch (Exception e) {
            fail();
        }
        assertTrue(CookieRule.isFlaggedBy(message));
    }
}
