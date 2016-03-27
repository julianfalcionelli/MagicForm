package julianfalcionelli.magicform;

import android.view.View;
import android.widget.TextView;

import java.util.regex.Pattern;

public class ValidationRegex extends Validation
{
	private Pattern mPattern;

	public ValidationRegex(Pattern pattern)
	{
		mPattern = pattern;
	}

	@Override
	public boolean isValid(View view)
	{
		return mPattern.matcher(((TextView) view).getText()).matches();
	}
}
