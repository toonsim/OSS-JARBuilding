import org.parosproxy.paros.network.HttpMessage;
import org.zaproxy.zap.extension.policyrulescanner.PolicyRule;

public class HTTPSOnlyRule extends PolicyRule {

    private static final String RULE_NAME = "HTTPSOnlyRule";

    public HTTPSOnlyRule(String policyName) {
        super(policyName);
    }

    @Override
    public boolean isFlaggedBy(HttpMessage msg) {
        return msg.getRequestHeader().isSecure();
    }

    @Override
    public String getName() {
        return RULE_NAME;
    }
}
