import org.parosproxy.paros.network.HtmlParameter;
import org.parosproxy.paros.network.HttpMessage;
import org.zaproxy.zap.extension.policyrulescanner.PolicyRule;

import java.util.TreeSet;


public class PasswordNotOnlyNumbersRule extends PolicyRule {

    private static final String RULE_NAME = "PasswordNotOnlyNumbersRule";
    private static final String[] PASSWORD_FIELD_NAMES = new String[]{"password", "pass", "pwd"};
    public PasswordNotOnlyNumbersRule(String policyName) {
        super(policyName);
    }

    @Override
    public boolean isFlaggedBy(HttpMessage msg) {
        try {
            String password = getFirstFieldValue(msg, PASSWORD_FIELD_NAMES);
           return IsOnlyNumbers(password);
        } catch (IllegalArgumentException ignored) {
            // msg doesn't contain any of the PASSWORD_FIELD_NAMES
        }
        return false;
    }

        @Override
        public String getName () {
            return RULE_NAME;
        }

        private String getFirstFieldValue (HttpMessage msg, String[]fieldNames) throws IllegalArgumentException {
            for (String fieldName : fieldNames) {
                try {
                    return getFieldValue(msg, fieldName);
                } catch (IllegalArgumentException ignored) {

                }
            }
            throw new IllegalArgumentException("The given message does not contain any of the given field names");
        }

        private String getFieldValue (HttpMessage msg, String fieldName) throws IllegalArgumentException {
            TreeSet<HtmlParameter> msgParams =  new TreeSet<HtmlParameter>();
            msgParams.addAll(msg.getFormParams());
            msgParams.addAll(msg.getUrlParams());
            for (HtmlParameter param : msgParams) {
                if (param.getName().equals(fieldName)) {
                    return param.getValue();
                }
            }
            throw new IllegalArgumentException("The given message does not contain a field with the given name");
        }
    private boolean IsOnlyNumbers(String stringToCheck){
        return (stringToCheck.chars().allMatch(Character::isDigit));
    }
    }

