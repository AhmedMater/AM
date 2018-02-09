//package am.main.data.dto;
//
//import am.main.api.validation.groups.*;
//import am.main.common.RegExp;
//import org.hibernate.validator.constraints.Length;
//
//import javax.validation.constraints.*;
//import java.io.Serializable;
//
///**
// * Created by ahmed.motair on 2/1/2018.
// */
//public class AppLoginData implements Serializable{
//
//    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
//    @Size(min = 3, max = 3, message = FormValidation.EQ_LENGTH, groups = LengthValidation.class)
//    @Pattern(regexp = RegExp.LOOKUP, message = FormValidation.REGEX, groups = InvalidValidation.class)
//    @NotBlank(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
//    private String appID;
//
//    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
//    @Length(min = 5, max = 50, message = FormValidation.MIN_MAX_LENGTH, groups = LengthValidation.class)
//    @Pattern(regexp = RegExp.USERNAME, message = FormValidation.REGEX, groups = InvalidValidation.class)
//    @NotEmpty(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
//    private String username;
//
//    @NotNull(message = FormValidation.REQUIRED, groups = RequiredValidation.class)
//    @Length(min = 5, max = 30, message = FormValidation.MIN_MAX_LENGTH, groups = LengthValidation.class)
//    @Pattern(regexp = RegExp.PASSWORD, message = FormValidation.REGEX, groups = InvalidValidation.class)
//    @NotEmpty(message = FormValidation.EMPTY_STR, groups = BlankValidation.class)
//    private String password;
//
//    public AppLoginData() {
//    }
//    public AppLoginData(String appID, String username, String password) {
//        this.appID = appID;
//        this.username = username;
//        this.password = password;
//    }
//
//    public String getAppID() {
//        return appID;
//    }
//    public void setAppID(String appID) {
//        this.appID = appID;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof AppLoginData)) return false;
//
//        AppLoginData that = (AppLoginData) o;
//
//        if (getAppID() != null ? !getAppID().equals(that.getAppID()) : that.getAppID() != null) return false;
//        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null) return false;
//        return getPassword() != null ? getPassword().equals(that.getPassword()) : that.getPassword() == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = getAppID() != null ? getAppID().hashCode() : 0;
//        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
//        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return "AppLoginData{" +
//                "appID = " + appID +
//                ", username = " + username +
//                ", password = " + password +
//                "}\n";
//    }
//}
