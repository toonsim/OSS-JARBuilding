import org.parosproxy.paros.network.HttpMessage;
import org.zaproxy.zap.extension.policyrulescanner.PolicyRule;


public class GenericEmailAddressRule extends PolicyRule {
    private static final String RULE_NAME = "GenericEmailAddressRule";
    private String[] genericEmailPrefixes = {"abuse@","accounting@","accounts@","adm@","admin@","administration@","administrator@","admissions@","ads@","all@","answers@","anti-spam@","antispam@","asdf@","billing@","ceo@","comments@","compliance@","contact@","contactus@","customer@","customercare@","customerservice@","database@","decliend@","decline@","declined@","denied@","designer@","devnull@","director@","dns@","email@","employment@","enquiries@","everyone@","fbl@","feedback@","finance@","ftp@","general@","hello@","helpdesk@","home@","hostmaster@","hr@","info@","information@","inoc@","investorrelations@","ispfeedback@","ispsupport@","jobs@","lawyer@","lawyers@","legal@","list@","list-request@","mail@","mailbox@","mail-daemon@","maildaemon@","mail-deamon@","manager@","managers@","marketing@","me@","media@","mediarelations@","mkt@","news@","noc@","noreplies@","no-reply@","noreply@","noemail@","nospam@","nothanks@","null@","office@","operations@","orders@","phish@","phishing@","post@","postbox@","postmaster@","prepress@","president@","press@","privacy@","purchasing@","qwer@","qwert@","qwerty@","reception@","refuse@","refused@","registrar@","remove@","request@","reservations@","returns@","root@","sales@","secretary@","security@","service@","services@","shop@","spam@","staff@","studio@","subscribe@","support@","sysadmin@","tech@","undisclosed-recipients@","unsubscribe@","usenet@","users@","uucp@","web@","webmaster@","welcome@","www@"};

    public GenericEmailAddressRule(String policyName) {
        super(policyName);
    }

    @Override
    public boolean isFlaggedBy(HttpMessage msg) {
        for (String prefix: genericEmailPrefixes) {
            if (msg.getRequestHeader().toString().contains(prefix)
                    || msg.getResponseHeader().toString().contains(prefix)
                    || msg.getRequestBody().toString().contains(prefix)
                    || msg.getResponseBody().toString().contains(prefix)) {
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
