package de.oceanlabs.mcpconfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Helper
{
    private static final String KEY_OPEN = "{";
    private static final String KEY_CLOSE = "}";

    public static List<String> fillVariables(final Iterable<String> keys, final Map<String, Object> values)
    {
        final List<String> result = new ArrayList<>();
        for (final String key : keys)
        {
            if (!key.startsWith(KEY_OPEN) || !key.endsWith(KEY_CLOSE) || !values.containsKey(key.substring(1, key.length() - 1)))
            {
                result.add(key);
            }
            else
            {
                final Object value = values.get(key.substring(1, key.length() - 1));
                result.add(value instanceof File ? ((File) value).getAbsolutePath() : value.toString());
            }
        }
        return result;
    }
}
