package am.main.spi;

import am.main.api.MessageHandler;
import am.main.data.enums.CodeTypes;

import java.text.MessageFormat;
import java.util.*;
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
        if(isInternal()) {
            switch (CODE_TYPE) {
                case INFO:  type = "I-"; break;
                case ERROR: type = "E-"; break;
                case WARN:  type = "W-"; break;
            }
        }
        return (!PREFIX.isEmpty() ? PREFIX + "-" : "") + type + CODE_NAME + (CODE_ID != -1 ? "-" + CODE_ID : "");
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
        List<String> argList = new ArrayList<>();

        if(args.length == 1 && args[0] instanceof List)
            argList = (List<String>) args[0];
        else {
            for (Object arg : args)
                argList.add(arg.toString());
        }
        return getFullMsg(null,  argList);
    }
    public String getFullMsg(MessageHandler messageHandler, Object ... args) throws IllegalArgumentException {
        List<String> argList = new ArrayList<>();

        if(args.length == 1 && args[0] instanceof List)
            argList = (List<String>) args[0];
        else {
            for (Object arg : args)
                argList.add(arg.toString());
        }
        return getFullMsg(messageHandler,  argList);
    }

    private String getFullMsg(MessageHandler messageHandler, List<String> args) throws IllegalArgumentException{
        if(isInternal()) {
            Matcher matcher = Pattern.compile("\\{[0-9]+\\}").matcher(INTERNAL_MSG);
            String _message = "";

            Set<String> placeholders = new HashSet<>();
            while (matcher.find())
                placeholders.add(matcher.group());

            if(placeholders.size() != args.size())
                throw new IllegalArgumentException(getFullCode() + " Code needs " +
                        placeholders.size() + " Placeholders, and arguments provided are " + args.size() + " Args, Which are " + args.toString());

            return getFullCode() + ": " + MessageFormat.format(INTERNAL_MSG, args.toArray());
        } else {
            if(messageHandler == null)
                throw new IllegalArgumentException("Session can't be null for External Codes");
            else
                return getFullCode() + ": " + messageHandler.getMsg(this, args);
        }
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
