//package am.main.exception;
//
//import am.main.api.validation.FormValidation;
//import am.shared.enums.EC;
//
//import java.io.Serializable;
//
///**
// * Created by ahmed.motair on 1/9/2018.
// */
//public class SerializedBusinessException extends Exception implements Serializable{
//    private EC errorCode;
//    private String formattedError;
//    private String CLASS;
//    private String METHOD;
//    private String fullErrMsg;
//    private FormValidation errorList;
//    private AMError amError;
//
//    public SerializedBusinessException(BusinessException ex){
//        super(ex.getCause());
//        this.errorCode = ex.getErrorCode();
//        this.formattedError = ex.getFormattedError();
//        this.CLASS = ex.getCLASS();
//        this.METHOD = ex.getMETHOD();
//        this.fullErrMsg = ex.getFullErrMsg();
//        this.errorList = ex.getErrorList();
//        this.amError = (AMError) ex.getResponse().getEntity();
//    }
//
//    public EC getErrorCode() {
//        return errorCode;
//    }
//    public void setErrorCode(EC errorCode) {
//        this.errorCode = errorCode;
//    }
//
//    public String getFormattedError() {
//        return formattedError;
//    }
//    public void setFormattedError(String formattedError) {
//        this.formattedError = formattedError;
//    }
//
//    public String getCLASS() {
//        return CLASS;
//    }
//    public void setCLASS(String CLASS) {
//        this.CLASS = CLASS;
//    }
//
//    public String getMETHOD() {
//        return METHOD;
//    }
//    public void setMETHOD(String METHOD) {
//        this.METHOD = METHOD;
//    }
//
//    public String getFullErrMsg() {
//        return fullErrMsg;
//    }
//    public void setFullErrMsg(String fullErrMsg) {
//        this.fullErrMsg = fullErrMsg;
//    }
//
//    public FormValidation getErrorList() {
//        return errorList;
//    }
//    public void setErrorList(FormValidation errorList) {
//        this.errorList = errorList;
//    }
//
//    public AMError getAmError() {
//        return amError;
//    }
//    public void setAmError(AMError amError) {
//        this.amError = amError;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof SerializedBusinessException)) return false;
//
//        SerializedBusinessException that = (SerializedBusinessException) o;
//
//        if (errorCode != that.errorCode) return false;
//        if (formattedError != null ? !formattedError.equals(that.formattedError) : that.formattedError != null) return false;
//        if (CLASS != null ? !CLASS.equals(that.CLASS) : that.CLASS != null) return false;
//        if (amError != null ? !amError.equals(that.amError) : that.amError != null) return false;
//        if (METHOD != null ? !METHOD.equals(that.METHOD) : that.METHOD != null) return false;
//        if (fullErrMsg != null ? !fullErrMsg.equals(that.fullErrMsg) : that.fullErrMsg != null) return false;
//        return errorList != null ? errorList.equals(that.errorList) : that.errorList == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = errorCode != null ? errorCode.hashCode() : 0;
//        result = 31 * result + (formattedError != null ? formattedError.hashCode() : 0);
//        result = 31 * result + (CLASS != null ? CLASS.hashCode() : 0);
//        result = 31 * result + (METHOD != null ? METHOD.hashCode() : 0);
//        result = 31 * result + (amError != null ? amError.hashCode() : 0);
//        result = 31 * result + (fullErrMsg != null ? fullErrMsg.hashCode() : 0);
//        result = 31 * result + (errorList != null ? errorList.hashCode() : 0);
//        return result;
//    }
//}
