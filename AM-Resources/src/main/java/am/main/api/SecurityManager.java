package am.main.api;

import am.main.exception.GeneralException;
import am.main.session.AppSession;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import java.security.MessageDigest;
import java.util.Base64;

import static am.main.data.enums.impl.AME.*;
import static am.main.data.enums.impl.AMI.*;
import static am.main.data.enums.impl.AMP.SECURITY_MANAGER;


/**
 * Created by ahmed.motair on 9/26/2017.
 */
public class SecurityManager {
    @Inject private AppLogger logger;
    private static final String CLASS = SecurityManager.class.getSimpleName();
//    private static final AppSession appSession = new AppSession(AM, SECURITY_MANAGER, , CLASS);

    // Generated at https://www.random.org/strings
    private final static String _salt = "rz8LuOtFBXphj9WQfvFh";
    private final static String SHA_256_ALGORITHM = "HmacSHA256";
    private final static String MD5_ALGORITHM = "MD5";

    public String generateAccessToken(AppSession appSession, String userName, String password, long ticks) throws Exception {
        String METHOD = "generateAccessToken";
        AppSession session = appSession.updateSession(SECURITY_MANAGER, CLASS, METHOD);
        logger.startDebug(session, userName, ticks);
        logger.info(session, I_SEC_1);
        Mac sha256_HMAC;

        try {
            sha256_HMAC = Mac.getInstance(SHA_256_ALGORITHM);
        }catch (Exception ex){
            throw new GeneralException(session, E_SEC_3, SHA_256_ALGORITHM);
        }

        String hash = String.join(":",new String[] {userName,String.valueOf(ticks)});
        byte[] hashedPassword = getHashedPassword(session, password);

        String result;
        if(hashedPassword != null) {
            SecretKeySpec secret_key = new SecretKeySpec(hashedPassword, SHA_256_ALGORITHM);
            try {
                sha256_HMAC.init(secret_key);
            }catch (Exception ex){
                throw new GeneralException(session, ex, E_SEC_4);
            }
            String hashLeft = new String(Base64.getEncoder().encode(sha256_HMAC.doFinal(hash.getBytes())),"US-ASCII");
            String hashRight = String.join(":", new String[] { userName, String.valueOf(ticks) });
            result = new String(Base64.getEncoder().encode(String.join(":", new String[] { hashLeft, hashRight }).getBytes()),"US-ASCII");
        }
        else
            throw new GeneralException(session, E_SEC_1);

        logger.info(session, I_SEC_2);
        logger.endDebug(session, "User Token");
        return result;
    }

    public byte[] getHashedPassword(AppSession appSession, String password) throws Exception{
        String METHOD = "getHashedPassword";
        AppSession session = appSession.updateSession(SECURITY_MANAGER, CLASS, METHOD);
        logger.startDebug(session);
        logger.info(session, I_SEC_3, SHA_256_ALGORITHM);

        String key = String.join(":", new String[]{password, _salt});
        Mac sha256_HMAC;

        try {
            sha256_HMAC = Mac.getInstance(SHA_256_ALGORITHM);
        }catch (Exception ex){
            throw new GeneralException(session, ex, E_SEC_3, SHA_256_ALGORITHM);
        }

        SecretKeySpec secret_key = new SecretKeySpec(_salt.getBytes(), SHA_256_ALGORITHM);
        try {
            sha256_HMAC.init(secret_key);
        }catch (Exception ex){
            throw new GeneralException(session, ex, E_SEC_4);
        }

        byte[] hash = Base64.getEncoder().encode(sha256_HMAC.doFinal(key.getBytes()));

        logger.info(session, I_SEC_4);
        logger.endDebug(session, hash);
        return hash;
    }

    public String dm5Hash(AppSession appSession, String password) throws Exception{
        String METHOD = "dm5Hash";
        AppSession session = appSession.updateSession(SECURITY_MANAGER, CLASS, METHOD);
        logger.startDebug(session);
        logger.info(session, I_SEC_3, MD5_ALGORITHM);
        MessageDigest md;

        try{
            md = MessageDigest.getInstance(MD5_ALGORITHM);
        }catch (Exception ex){
            throw new GeneralException(session, ex, E_SEC_3, MD5_ALGORITHM);
        }

        md.update(password.getBytes());
        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++)
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

        logger.info(session, I_SEC_4);
        logger.endDebug(session, "Hashed Password");
        return sb.toString();
    }

}
