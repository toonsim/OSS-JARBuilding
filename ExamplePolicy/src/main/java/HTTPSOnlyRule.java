import org.parosproxy.paros.network.HttpMessage;
import org.zaproxy.zap.extension.policyrulescanner.PolicyRule;

public class HTTPSOnlyRule extends PolicyRule {

    private static final String RULE_NAME = "HTTPSOnlyRule";

    public HTTPSOnlyRule(String policyName) {
        super(policyName);
    }

    @Override
    public boolean isFlaggedBy(HttpMessage msg) {
        String[] doms = msg.getRequestHeader().getURI().toString().split("://");
        System.out.println(doms[0]+ "+++++" + doms[1]+"+++++lengte="+doms.length);
        if (doms[0].contentEquals("https")) return false;
        else return true;
        //return msg.getRequestHeader().isSecure();
    }

    @Override
    public String getName() {
        return RULE_NAME;
    }
}
