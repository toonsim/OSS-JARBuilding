import org.parosproxy.paros.network.HttpMessage;
import org.zaproxy.zap.extension.policyrulescanner.PolicyRule;

public class DomainRule extends PolicyRule {



    private static final String RULE_NAME = "DomainRule";
    private String[] domains = {"twitter.com"};

    public DomainRule(String policyName) {
        super(policyName);
    }

    @Override
    public boolean isFlaggedBy(HttpMessage msg) {
        for (String domain: domains) {
            String messageDomain = extractDomain(msg);
            if (messageDomain.equals(domain)) {
                return true;
            }
        }
        return false;
    }

    private String extractDomain(HttpMessage message) {
        String[] doms = message.getRequestHeader().getHostName().split("\\.");
        return doms[doms.length - 2] + "." + doms[doms.length - 1];
    }

    @Override
    public String getName() {
        return RULE_NAME;
    }
}
