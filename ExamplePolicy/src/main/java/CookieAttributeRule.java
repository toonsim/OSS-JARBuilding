import org.parosproxy.paros.network.HtmlParameter;
import org.parosproxy.paros.network.HttpMessage;
import org.zaproxy.zap.extension.policyrulescanner.PolicyRule;

import java.net.HttpCookie;

public class CookieAttributeRule extends PolicyRule {
    private static final String RULE_NAME = "CookieAttributeRule";

    public CookieAttributeRule(String policyName) {
        super(policyName);
    }

    @Override
    public boolean isFlaggedBy(HttpMessage msg) {
        for(HttpCookie cookie : msg.getResponseHeader().getHttpCookies(null)){
            if (!cookie.getSecure() || !cookie.isHttpOnly()){
                return true;
            }
            if (!(msg.getRequestHeader().getHostName().contains(cookie.getDomain()))){  //Same Site
                return true;
            }
        }

        return false;
    }

    @Override
    public String getName() {
        return RULE_NAME;
    }
}
