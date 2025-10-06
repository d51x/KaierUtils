package ru.d51x.kaierutils.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.widget.TextView;

public class UiUtils {

    public static final int TEXT_SIZE_BEFORE_DOT = 40;
    public static final int TEXT_SIZE_AFTER_DOT = 16;

    public static void TextViewToSpans(TextView tv, String value, int size1, int size2) {
        String s = value.replace(",", ".");
        SpannableString ss =  new SpannableString(s);
        int dot = s.indexOf(".");
        if ( dot > -1 ) {
            ss.setSpan(new AbsoluteSizeSpan(size1), 0, dot, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new AbsoluteSizeSpan(size2), dot, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tv.setText(ss);
    }
}
