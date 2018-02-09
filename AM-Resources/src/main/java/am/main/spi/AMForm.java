package am.main.spi;

/**
 * Created by ahmed.motair on 1/31/2018.
 */
public abstract class AMForm {
    private final String form;

    public AMForm(String form) {
        this.form = form;
    }

    public String getForm() {
        return form;
    }

    @Override
    public String toString() {
        return "AMForm{" +
                "form = " + form +
                "}\n";
    }
}
