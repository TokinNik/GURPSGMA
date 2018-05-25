package core;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FloatDocumentFilter extends DocumentFilter
{
    private JTextField field;
    private Boolean hasMinus;

    FloatDocumentFilter(JTextField field, Boolean hasMinus)
    {
        this.field = field;
        this.hasMinus = hasMinus;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException
    {
        if (string.isEmpty())
            return;
        super.insertString(fb, offset, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attrs) throws BadLocationException
    {
        if (string.isEmpty())
            return;
        String added;
        Boolean hasDot = field.getText().indexOf('.') != -1;
        if (string.length() == 1 && offset > 0)
            if (string.equals(".") && !hasDot)
                added = string;
            else
                added = string.replaceAll("\\D++", "");
        else if (string.length() == 1 && offset == 0 && (string.equals(".")))
            added = "";
        else if (hasMinus && string.length() == 1 && offset == 0 && string.equals("-"))
            added = "-";
        else
        {
            if (offset == 0)
                hasDot = false;
            char[] buffer = string.toCharArray();
            char[] digit = new char[buffer.length];
            int j = 0;
            for (char aBuffer : buffer)
            {
                if (Character.isDigit(aBuffer))
                    digit[j++] = aBuffer;
                if (!hasDot && aBuffer == '.')
                {
                    digit[j++] = '.';
                    hasDot = true;
                }
                if (hasMinus && offset == 0 && j == 0 && aBuffer == '-')
                    digit[j++] = '-';
            }
            added = new String(digit, 0, j);
        }
        super.replace(fb, offset, length, added, attrs);
    }
}
