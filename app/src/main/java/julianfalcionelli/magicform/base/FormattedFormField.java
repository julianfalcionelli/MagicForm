package julianfalcionelli.magicform.base;

import android.app.Activity;
import android.view.View;

public class FormattedFormField extends FormField {
    private String mRawValue = "", mRegularExp, mReplacement;

    public FormattedFormField(View view, Formatter formatter) {
        super(view);
        setupFormatter(formatter);
    }

    public FormattedFormField(View view, String regularExp, String replacement) {
        super(view);
        setupFormatter(regularExp, replacement);
    }

    public FormattedFormField(Activity activity, int viewId, Formatter formatter) {
        super(activity, viewId);
        setupFormatter(formatter);
    }

    public FormattedFormField(Activity activity, int viewId, String regularExp,
            String replacement) {
        super(activity, viewId);
        setupFormatter(regularExp, replacement);
    }

    private void setupFormatter(Formatter formatter) {
        setupFormatter(formatter.getRegularExp(), formatter.getReplacement());
    }

    private void setupFormatter(String regularExp, String replacement) {
        mRegularExp = regularExp;
        mReplacement = replacement;
    }

    public String getRawValue() {
        return mRawValue;
    }

    public String getRawValue(String string) {
        return string.replaceAll("\\D+", "");
    }

    public void setRawValue(String rawValue) {
        mRawValue = rawValue;
    }

    public String getFormattedValue(String rawValue) {
        StringBuilder formattedValue = new StringBuilder();
        formattedValue.append(rawValue.replaceFirst(mRegularExp, mReplacement));

        while (formattedValue.length() > 0 && !Character.isDigit(
                formattedValue.charAt(formattedValue.length() - 1))) {
            formattedValue.deleteCharAt(formattedValue.length() - 1);
        }

        return formattedValue.toString();
    }
}