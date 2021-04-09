package julianfalcionelli.magicform.validation;

import android.widget.TextView;

import java.util.regex.Pattern;

public class ValidationRegex extends Validation<TextView> {
    private Pattern mPattern;

    public ValidationRegex(Pattern pattern) {
        mPattern = pattern;
    }

    @Override
    public boolean isValid(TextView view) {
        return mPattern.matcher(view.getText()).matches();
    }
}
