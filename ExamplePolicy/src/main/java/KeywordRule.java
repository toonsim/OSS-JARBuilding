import org.parosproxy.paros.network.HttpMessage;
import org.zaproxy.zap.extension.policyrulescanner.PolicyRule;

import java.util.ArrayList;

public class KeywordRule extends PolicyRule {
    private static final String RULE_NAME = "KeywordRule";
    private ArrayList<String> keywords; // TODO: Nuke this monstrosity


    public KeywordRule(String policyName) {
        super(policyName);
        keywords = new ArrayList<String>();
        keywords.add("spitfire");
    }

    @Override
    public boolean isFlaggedBy(HttpMessage msg) {
        for(String keyword: keywords){
            if(msg.getRequestHeader().toString().contains(keyword)
                    || msg.getResponseHeader().toString().contains(keyword)
                    || msg.getRequestBody().toString().contains(keyword)
                    || msg.getResponseBody().toString().contains(keyword)
            ) return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return RULE_NAME;
    }
}
