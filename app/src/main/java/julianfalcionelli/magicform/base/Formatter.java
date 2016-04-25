package julianfalcionelli.magicform.base;

import android.view.View;

public enum Formatter
{
	PHONE("(\\d{0,1})(\\d{0,3})(\\d{0,3})(\\d{0,4})", "+$1 ($2) $3-$4"),
	SECURIY_NUMBER("(\\d{0,3})(\\d{0,2})(\\d{0,4})", "$1-$2-$3");

	private String mRegularExp, mReplacement;

	Formatter(String regularExp, String replacement)
	{
		mRegularExp = regularExp;
		mReplacement = replacement;
	}

	public String getRegularExp()
	{
		return mRegularExp;
	}

	public String getReplacement()
	{
		return mReplacement;
	}

}