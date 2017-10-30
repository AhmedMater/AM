package am.api.components;

import am.common.enums.AME;
import am.exception.GeneralException;
import am.session.AppSession;
import am.session.Phase;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * Created by ahmed.motair on 9/26/2017.
 */
public class AMSecurityManager {
    @Inject private AppLogger logger;
    private static final String CLASS = "AMSecurityManager";

    // Generated at https://www.random.org/strings
    private final static String _salt = "rz8LuOtFBXphj9WQfvFh";
    private final static String SHA_256_ALGORITHM = "HmacSHA256";
    private final static String MD5_ALGORITHM = "MD5";

    public String generateToken(AppSession appSession, String userName, String password, long ticks) throws Exception {
        String FN_NAME = "generateToken";
        AppSession session = appSession.updateSession(Phase.SECURITY, CLASS, FN_NAME);
        logger.startDebug(session, userName, ticks);
        Mac sha256_HMAC;

        try {
            sha256_HMAC = Mac.getInstance(SHA_256_ALGORITHM);
        }catch (Exception ex){
            throw new GeneralException(session, AME.SEQ_003, SHA_256_ALGORITHM);
        }

        String hash = String.join(":",new String[] {userName,String.valueOf(ticks)});
        byte[] hashedPassword = getHashedPassword(session, password);

        String result;
        if(hashedPassword != null) {
            SecretKeySpec secret_key = new SecretKeySpec(hashedPassword, SHA_256_ALGORITHM);
            try {
                sha256_HMAC.init(secret_key);
            }catch (Exception ex){
                throw new GeneralException(session, AME.SEQ_004);
            }
            String hashLeft = new String(Base64.getEncoder().encode(sha256_HMAC.doFinal(hash.getBytes())),"US-ASCII");
            String hashRight = String.join(":", new String[] { userName, String.valueOf(ticks) });
            result = new String(Base64.getEncoder().encode(String.join(":", new String[] { hashLeft, hashRight }).getBytes()),"US-ASCII");
        }
        else
            throw new GeneralException(session, AME.SEQ_001);

        logger.endDebug(session, "User Token");
        return result;
    }

    public byte[] getHashedPassword(AppSession appSession, String password) throws Exception{
        String FN_NAME = "getHashedPassword";
        AppSession session = appSession.updateSession(Phase.SECURITY, CLASS, FN_NAME);
        logger.startDebug(session);

        String key = String.join(":", new String[]{password, _salt});
        Mac sha256_HMAC;

        try {
            sha256_HMAC = Mac.getInstance(SHA_256_ALGORITHM);
        }catch (Exception ex){
            throw new GeneralException(session, AME.SEQ_003, SHA_256_ALGORITHM);
        }

        SecretKeySpec secret_key = new SecretKeySpec(_salt.getBytes(), SHA_256_ALGORITHM);
        try {
            sha256_HMAC.init(secret_key);
        }catch (Exception ex){
            throw new GeneralException(session, AME.SEQ_004);
        }

        byte[] hash = Base64.getEncoder().encode(sha256_HMAC.doFinal(key.getBytes()));

        logger.endDebug(session, hash);
        return hash;
    }

    public String dm5Hash(AppSession appSession, String password) throws Exception{
        String FN_NAME = "dm5Hash";
        AppSession session = appSession.updateSession(Phase.SECURITY, CLASS, FN_NAME);
        logger.startDebug(session);
        MessageDigest md;

        try{
            md = MessageDigest.getInstance(MD5_ALGORITHM);
        }catch (Exception ex){
            throw new GeneralException(session, AME.SEQ_003, MD5_ALGORITHM);
        }

        md.update(password.getBytes());
        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++)
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

        logger.endDebug(session, "Hashed Password");
        return sb.toString();
    }

}
