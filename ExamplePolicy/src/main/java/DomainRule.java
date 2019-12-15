import org.parosproxy.paros.network.HttpMessage;
import org.zaproxy.zap.extension.policyrulescanner.PolicyRule;

public class DomainRule extends PolicyRule {



    private static final String RULE_NAME = "DomainRule";
    private String[] domains = {"twitter.com"};//,"www.twitter.com"}; //TODO: Fix the www.

    public DomainRule(String policyName) {
        super(policyName);
    }

    @Override
    public boolean isFlaggedBy(HttpMessage msg) {
        for (String domain: domains) {
            if (msg.getRequestHeader().getHostName().equals(domain)) {
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
