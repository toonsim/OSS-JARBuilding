import org.apache.commons.httpclient.URI;
import org.junit.Before;
import org.junit.Test;
import org.parosproxy.paros.network.HtmlParameter;
import org.parosproxy.paros.network.HttpMessage;

import java.util.TreeSet;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/** Unit test for {@link PasswordNotOnlyNumbersRule}. */
public class PasswordNotOnlyNumbersRuleTest {
    private static final String POLICY_NAME = "ExamplePolicy";
    private PasswordNotOnlyNumbersRule NumbersRule;



    @Before
    public void setUp() throws Exception {
        NumbersRule = new PasswordNotOnlyNumbersRule(POLICY_NAME);

    }
    @Test
    public void isFlaggedBy_MessageWithoutPasswordField_ShouldReturnFalse(){
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));
        } catch (Exception e) {
            fail();
        }
        assertFalse(NumbersRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_MessageWithPasswordField_PasswordFieldNotInList_ShouldReturnFalse(){
        HttpMessage message = mock(HttpMessage.class);
        HtmlParameter PasswordField = new HtmlParameter(HtmlParameter.Type.form,"passw0rd", "1234");
        TreeSet<HtmlParameter> params = new TreeSet<HtmlParameter>();
        params.add(PasswordField);
        when(message.getFormParams()).thenReturn(params);
        assertFalse(NumbersRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_MessageWithPasswordField_PasswordFieldInList_DoesNotContainsOnlyNumbers_ShouldReturnFalse(){
        HttpMessage message = mock(HttpMessage.class);
        HtmlParameter PasswordField = new HtmlParameter(HtmlParameter.Type.form,"password", "123jul4");
        TreeSet<HtmlParameter> params = new TreeSet<HtmlParameter>();
        params.add(PasswordField);
        when(message.getFormParams()).thenReturn(params);
        assertFalse(NumbersRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_MessageWithPasswordField_PasswordFieldInList_DoesContainsOnlyNumbers_ShouldReturnTrue(){
        HttpMessage message = mock(HttpMessage.class);
        HtmlParameter PasswordField = new HtmlParameter(HtmlParameter.Type.form,"password", "1234");
        TreeSet<HtmlParameter> params = new TreeSet<HtmlParameter>();
        params.add(PasswordField);
        when(message.getFormParams()).thenReturn(params);
        assertTrue(NumbersRule.isFlaggedBy(message));
    }

}
