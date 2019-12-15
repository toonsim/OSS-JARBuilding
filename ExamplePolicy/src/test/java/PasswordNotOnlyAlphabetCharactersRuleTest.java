import org.apache.commons.httpclient.URI;
import org.junit.Before;
import org.junit.Test;
import org.parosproxy.paros.network.HtmlParameter;
import org.parosproxy.paros.network.HttpMessage;

import java.util.TreeSet;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** Unit test for {@link PasswordNotOnlyAlphabetCharactersRule}. */
public class PasswordNotOnlyAlphabetCharactersRuleTest {
    private static final String POLICY_NAME = "ExamplePolicy";
    private PasswordNotOnlyAlphabetCharactersRule charactersRule;



    @Before
    public void setUp() throws Exception {
        charactersRule = new PasswordNotOnlyAlphabetCharactersRule(POLICY_NAME);

    }
    @Test
    public void isFlaggedBy_MessageWithoutPasswordField_ShouldReturnFalse(){
        HttpMessage message = null;
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));
        } catch (Exception e) {
            fail();
        }
        assertFalse(charactersRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_MessageWithPasswordField_PasswordFieldNotInList_ShouldReturnFalse(){
        HttpMessage message = null;
        HtmlParameter PasswordField = new HtmlParameter(HtmlParameter.Type.form,"passw0rd", "abd");
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));
            TreeSet<HtmlParameter> params = message.getFormParams();
            params.add(PasswordField);
            message.setFormParams(params);
        } catch (Exception e) {
            fail();
        }
        assertFalse(charactersRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_MessageWithPasswordField_PasswordFieldInList_DoesNotContainsOnlyCharacters_ShouldReturnFalse(){
        HttpMessage message = null;
        HtmlParameter PasswordField = new HtmlParameter(HtmlParameter.Type.form,"password", "123jul4");
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));
            System.out.println(message.getFormParams().toString());
        } catch (Exception e) {
            fail();
        }
        System.out.println("thisssssssssssssssssssss");
        TreeSet<HtmlParameter> params = message.getFormParams();
        params.add(PasswordField);
        message.setFormParams(params);
        System.out.println(message.getFormParams().toString());
        assertFalse(charactersRule.isFlaggedBy(message));
    }
    @Test
    public void isFlaggedBy_MessageWithPasswordField_PasswordFieldInList_DoesContainsOnlyCharacters_ShouldReturnTrue(){
        HttpMessage message = null;
        HtmlParameter PasswordField = new HtmlParameter(HtmlParameter.Type.form,"password", "abcd");
        try {
            message = new HttpMessage(new URI("http://www.google.com",true));
            TreeSet<HtmlParameter> params = message.getFormParams();
            params.add(PasswordField);
            message.setFormParams(params);

        } catch (Exception e) {
            fail();
        }
        assertTrue(charactersRule.isFlaggedBy(message));
    }

}
