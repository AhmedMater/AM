package am.main.spi;

import am.main.data.enums.CodeTypes;
import am.main.session.AppSession;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ahmed.motair on 1/21/2018.
 */
public abstract class AMCode {
    private final CodeTypes CODE_TYPE;
    private final Boolean IS_INTERNAL;
    private final Integer CODE_ID;
    private final String CODE_NAME;
    private final String PREFIX;
    private String INTERNAL_MSG;

    private static HashMap<String, AMCode> ALL_CODES = new HashMap<>();

    protected AMCode(CodeTypes CODE_TYPE, Boolean IS_INTERNAL, Integer CODE_ID, String CODE_NAME, String INTERNAL_MSG, String PREFIX) {
        this.CODE_TYPE = CODE_TYPE;
        this.IS_INTERNAL = IS_INTERNAL;
        this.INTERNAL_MSG = INTERNAL_MSG;
        this.CODE_ID = CODE_ID;
        this.CODE_NAME = CODE_NAME;
        this.PREFIX = PREFIX;

        ALL_CODES.put(getFullCode(), this);
    }

    public CodeTypes getCodeType() {
        return CODE_TYPE;
    }
    public Boolean isInternal() {
        return IS_INTERNAL;
    }
    public Integer getCodeID() {
        return CODE_ID;
    }
    public String getCodeName() {
        return CODE_NAME;
    }
    public String getPrefix() {
        return PREFIX;
    }

    public String getFullCode(){
        String type = "";
        switch (CODE_TYPE){
            case INFO: type = "I-"; break;
            case ERROR: type = "E-"; break;
            case WARN: type = "W-"; break;
        }
        return (!PREFIX.isEmpty() ? PREFIX + "-" : "") + type + CODE_NAME + (CODE_ID != -1 ? "-" + CODE_ID : "");
    }
    public AMCode getCode(String fullCode) throws Exception{
        for (String code : ALL_CODES.keySet()) {
            if (code.equals(fullCode))
                return ALL_CODES.get(code);
        }
        throw new Exception("Invalid Code ID " + fullCode);
    }

    /**
     * Retrieves the AMCode Object associated with the Full Code
     * @param code Full Code
     * @return AMCode Object
     * @throws IllegalArgumentException If the Code isn't found in the System
     */
    public static AMCode getCodeByFullCode(String code) throws IllegalArgumentException{
        AMCode amCode = ALL_CODES.get(code);
        if(amCode == null)
            throw new IllegalArgumentException(code + " Code isn't found in the System");
        return amCode;
    }

    private String getInternalMsg() {
        return INTERNAL_MSG;
    }

    public String getFullMsg(Object ... args) throws IllegalArgumentException {
        return getFullMsg(null, args);
    }

    public String getFullMsg(AppSession appSession, Object ... args) throws IllegalArgumentException{
        if(isInternal()) {
            Matcher matcher = Pattern.compile("\\{[0-9]+\\}").matcher(INTERNAL_MSG);
            String _message = "";

            Set<String> placeholders = new HashSet<>();
            while (matcher.find())
                placeholders.add(matcher.group());

            if(args.length != 0 && args[1] instanceof Object[])
                args = (Object[]) args[1];

            if(placeholders.size() != args.length)
                throw new IllegalArgumentException(getFullCode() + " Code needs " +
                        placeholders.size() + " Placeholders, and arguments provided are " + args.length + " Args, Which are " + Arrays.toString(args));

            return getFullCode() + ": " + MessageFormat.format(INTERNAL_MSG, args);
        } else
            return getFullCode() + ": " + appSession.getMessageHandler().getMsg(appSession, this, args);
    }
    public void setInternalMsg(String INTERNAL_MSG) {
        this.INTERNAL_MSG = INTERNAL_MSG;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AMCode)) return false;

        AMCode amCode = (AMCode) o;

        return getFullCode() != null ? getFullCode().equals(amCode.getFullCode()) : amCode.getFullCode() == null;
    }

    @Override
    public int hashCode() {
        return getFullCode() != null ? getFullCode().hashCode() : 0;
    }

    @Override
    public String toString() {
        return getFullCode();
    }
}
