package ru.d51x.kaierutils.Settings;

import android.content.Context;
import android.preference.EditTextPreference;
import android.util.AttributeSet;

public class NumberTextPreference extends EditTextPreference {
    private static final String PREFERENCE_NS = "http://schemas.android.com/apk/res/ru.d51x.kaierutils";
    private static final String ANDROID_NS = "http://schemas.android.com/apk/res/android";
    private static final String ATTR_DEFAULT_VALUE = "defaultValue";
    private final String mDefaultValue;

    public NumberTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDefaultValue = attrs.getAttributeValue(ANDROID_NS, ATTR_DEFAULT_VALUE);
    }

    @Override
    public CharSequence getSummary() {
        String summary = super.getSummary().toString();
        String value = getPersistedString(mDefaultValue);
        return String.format(summary, value);
    }
}
