package core;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

class IntDocumentFilter extends DocumentFilter
{

    private Boolean hasMinus;

    IntDocumentFilter(Boolean hasMinus)
    {
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
        if (hasMinus && offset == 0 && string.equals("-"))
            added = "-";
        else
        {
            char[] buffer = string.toCharArray();
            char[] digit = new char[buffer.length];
            int j = 0;
            for (char aBuffer : buffer)
            {
                if (Character.isDigit(aBuffer))
                    digit[j++] = aBuffer;
                if (hasMinus && offset == 0 && j == 0 && aBuffer == '-')
                    digit[j++] = '-';
            }
            added = new String(digit, 0, j);
        }
        super.replace(fb, offset, length, added, attrs);
    }
}