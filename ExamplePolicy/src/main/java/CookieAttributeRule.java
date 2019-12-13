import org.parosproxy.paros.network.HtmlParameter;
import org.parosproxy.paros.network.HttpMessage;
import org.zaproxy.zap.extension.policyrulescanner.PolicyRule;

public class CookieAttributeRule extends PolicyRule {
    private static final String RULE_NAME = "CookieAttributeRule";

    public CookieAttributeRule(String policyName) {
        super(policyName);
    }
//
    @Override
    public boolean isFlaggedBy(HttpMessage msg) {
        boolean containsHttpOnly = false;
        boolean containsSecure = false;
        boolean containsSameSite = false;
        for(HtmlParameter param : msg.getCookieParams()){
            if (param.getName().equals("HttpOnly")) containsHttpOnly = true;
            if (param.getName().equals("containsSecure")) containsSecure = true;
            if (param.getName().equals("SameSite")) containsSameSite = true;
        }

        return !(containsHttpOnly && containsSecure && containsSameSite);
    }

    @Override
    public String getName() {
        return RULE_NAME;
    }
}
